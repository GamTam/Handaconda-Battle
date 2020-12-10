package HandacondaBattle;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.LinkedList;

public class BattleChars extends GameObject{

    private LinkedList<BufferedImage> idle = new LinkedList<>();
    private LinkedList<BufferedImage> dead = new LinkedList<>();
    private LinkedList<BufferedImage> win = new LinkedList<>();
    private BufferedImage hit;

    private BufferedImage activeSprite;

    public PlayerState state = PlayerState.Idle;

    public int currentFrame = 0;
    private long lastUpdate = 0;
    private int animSpeed = 60;

    public int hp;
    private int maxHP;

    private int rectWidth = 100;
    private int rectHeight = 25;

    private int timer = 30;

    private int scale = 1;

    public BattleChars(Game game, String skin, boolean player) throws IOException {
        super(0, 404, ID.BATTLECHAR, game);

        int i = 1;
        String fileName;
        ClassLoader classLoader;
        InputStream inputStream;

        skin = skin.toLowerCase();

        if (player) {
            x = 75;
            maxHP = 10;
            try {
                while (true) {
                    fileName = "sprites/" + skin + "/battle/idle/" + i + ".png";

                    classLoader = BG.class.getClassLoader();
                    inputStream = classLoader.getResourceAsStream(fileName);
                    idle.add(ImageIO.read(inputStream));
                    i += 1;
                }
            } catch(Exception e) {
                // No u
            }

            i = 1;
            try {
                while (true) {
                    fileName = "sprites/" + skin + "/battle/dead/" + i + ".png";

                    classLoader = BG.class.getClassLoader();
                    inputStream = classLoader.getResourceAsStream(fileName);
                    dead.add(ImageIO.read(inputStream));
                    i += 1;
                }
            } catch(Exception e) {
                // No u
            }

            i = 1;
            try {
                while (true) {
                    fileName = "sprites/" + skin + "/battle/win/" + i + ".png";

                    classLoader = BG.class.getClassLoader();
                    inputStream = classLoader.getResourceAsStream(fileName);
                    win.add(ImageIO.read(inputStream));
                    i += 1;
                }
            } catch(Exception e) {
                // No u
            }

            fileName = "sprites/" + skin + "/battle/hit.png";

            classLoader = BG.class.getClassLoader();
            inputStream = classLoader.getResourceAsStream(fileName);

            hit = ImageIO.read(inputStream);
        } else {
            x = game.getWidth() - 75;
            try {
                i = 1;
                try {
                    while (true) {
                        fileName = "sprites/" + skin + "/enemy/idle/" + i + ".png";

                        classLoader = BG.class.getClassLoader();
                        inputStream = classLoader.getResourceAsStream(fileName);
                        idle.add(ImageIO.read(inputStream));
                        i += 1;
                    }
                } catch(Exception e) {
                    // No u
                }

                i = 1;
                try {
                    while (true) {
                        fileName = "sprites/" + skin + "/enemy/dead/" + i + ".png";

                        classLoader = BG.class.getClassLoader();
                        inputStream = classLoader.getResourceAsStream(fileName);
                        dead.add(ImageIO.read(inputStream));
                        i += 1;
                    }
                } catch(Exception e) {
                    // No u
                }

                i = 1;
                try {
                    while (true) {
                        fileName = "sprites/" + skin + "/enemy/win/" + i + ".png";

                        classLoader = BG.class.getClassLoader();
                        inputStream = classLoader.getResourceAsStream(fileName);
                        win.add(ImageIO.read(inputStream));
                        i += 1;
                    }
                } catch(Exception e) {
                    // No u
                }

                fileName = "sprites/" + skin + "/enemy/hit.png";

                classLoader = BG.class.getClassLoader();
                inputStream = classLoader.getResourceAsStream(fileName);

                hit = ImageIO.read(inputStream);

            } catch (Exception f) {
                scale = -1;

                try {
                    while (true) {
                        fileName = "sprites/" + skin + "/battle/idle/" + i + ".png";

                        classLoader = BG.class.getClassLoader();
                        inputStream = classLoader.getResourceAsStream(fileName);
                        idle.add(ImageIO.read(inputStream));
                        i += 1;
                    }
                } catch(Exception e) {
                    // No u
                }

                i = 1;
                try {
                    while (true) {
                        fileName = "sprites/" + skin + "/battle/dead/" + i + ".png";

                        classLoader = BG.class.getClassLoader();
                        inputStream = classLoader.getResourceAsStream(fileName);
                        dead.add(ImageIO.read(inputStream));
                        i += 1;
                    }
                } catch(Exception e) {
                    // No u
                }

                i = 1;
                try {
                    while (true) {
                        fileName = "sprites/" + skin + "/battle/win/" + i + ".png";

                        classLoader = BG.class.getClassLoader();
                        inputStream = classLoader.getResourceAsStream(fileName);
                        win.add(ImageIO.read(inputStream));
                        i += 1;
                    }
                } catch(Exception e) {
                    // No u
                }

                fileName = "sprites/" + skin + "/battle/hit.png";

                classLoader = BG.class.getClassLoader();
                inputStream = classLoader.getResourceAsStream(fileName);

                hit = ImageIO.read(inputStream);
            }

            if (game.difficulty.equalsIgnoreCase("easy")) {
                maxHP = 5;
            } else if (game.difficulty.equalsIgnoreCase("normal")) {
                maxHP = 10;
            } else if (game.difficulty.equalsIgnoreCase("hard")) {
                maxHP = 20;
            }
        }

        hp = maxHP;

        activeSprite = idle.get(0);
        center();

        game.handler.addObject(this);
    }

