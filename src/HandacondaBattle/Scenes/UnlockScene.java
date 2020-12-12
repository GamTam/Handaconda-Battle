package HandacondaBattle.Scenes;

import HandacondaBattle.*;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class UnlockScene extends GameScene {

    public UnlockScene(Game game, Handler handler, int unlockingChar) throws IOException, FontFormatException, URISyntaxException, InterruptedException {
        super(game, handler, SCENE.Unlock);
        Sound sound = new Sound("char reveal");

        game.loaded = false;
        game.handler.clearTrans();
        sound.play();
        new BG(this.game, unlockingChar + " reveal", false, false);

        if (unlockingChar == 1) {
            game.sChar1 = true;
        } else if (unlockingChar == 2) {
            game.sChar2 = true;
        }

        game.loaded = true;

        game.addMouseListener(this);
    }

    public void mousePressed(MouseEvent e) {
        int key = e.getButton();

        if (key == MouseEvent.BUTTON1) {
            this.game.removeMouseListener(this);
            try {
                game.writeStats();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            game.handler.clearTrans();
            game.handler.addObject(new SceneTransition(SCENE.MainMenu, game));
        }
    }

    public void mouseExited(MouseEvent e) {
        game.mouse.x = -100;
        game.mouse.y = -100;
    }
}
