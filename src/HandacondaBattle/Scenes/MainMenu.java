package HandacondaBattle.Scenes;

import HandacondaBattle.Button;
import HandacondaBattle.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class MainMenu extends GameScene {
    TitleScreenChars luigi;
    TitleScreenChars mario;
    TitleScreenChars shyGuy;
    TitleScreenChars fawful;
    TitleScreenChars sans;
    TitleScreenChars toadette;
    TitleScreenChars shroob;
    TitleScreenChars kirby;

    Button titleScreen;
    Button playButton;
    Button stats;

    public MainMenu(Game game, Handler handler, SCENE scene) throws IOException, FontFormatException {
        super(game, handler, scene);

        game.loaded = false;
        if (game.soundtrack.getsongPlaying() == null) {
            game.soundtrack.play("title");
        }

        new BG(this.game, "Title Screen bg", false, false);

        if (game.sChar1 && game.sChar2) {
            luigi = new TitleScreenChars(((game.getWidth() + 210) / 7), game.getHeight() / 2, 2, ID.SLIDE, game, "luigi/title screen", 60, 180, true);
            mario = new TitleScreenChars(((game.getWidth() + 210) / 7) * 2, game.getHeight() / 2, 2, ID.SLIDE, game, "mario/title screen", 60, 180, true);
            shyGuy = new TitleScreenChars(((game.getWidth() + 210) / 7) * 3, game.getHeight() / 2, 2, ID.SLIDE, game, "shy guy/title screen", 60, 180, true);
            fawful = new TitleScreenChars((((game.getWidth() + 210) / 7) * 4), game.getHeight() / 2, 2, ID.SLIDE, game, "fawful/title screen", 60, 180, true);
            shroob = new TitleScreenChars((((game.getWidth() + 210) / 7) * 6), game.getHeight() / 2, 2, ID.SLIDE, game, "shroob/title screen", 60, 180, true);
            toadette = new TitleScreenChars((((game.getWidth() + 210) / 7) * 5), game.getHeight() / 2, 2, ID.SLIDE, game, "toadette/title screen", 60, 180, true);
            kirby = new TitleScreenChars(0, game.getHeight() / 2, 2, ID.SLIDE, game, "kirby/title screen", 70, 180, true);
            sans = new TitleScreenChars((((game.getWidth() + 210) / 7) * 7), game.getHeight() / 2, 2, ID.SLIDE, game, "sans/title screen", 60, 180, true);
        } else if (game.sChar1) {
            luigi = new TitleScreenChars(((game.getWidth() + 180) / 6), game.getHeight() / 2, 2, ID.SLIDE, game, "luigi/title screen", 60, 180, true);
            mario = new TitleScreenChars(((game.getWidth() + 180) / 6) * 2, game.getHeight() / 2, 2, ID.SLIDE, game, "mario/title screen", 60, 180, true);
            shyGuy = new TitleScreenChars(((game.getWidth() + 180) / 6) * 3, game.getHeight() / 2, 2, ID.SLIDE, game, "shy guy/title screen", 60, 180, true);
            fawful = new TitleScreenChars((((game.getWidth() + 180) / 6) * 4), game.getHeight() / 2, 2, ID.SLIDE, game, "fawful/title screen", 60, 180, true);
            shroob = new TitleScreenChars((((game.getWidth() + 180) / 6) * 6), game.getHeight() / 2, 2, ID.SLIDE, game, "shroob/title screen", 60, 180, true);
            toadette = new TitleScreenChars((((game.getWidth() + 180) / 6) * 5), game.getHeight() / 2, 2, ID.SLIDE, game, "toadette/title screen", 60, 180, true);
            kirby = new TitleScreenChars(0, game.getHeight() / 2, 2, ID.SLIDE, game, "kirby/title screen", 70, 180, true);
            sans = new TitleScreenChars(0, -500, 2, ID.SLIDE, game, "kirby/title screen", 70, 180, true);
        } else if (game.sChar2) {
            luigi = new TitleScreenChars(((game.getWidth() + 180) / 6), game.getHeight() / 2, 2, ID.SLIDE, game, "luigi/title screen", 60, 180, true);
            mario = new TitleScreenChars(((game.getWidth() + 180) / 6) * 2, game.getHeight() / 2, 2, ID.SLIDE, game, "mario/title screen", 60, 180, true);
            shyGuy = new TitleScreenChars(((game.getWidth() + 180) / 6) * 3, game.getHeight() / 2, 2, ID.SLIDE, game, "shy guy/title screen", 60, 180, true);
            fawful = new TitleScreenChars((((game.getWidth() + 180) / 6) * 4), game.getHeight() / 2, 2, ID.SLIDE, game, "fawful/title screen", 60, 180, true);
            shroob = new TitleScreenChars((((game.getWidth() + 180) / 6) * 6), game.getHeight() / 2, 2, ID.SLIDE, game, "shroob/title screen", 60, 180, true);
            toadette = new TitleScreenChars((((game.getWidth() + 180) / 6) * 5), game.getHeight() / 2, 2, ID.SLIDE, game, "toadette/title screen", 60, 180, true);
            sans = new TitleScreenChars(0, game.getHeight() / 2, 2, ID.SLIDE, game, "sans/title screen", 60, 180, true);
            kirby = new TitleScreenChars(0, -500, 2, ID.SLIDE, game, "kirby/title screen", 70, 180, true);
        } else {
            luigi = new TitleScreenChars(((game.getWidth() + 150) / 5), game.getHeight() / 2, 2, ID.SLIDE, game, "luigi/title screen", 60, 150, true);
            mario = new TitleScreenChars(((game.getWidth() + 150) / 5) * 2, game.getHeight() / 2, 2, ID.SLIDE, game, "mario/title screen", 60, 150, true);
            shyGuy = new TitleScreenChars(((game.getWidth() + 150) / 5) * 3, game.getHeight() / 2, 2, ID.SLIDE, game, "shy guy/title screen", 60, 150, true);
            fawful = new TitleScreenChars((((game.getWidth() + 150) / 5) * 4), game.getHeight() / 2, 2, ID.SLIDE, game, "fawful/title screen", 60, 150, true);
            shroob = new TitleScreenChars(0, game.getHeight() / 2, 2, ID.SLIDE, game, "shroob/title screen", 60, 150, true);
            toadette = new TitleScreenChars((((game.getWidth() + 150) / 5) * 5), game.getHeight() / 2, 2, ID.SLIDE, game, "toadette/title screen", 60, 150, true);
            kirby = new TitleScreenChars(0, -500, 2, ID.SLIDE, game, "kirby/title screen", 70, 180, true);
            sans = new TitleScreenChars(0, -500, 2, ID.SLIDE, game, "kirby/title screen", 70, 180, true);
        }

        titleScreen = new Button(game.getWidth() / 2, 75, 0, ID.BUTTON, game,"Title Screen", false);
        playButton = new Button(game.getWidth() / 4, game.getHeight() - 125, 0, ID.BUTTON, game,"Play Game", true);
        stats = new Button((game.getWidth() / 4) * 3, game.getHeight() - 125, 0, ID.BUTTON, game,"View Stats", true);

        game.loaded = true;
        game.addMouseListener(this);
    }

    public void mousePressed(MouseEvent e) {
        int key = e.getButton();
        int x = e.getX();
        int y = e.getY();

        if (key == MouseEvent.BUTTON1) {
            if (playButton.mouseOver(x, y)) {
                this.game.removeMouseListener(this);
                game.handler.clearTrans();
                game.handler.addObject(new SceneTransition(SCENE.CharSelect, game));
            } else if (titleScreen.mouseOver(x, y)) {
                if (!game.soundtrack.getsongPlaying().equalsIgnoreCase("secret")) {
                    game.soundtrack.play("secret");
                } else {
                    game.soundtrack.play("title");
                }
            } else if (mario.mouseOver(x, y)) {
                if (!game.soundtrack.getsongPlaying().equalsIgnoreCase("mario")) {
                    game.soundtrack.play("mario");
                } else {
                    game.soundtrack.play("title");
                }
            } else if (luigi.mouseOver(x, y)) {
                if (!game.soundtrack.getsongPlaying().equalsIgnoreCase("luigi")) {
                    game.soundtrack.play("luigi");
                } else {
                    game.soundtrack.play("title");
                }
            } else if (shyGuy.mouseOver(x, y)) {
                if (!game.soundtrack.getsongPlaying().equalsIgnoreCase("shy guy")) {
                    game.soundtrack.play("shy guy");
                } else {
                    game.soundtrack.play("title");
                }
            } else if (fawful.mouseOver(x, y)) {
                if (!game.soundtrack.getsongPlaying().equalsIgnoreCase("fawful")) {
                    game.soundtrack.play("fawful");
                } else {
                    game.soundtrack.play("title");
                }
            } else if (sans.mouseOver(x, y)) {
                if (!game.soundtrack.getsongPlaying().equalsIgnoreCase("sans")) {
                    game.soundtrack.play("sans");
                } else {
                    game.soundtrack.play("title");
                }
            } else if (toadette.mouseOver(x, y)) {
                if (!game.soundtrack.getsongPlaying().equalsIgnoreCase("toadette")) {
                    game.soundtrack.play("toadette");
                } else {
                    game.soundtrack.play("title");
                }
            } else if (stats.mouseOver(x, y)) {
                this.game.removeMouseListener(this);
                game.handler.clearTrans();
                game.handler.addObject(new SceneTransition(SCENE.Stats, game));
            } else if (kirby.mouseOver(x, y)) {
                if (!game.soundtrack.getsongPlaying().equalsIgnoreCase("kirby")) {
                    game.soundtrack.play("kirby");
                } else {
                    game.soundtrack.play("title");
                }
            } else if (shroob.mouseOver(x, y)) {
                if (!game.soundtrack.getsongPlaying().equalsIgnoreCase("shroob")) {
                    game.soundtrack.play("shroob");
                } else {
                    game.soundtrack.play("title");
                }
            }
        }
    }

    public void mouseExited(MouseEvent e) {
        game.mouse.x = -100;
        game.mouse.y = -100;
    }
}
