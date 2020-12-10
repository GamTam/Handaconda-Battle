package HandacondaBattle;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class Button extends GameObject {

    private BufferedImage sprite;
    private BufferedImage selectSprite;
    private BufferedImage activeSprite;

    double speed;
    boolean selectedFrame;

    public int xDest;
    public int yDest;

    double deltaX;
    double deltaY;
    double dir;

    public String name;

    public boolean selected = false;

    public Button(int x, int y, int speed, ID id, Game game, String image, boolean selectedFrame) throws IOException {
        super(x, y, id, game);
        xDest = this.x;
        yDest = this.y;
        this.speed = speed;
        this.selectedFrame = selectedFrame;
        this.name = image.replace("charselect/", "");
        if (selectedFrame) {
            String fileName = "sprites/" + image + " Unselected.png";

            ClassLoader classLoader = Button.class.getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream(fileName);
            InputStream bs = new BufferedInputStream(inputStream);
            sprite = ImageIO.read(bs);
            activeSprite = sprite;

            fileName = "sprites/" + image + " Selected.png";

            classLoader = BG.class.getClassLoader();
            inputStream = classLoader.getResourceAsStream(fileName);
            bs = new BufferedInputStream(inputStream);
            selectSprite = ImageIO.read(bs);
        } else {
            String fileName = "sprites/" + image + ".png";

            ClassLoader classLoader = BG.class.getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream(fileName);
            InputStream bs = new BufferedInputStream(inputStream);
            activeSprite = ImageIO.read(bs);
        }

        center();
        game.handler.addObject(this);
    }

    public void center() {
        x = x - activeSprite.getWidth() / 2;
        y = y - activeSprite.getHeight() / 2;
    }

    public  void unCenter() {
        x = x + activeSprite.getWidth() / 2;
        y = y + activeSprite.getHeight() / 2;
    }

    public void tick() {
        if (selectedFrame) {
            if (mouseOver(game.mouse.getX(), game.mouse.getY()) || selected) {
                activeSprite = selectSprite;
            } else {
                activeSprite = sprite;
            }
        }

        unCenter();

        if (!inRange(x, xDest)) {
            x = (int) (x + (speed * Math.cos(dir)));
        } else {
            x = xDest;
        }

        if (!inRange(y, yDest)) {
            y = (int) (y + (speed * Math.sin(dir)));
        } else {
            y = yDest;
        }

        center();
}

    public void goToPoint(int x, int y) {
            xDest = x;
            yDest = y;

            deltaX = xDest - this.x;
            deltaY = yDest - this.y;

            dir = Math.atan(deltaY / deltaX);
    }

    public boolean inRange(int current, int dest) {
        boolean t = false;

        if (current <= dest + speed && current >= dest - speed) {
            t = true;
        }

        return t;
    }


    public void render(Graphics g) {
        g.drawImage(activeSprite, x, y, activeSprite.getWidth(), activeSprite.getHeight(), null);
    }

    public boolean mouseOver(int x, int y) {
        return  x >= this.x && x <= this.x + getWidth() && y >= this.y && y <= this.y + getHeight();
    }

    public int getWidth() {
        return activeSprite.getWidth();
    }

    public int getHeight() {
        return activeSprite.getHeight();
    }
}