    public  void center() {
        x = x - activeSprite.getWidth() / 2;
        y = y - activeSprite.getHeight();
    }

    public void tick() {
        if (state == PlayerState.Idle) {
            animate(idle, false);
            activeSprite = idle.get(currentFrame);
        } else if (state == PlayerState.Hit) {
            int tempX = x + activeSprite.getWidth() / 2;
            int tempY = y + activeSprite.getHeight();

            activeSprite = hit;

            x = tempX - activeSprite.getWidth() / 2;
            y = tempY - activeSprite.getHeight();

            timer--;
            if (timer == 0) {
                timer = 30;
                state = PlayerState.Idle;

                activeSprite = idle.get(currentFrame);

                x = tempX - idle.get(currentFrame).getWidth() / 2;
                y = tempY - idle.get(currentFrame).getHeight();
            }
        } else if (state == PlayerState.Dead) {
            if (!checkActive(dead, activeSprite)) {
                currentFrame = 0;
            }
            animate(dead, true);

            activeSprite = dead.get(currentFrame);
        } else if (state == PlayerState.Win) {
            if (!checkActive(win, activeSprite)) {
                currentFrame = 0;
            }

            animate(win, true);

            activeSprite = win.get(currentFrame);
        }

    }

    public boolean checkActive(LinkedList<BufferedImage> list, BufferedImage value) {
        for (BufferedImage element : list) {
            if (element == value) {
                return true;
            }
        }
        return false;
    }

    public void animate(LinkedList<BufferedImage> anim, boolean stop, int loopPoint) {
        long now = System.currentTimeMillis();
        int tempX = x + anim.get(currentFrame).getWidth() / 2;
        int tempY = y + anim.get(currentFrame).getHeight();

        if (now - lastUpdate > animSpeed) {
            lastUpdate = now;

            if (currentFrame < anim.size() - 1) {
                currentFrame += 1;
            } else if (!stop) {
                currentFrame = loopPoint;
            }

            x = tempX - anim.get(currentFrame).getWidth() / 2;
            y = tempY - anim.get(currentFrame).getHeight();
        }
    }

    public void animate(LinkedList<BufferedImage> anim, boolean stop) {
        animate(anim, stop, 0);
    }

    public int getCenterX() {
        return x + activeSprite.getWidth() / 2;
    }

    public int getBottomY() {
        return y + activeSprite.getHeight();
    }

    public void render(Graphics g) {
        int tempX = x;
        int tempY = y;

        if (scale < 0) {
            x += activeSprite.getWidth();
        }

        g.drawImage(activeSprite, x, y, activeSprite.getWidth() * scale, activeSprite.getHeight(), null);

        x = tempX;
        y = tempY;
    }

    public void renderFront(Graphics g) {
        if (state != PlayerState.Dead && state != PlayerState.Win) {
            int rx = getCenterX() - rectWidth / 2;
            int ry = getBottomY() + (int) (rectHeight * 0.75);

            g.setColor(Color.red);
            g.fillRect(rx, ry, rectWidth, rectHeight);

            g.setColor(Color.green);
            float hpScale = ((float) hp / (float) maxHP);
            g.fillRect(rx, ry, (int) (rectWidth * hpScale), rectHeight);

            g.setColor(Color.black);
            Graphics2D g2 = (Graphics2D) g;
            float thickness = 5;
            Stroke oldStroke = g2.getStroke();
            g2.setStroke(new BasicStroke(thickness));
            g2.drawRect(rx, ry, rectWidth, rectHeight);
            g2.setStroke(oldStroke);
        }
    }
}
