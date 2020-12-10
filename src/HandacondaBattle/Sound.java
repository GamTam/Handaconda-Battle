package HandacondaBattle;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class Sound {
    // Variable Init
    public String sound;

    public Clip clip;

    public Sound(String sound) {
        this.sound = sound;

        try {
            // Locate file
            String fileName = "sounds/" + sound + ".wav";

            ClassLoader classLoader = Sound.class.getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream(fileName);
            InputStream bs = new BufferedInputStream(inputStream);

            // Load and play audio
            AudioInputStream ais = AudioSystem.getAudioInputStream(bs);
            AudioFormat format = ais.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            clip = (Clip)AudioSystem.getLine(info);
            clip.open(ais);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public void play() {
        clip.start();
        int i = 0;
        while (!clip.isActive()) {
            i++;
        }
    }
}
