package HandacondaBattle.Scenes;

import HandacondaBattle.Game;
import HandacondaBattle.Handler;
import HandacondaBattle.SCENE;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public abstract class GameScene extends MouseAdapter {

    protected Game game;
    protected SCENE scene;
    protected Handler handler;

    public GameScene(Game game, Handler handler, SCENE scene) {
        this.game = game;
        this.scene = scene;
        this.handler = handler;
    }

    public abstract void mousePressed(MouseEvent e);

    public void mouseMoved(MouseEvent e) {
        System.out.println(e.getX() + ", " + e.getY());
    }
}
