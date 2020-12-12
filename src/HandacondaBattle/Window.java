package HandacondaBattle;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Window extends Canvas {
    public JFrame frame;

    public Window(int width, int height, String title, Game game) throws IOException {
        frame = new JFrame(title);

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
