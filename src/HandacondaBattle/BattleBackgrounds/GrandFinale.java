package HandacondaBattle.BattleBackgrounds;

import HandacondaBattle.BG;
import HandacondaBattle.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.LinkedList;

public class GrandFinale extends BattleBack {

    private LinkedList<BufferedImage> clouds1 = new LinkedList<>();
    private LinkedList<BufferedImage> clouds2 = new LinkedList<>();

    private BufferedImage bg;
    private BufferedImage fg;

    private int animSpeed = 15;

    private long lastUpdate = 0;

    private int c1CurrentFrame = 0;
    private int c2CurrentFrame = 0;

    public GrandFinale(Game game) throws IOException {
        super(game);
        game.handler.addObject(this);

        String fileName = "sprites/backgrounds/battle/grand finale/bg.png";

        ClassLoader classLoader = BG.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);
        bg = ImageIO.read(inputStream);

        fileName = "sprites/backgrounds/battle/grand finale/fg.png";

        classLoader = BG.class.getClassLoader();
        inputStream = classLoader.getResourceAsStream(fileName);
        fg = ImageIO.read(inputStream);

        int i = 1;
        try {
            while (true) {
                fileName = "sprites/backgrounds/battle/grand finale/clouds 1/" + i + ".png";

                classLoader = BG.class.getClassLoader();
                inputStream = classLoader.getResourceAsStream(fileName);
                clouds1.add(ImageIO.read(inputStream));
                i += 1;
            }
        } catch(Exception e) {
            // No u
        }

        i = 1;
        try {
            while (true) {
                fileName = "sprites/backgrounds/battle/grand finale/clouds 2/" + i + ".png";

                classLoader = BG.class.getClassLoader();
                inputStream = classLoader.getResourceAsStream(fileName);
                clouds2.add(ImageIO.read(inputStream));
                i += 1;
            }
        } catch(Exception e) {
            // No u
        }
    }

    public void tick() {

    }

    public void render(Graphics g) {
        long now = System.currentTimeMillis();

        if (now - lastUpdate > animSpeed) {
            lastUpdate = now;

            if (c1CurrentFrame < clouds1.size() - 1) {
                c1CurrentFrame += 1;
            } else {
                c1CurrentFrame = 0;
            }

            if (c2CurrentFrame < clouds2.size() - 1) {
                c2CurrentFrame += 1;
            } else {
                c2CurrentFrame = 0;
            }
        }

        g.drawImage(clouds2.get(c2CurrentFrame), x, y, clouds2.get(c2CurrentFrame).getWidth(), clouds2.get(c2CurrentFrame).getHeight(), null);
        g.drawImage(clouds1.get(c1CurrentFrame), x, y - 10, clouds1.get(c1CurrentFrame).getWidth(), clouds1.get(c1CurrentFrame).getHeight(), null);
        g.drawImage(bg, x, y, bg.getWidth(), bg.getHeight(), null);
    }

    public void renderFront(Graphics g) {
        g.drawImage(fg, x, y, fg.getWidth(), fg.getHeight(), null);
    }
}
