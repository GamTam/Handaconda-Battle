package HandacondaBattle;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class BG extends GameObject  {

    BufferedImage image;
    boolean travel;
    boolean up;

    public BG(Game game, String image, boolean travel, boolean up) throws IOException {
        super(0, 0, ID.BG, game);

        if (!up && travel) {
            y = -game.getHeight();
        }

        this.travel = travel;
        this.up = up;

        String fileName = "sprites/backgrounds/" + image + ".png";

        ClassLoader classLoader = BG.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);
        this.image = ImageIO.read(inputStream);
        game.handler.addObject(this);
    }

    public void tick() {
        if (travel) {
            if (!up) {
                y += 1;

                if (y >= -20) {
                    y -= game.getHeight() * 2;
                }
            } else {
                y -= 1;

                if (y <= -game.getHeight() * 2) {
                    y += game.getHeight() * 2;
                }
            }
        }
    }

    public void render(Graphics g) {
        g.drawImage(image, x, y, image.getWidth(), image.getHeight(), null);
    }
}
