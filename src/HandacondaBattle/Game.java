package HandacondaBattle;

import HandacondaBattle.Scenes.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Random;

public class Game extends Canvas implements Runnable {

    public static final int width = 815, height = (int) (width / 12 * 9.5);

    private Thread thread;
    private boolean running = false;

    public Handler handler;
    public Random random;
    public Soundtrack soundtrack = new Soundtrack("snif city", "origami king boss", "stabby stabby souls", this);

    public SCENE scene;
    public SCENE prevScene;
    public boolean loaded = false;

    public MouseListener mouse;

    public Window window = new Window(width, height, "Super Roshambo", this);

    public Battle battle;

    public String playerChar;
    public String opponentChar;
    public String difficulty;

    public int games = 0;
    public int gamesWon = 0;
    public int gamesLost = 0;
    public int easyTimes = 0;
    public int normalTimes = 0;
    public int hardTimes = 0;

    public int marioTimes = 0;
    public int luigiTimes = 0;
    public int fawfulTimes = 0;
    public int toadetteTimes = 0;
    public int sansTimes = 0;
    public int shyGuyTimes = 0;
    public int kirbyTimes = 0;
    public int shroobTimes = 0;

    public long playTime = 0;

    public boolean sChar1 = false;
    public boolean sChar2 = false;

    public SCENE getID() {
        return scene;
    }

    public Game() throws IOException, LineUnavailableException, UnsupportedAudioFileException, FontFormatException {
        handler = new Handler(this);
        random = new Random();

        handler.addObject(soundtrack);
        mouse = new MouseListener(this);
        SceneTransition t = new SceneTransition(SCENE.MainMenu, this);
        t.setX(-60);
        handler.addObject(t);
        soundtrack.play("title");

        try {
            loadStats();
        } catch (FileNotFoundException e) {
            Path path = Path.of(System.getProperty("user.home"));
            File file = new File(path + "/Super Roshambo");
            file.mkdirs();
            file = new File(path + "/Super Roshambo/stats.ini");
            file.createNewFile();
            writeStats();
        }
    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            while (delta >= 1 && delta <= amountOfTicks) {
                try {
                    tick();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                delta--;
            }

            while (delta >= 1) {
                delta --;
            }

            if (running) {
                render();
            }

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
            }
        }

        stop();
    }

    private void tick() throws IOException, FontFormatException, LineUnavailableException, UnsupportedAudioFileException {
        playTime += 1;
        if (scene != prevScene) {
            prevScene = scene;
            handler.clearAll();

            if (scene == SCENE.MainMenu) {
                new MainMenu(this, handler, SCENE.MainMenu);
            } else if (scene == SCENE.CharSelect) {
                new CharSelect(this, handler, SCENE.CharSelect);
            } else if (scene == SCENE.Stats) {
                new StatsScene(this, handler);
            } else if (scene == SCENE.Game) {
                do {
                    int num = random.nextInt(6);

                    if (num == 1) {
                        opponentChar = "Mario";
                    } else if (num == 2) {
                        opponentChar = "Luigi";
                    } else if (num == 3) {
                        opponentChar = "Fawful";
                    } else if (num == 4) {
                        opponentChar = "Shy Guy";
                    } else if (num == 5) {
                        opponentChar = "Sans";
                    } else {
                        opponentChar = "Toadette";
                    }
                } while (opponentChar.equalsIgnoreCase(playerChar));

                battle = new Battle(this, handler, SCENE.Game);
            }
        }

        handler.tick();
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.black);

        g.fillRect(0, 0, width, height);
        handler.render(g);

        g.dispose();
        bs.show();
    }

    public void loadStats() throws IOException, NumberFormatException {
        Path path = Path.of(System.getProperty("user.home"));
        File file = new File(path + "/Super Roshambo/stats.ini");

        BufferedReader br = new BufferedReader(new FileReader(file));

        games = Integer.parseInt(br.readLine());
        gamesWon = Integer.parseInt(br.readLine());
        gamesLost = Integer.parseInt(br.readLine());

        easyTimes = Integer.parseInt(br.readLine());
        normalTimes = Integer.parseInt(br.readLine());
        hardTimes = Integer.parseInt(br.readLine());

        marioTimes = Integer.parseInt(br.readLine());
        luigiTimes = Integer.parseInt(br.readLine());
        fawfulTimes = Integer.parseInt(br.readLine());
        toadetteTimes = Integer.parseInt(br.readLine());
        sansTimes = Integer.parseInt(br.readLine());
        shyGuyTimes = Integer.parseInt(br.readLine());
        kirbyTimes = Integer.parseInt(br.readLine());
        shroobTimes = Integer.parseInt(br.readLine());

        playTime = Integer.parseInt(br.readLine());

        sChar1 = Boolean.parseBoolean(br.readLine());
        sChar2 = Boolean.parseBoolean(br.readLine());

        br.close();
    }

    public void writeStats() throws IOException, NumberFormatException {
        Path path = Path.of(System.getProperty("user.home"));
        File file = new File(path + "/Super Roshambo/stats.ini");

        FileWriter writer = new FileWriter(file);

        writer.write(games + "\n");
        writer.write(gamesWon + "\n");
        writer.write(gamesLost + "\n");

        writer.write(easyTimes + "\n");
        writer.write(normalTimes + "\n");
        writer.write(hardTimes + "\n");

        writer.write(marioTimes + "\n");
        writer.write(luigiTimes + "\n");
        writer.write(fawfulTimes + "\n");
        writer.write(toadetteTimes + "\n");
        writer.write(sansTimes + "\n");
        writer.write(shyGuyTimes + "\n");
        writer.write(kirbyTimes + "\n");
        writer.write(shroobTimes + "\n");

        writer.write(playTime + "\n");

        writer.write(sChar1 + "\n");
        writer.write(sChar2 + "");

        writer.close();
    }

    public static void main(String[] args) throws IOException, LineUnavailableException, UnsupportedAudioFileException, FontFormatException {
        new Game();
    }
}
