package HandacondaBattle.Scenes;

import HandacondaBattle.*;

import java.awt.event.MouseEvent;
import java.io.IOException;

// The Character select screen
public class CharSelect extends GameScene {
    Button random;
    Button mario;
    Button luigi;
    Button fawful;
    Button toadette;
    Button sans;
    Button shroob;
    Button kirby;
    Button shyGuy;

    Button back;

    Button easy;
    Button normal;
    Button hard;

    Button start;

    Button playerChar = null;
    Button difficulty = null;

    public CharSelect(Game game, Handler handler, SCENE scene) throws IOException {
        super(game, handler, scene);
        Sound snd = new Sound("choosefighter");
        System.out.println(snd.clip);

        game.loaded = false;



        // Loads all of the objects into the scene
        new BG(this.game, "charselect bg", true, true);

        random =   new Button(400, 198, 0, ID.BUTTON, this.game, "charselect/Random", true);

        mario =    new Button(185, 67, 0, ID.BUTTON, this.game, "charselect/Mario", true);
        luigi =    new Button(400, 67, 0, ID.BUTTON, this.game, "charselect/Luigi", true);
        fawful =   new Button(615, 67, 0, ID.BUTTON, this.game, "charselect/Fawful", true);

        toadette = new Button(185, 330, 0, ID.BUTTON, this.game, "charselect/Toadette", true);
        shroob =   new Button(400, 330, 0, ID.BUTTON, this.game, "charselect/Shroob", true);
        shyGuy =   new Button(615, 330, 0, ID.BUTTON, this.game, "charselect/Shy Guy", true);

        if (game.sChar2) {
            sans = new Button(185, 198, 0, ID.BUTTON, this.game, "charselect/Sans", true);
        } else {
            sans = new Button(185, -500, 0, ID.BUTTON, this.game, "charselect/Sans", true);
        }

        if (game.sChar1) {
            kirby = new Button(615, 198, 0, ID.BUTTON, this.game, "charselect/Kirby", true);
        } else {
            kirby = new Button(185, -500, 0, ID.BUTTON, this.game, "charselect/Kirby", true);
        }

        back =     new Button(127, 565, 0, ID.BUTTON, this.game, "charselect/Back", true);

        easy = new Button(616, 425, 0, ID.BUTTON, this.game, "charselect/Easy", true);
        normal = new Button(616, 491, 0, ID.BUTTON, this.game, "charselect/Normal", true);
        hard = new Button(616, 557, 0, ID.BUTTON, this.game, "charselect/Hard", true);

        start = new Button(-125, 443, 10, ID.BUTTON, this.game, "charselect/begin", true);

        this.game.addMouseListener(this);
        game.loaded = true;
        snd.play();
    }

    // Checks when the mouse is clicked
    public void mousePressed(MouseEvent e) {
        int key = e.getButton();
        int x = e.getX();
        int y = e.getY();

        if (key == MouseEvent.BUTTON1) {
            if (playerChar != null) {
                playerChar.selected = false;
            }

            if (difficulty != null) {
                difficulty.selected = false;
            }

            if (back.mouseOver(x, y)) {
                this.game.removeMouseListener(this);
                game.handler.addObject(new SceneTransition(SCENE.MainMenu, game));
            } else if (mario.mouseOver(x, y)) {
                playerChar = mario;
            } else if (luigi.mouseOver(x, y)) {
                playerChar = luigi;
            } else if (fawful.mouseOver(x, y)) {
                playerChar = fawful;
            } else if (toadette.mouseOver(x, y)) {
                playerChar = toadette;
            } else if (sans.mouseOver(x, y)) {
                playerChar = sans;
            } else if (shyGuy.mouseOver(x, y)) {
                playerChar = shyGuy;
            } else if (kirby.mouseOver(x, y)) {
                playerChar = kirby;
            } else if (shroob.mouseOver(x, y)) {
                playerChar = shroob;
            } else if (random.mouseOver(x, y)) {
                playerChar = random;
            } else if (easy.mouseOver(x, y)) {
                difficulty = easy;
            } else if (normal.mouseOver(x, y)) {
                difficulty = normal;
            } else if (hard.mouseOver(x, y)) {
                difficulty = hard;
            } else if (start != null) {
                if (start.mouseOver(x, y)) {
                    game.difficulty = difficulty.name;

                    if (playerChar.name.equalsIgnoreCase("random")) {
                        boolean chosen = false;
                        while (!chosen) {
                            int num = game.random.nextInt(8);

                            if (num == 1) {
                                game.playerChar = "Mario";
                                game.marioTimes += 1;
                                chosen = true;
                            } else if (num == 2) {
                                game.playerChar = "Luigi";
                                game.luigiTimes += 1;
                                chosen = true;
                            } else if (num == 3) {
                                game.playerChar = "Fawful";
                                game.fawfulTimes += 1;
                                chosen = true;
                            } else if (num == 4) {
                                game.playerChar = "Shy Guy";
                                game.shyGuyTimes += 1;
                                chosen = true;
                            } else if (num == 5 && game.sChar2) {
                                game.playerChar = "Sans";
                                game.sansTimes += 1;
                                chosen = true;
                            } else if (num == 6 && game.sChar1) {
                                game.playerChar = "Kirby";
                                game.sansTimes += 1;
                                chosen = true;
                            } else if (num == 7) {
                                game.playerChar = "Shroob";
                                game.sansTimes += 1;
                                chosen = true;
                            } else if (num == 0) {
                                game.playerChar = "Toadette";
                                game.toadetteTimes += 1;
                                chosen = true;
                            }
                        }
                    } else {
                        game.playerChar = playerChar.name;
                    }

                    if (playerChar.name.equalsIgnoreCase("mario")) {
                        game.marioTimes += 1;
                    } else if (playerChar.name.equalsIgnoreCase("luigi")) {
                        game.luigiTimes += 1;
                    } else if (playerChar.name.equalsIgnoreCase("fawful")) {
                        game.fawfulTimes += 1;
                    } else if (playerChar.name.equalsIgnoreCase("shy guy")) {
                        game.shyGuyTimes += 1;
                    } else if (playerChar.name.equalsIgnoreCase("sans")) {
                        game.sansTimes += 1;
                    } else if (playerChar.name.equalsIgnoreCase("toadette")) {
                        game.toadetteTimes += 1;
                    } else if (playerChar.name.equalsIgnoreCase("kirby")) {
                        game.kirbyTimes += 1;
                    } else if (playerChar.name.equalsIgnoreCase("shroob")) {
                        game.shroobTimes += 1;
                    }

                    if (difficulty.name.equalsIgnoreCase("easy")) {
                        game.easyTimes += 1;
                    } else if (difficulty.name.equalsIgnoreCase("normal")) {
                        game.normalTimes += 1;
                    } else if (difficulty.name.equalsIgnoreCase("hard")) {
                        game.hardTimes += 1;
                    }

                    game.games += 1;

                    this.game.removeMouseListener(this);
                    game.soundtrack.fadeOutAll();
                    game.handler.addObject(new SceneTransition(SCENE.Game, game));
                }
            }
        }

        if (playerChar != null) {
            playerChar.selected = true;
        }

        if (difficulty != null) {
            difficulty.selected = true;
        }

        if (playerChar != null && difficulty != null) {
            assert start != null;
            start.goToPoint(189, 443);
        }
    }

    public void mouseExited(MouseEvent e) {
        game.mouse.x = -100;
        game.mouse.y = -100;
    }
}
