package HandacondaBattle;

import javax.sound.sampled.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Path;

class Song implements Runnable {
    // Variable Init
    public String name;
    public int loopStart = 0;
    public int loopEnd = -1;

    private int targetGain = 1;

    public Clip clip;
    public boolean running = false;

    public Song (String newSong) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        // Locate file
        String fileName = "songData.csv";

        ClassLoader classLoader = Song.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);
        InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);

        BufferedReader br = new BufferedReader(streamReader);

        String oneData = "";
        int i = 0;
        boolean keepLooping = true;

        // Loops through CSV and adds data to food class
        while ((oneData = br.readLine()) != null && keepLooping) {
            String[] data = oneData.split(",");

            if (i > 0 && data[0].equalsIgnoreCase(newSong)) {
                name = data[0];
                loopStart = Integer.parseInt(data[1]);
                loopEnd = Integer.parseInt(data[2]);
                keepLooping = false;
            }

            i++;
        }

        fileName = "songs/" + name + ".wav";

        classLoader = Song.class.getClassLoader();
        inputStream = classLoader.getResourceAsStream(fileName);
        InputStream bs = new BufferedInputStream(inputStream);

        // Load and play audio
        AudioInputStream ais = AudioSystem.getAudioInputStream(bs);
        AudioFormat format = ais.getFormat();
        DataLine.Info info = new DataLine.Info(Clip.class, format);
        clip = (Clip)AudioSystem.getLine(info);
        clip.open(ais);
        clip.setLoopPoints(loopStart, loopEnd);
    }

    // Plays song
    public void play() {
        try {
            if (!clip.isActive()) {
                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(0);
                clip.setFramePosition(0);
                clip.loop(-1);

                long i = 0;

                while (!clip.isActive()) {
                    i++;
                }
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    // Stops song
    public void stop() {
        if (clip != null) {
            if (clip.isActive()) {
                clip.stop();
            }
        }
    }

    public void fadeOut() {
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

        if (!running && clip.isActive()) {
            targetGain = -50;
            Thread t = new Thread(this);
            t.start();
        } else {
            gainControl.setValue(targetGain);
        }
    }

    // Fades in or out (runs on a different thread)
    public void run() {
        running = true;
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        if (gainControl.getValue() > targetGain) {
            while (gainControl.getValue() > targetGain) {
                gainControl.setValue(gainControl.getValue() - 0.2f);
                try {Thread.sleep(10);} catch (Exception e) {}
            }

            stop();
        }

        running = false;
    }
}