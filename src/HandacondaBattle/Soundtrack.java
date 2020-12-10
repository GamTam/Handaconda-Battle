package HandacondaBattle;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;
import java.util.Hashtable;

public class Soundtrack extends GameObject{
    private Hashtable<String, Song> soundtrack = new Hashtable<>();

    private boolean heavy = true;

    public Soundtrack(String menu, String gameplay, String secret, Game game) throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        super(0, 0, ID.SOUNDTRACK, game);

        soundtrack.put("title", new Song(menu));
        soundtrack.put("secret", new Song(secret));
        soundtrack.put("game", new Song("origami king boss"));

        soundtrack.put("mario", new Song("musee"));
        soundtrack.put("luigi", new Song("brobot battle"));
        soundtrack.put("shy guy", new Song("snifit or whiffit"));
        soundtrack.put("fawful", new Song("fawful is there"));
        soundtrack.put("fawful 2", new Song("grand finale"));
        soundtrack.put("sans", new Song("MEGALOVANIA"));
        soundtrack.put("toadette", new Song("where's toad"));
    }

    public void play(String song) {
        for (String s : soundtrack.keySet()) {
            if (!s.equalsIgnoreCase(song)) soundtrack.get(s).stop();
        }

        soundtrack.get(song).play();
    }

    public void randomGameSong() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        int num = game.random.nextInt(21);

        if (num == 1) {
            soundtrack.put("game", new Song("king bowser"));
        } else if (num == 2) {
            soundtrack.put("game", new Song("scissors"));
        } else if (num == 3) {
            soundtrack.put("game", new Song("final bowser g1"));
        } else if (num == 4) {
            soundtrack.put("game", new Song("grand finale"));
        } else if (num == 5) {
            soundtrack.put("game", new Song("the fanged fastener"));
        } else if (num == 6) {
            soundtrack.put("game", new Song("ASGORE"));
        } else if (num == 7) {
            soundtrack.put("game", new Song("MEGALOVANIA Orchestra"));
        } else if (num == 8) {
            soundtrack.put("game", new Song("final bowser g2"));
        } else if (num == 9) {
            soundtrack.put("game", new Song("battle with king olly"));
        } else if (num == 10) {
            soundtrack.put("game", new Song("rose battle"));
        } else if (num == 11) {
            soundtrack.put("game", new Song("rumble with wendy"));
        } else if (num == 12) {
            soundtrack.put("game", new Song("I'm not nice"));
        } else if (num == 13) {
            soundtrack.put("game", new Song("bouldergeist"));
        } else if (num == 14) {
            soundtrack.put("game", new Song("the marvelous duo"));
        } else if (num == 15) {
            soundtrack.put("game", new Song("meta knight"));
        } else if (num == 16) {
            soundtrack.put("game", new Song("battle tower"));
        } else if (num == 17) {
            soundtrack.put("game", new Song("origami castle"));
        } else if (num == 18) {
            soundtrack.put("game", new Song("the galaxy reactor"));
        } else if (num == 19) {
            soundtrack.put("game", new Song("cs miniboss"));
        } else if (num == 20) {
            soundtrack.put("game", new Song("black bowser castle"));
        } else {
            soundtrack.put("game", new Song("origami king boss"));
        }
    }

    public void fadeOutAll() {
        for (String s : soundtrack.keySet()) {
            soundtrack.get(s).fadeOut();
        }
    }

    public String getsongPlaying() {
        for (String s : soundtrack.keySet()) {
            if (soundtrack.get(s).clip.isActive()) {
                return s;
            }
        }

        return null;
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {
        //
    }
}
