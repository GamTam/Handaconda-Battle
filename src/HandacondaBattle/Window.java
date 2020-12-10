package HandacondaBattle;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Window extends Canvas {

    public Window(int width, int height, String title, Game game) throws IOException {
        JFrame frame = new JFrame(title);

        frame.pack();
        frame.setSize(new Dimension(width, height));

        String fileName = "sprites/icon.png";

        ClassLoader classLoader = Button.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);
        InputStream bs = new BufferedInputStream(inputStream);

        BufferedImage icon = ImageIO.read(bs);

        frame.setIconImage(icon);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(game);
        frame.setVisible(true);
        frame.setBackground(Color.green);
        game.start();
    }
}
