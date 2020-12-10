package HandacondaBattle;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class StatsText extends GameObject {

    Font font;
    int increaseAmount = 40;
    String displayTime = "00:00";

    public StatsText(Game game) throws IOException, FontFormatException {
        super(0, 0, ID.BUTTON, game);

        String fileName = "fonts/text.ttf";

        ClassLoader classLoader = BG.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        font = Font.createFont(Font.TRUETYPE_FONT, inputStream);
        font = font.deriveFont(40f);

        x = 40;
        y = 0;

        game.handler.addObject(this);
    }

    @Override
    public void tick() {
        long playSeconds = game.playTime / 60;
        long playMinutes = playSeconds / 60;
        long playHours = playMinutes / 60;

        String ss;
        String sm;
        String sh;

        if (playSeconds >= 60) {
            playSeconds %= 60;
        }

        if (playSeconds < 10) {
            ss = "0" + playSeconds;
        } else {
            ss = Long.toString(playSeconds);
        }

        if (playMinutes >= 60) {
            playMinutes %= 60;
        }

        if (playMinutes < 10) {
            sm = "0" + playMinutes;
        } else {
            sm = Long.toString(playMinutes);
        }

        if (playHours >= 60) {
            playHours %= 60;
        }

        if (playHours < 10) {
            sh = "0" + playHours;
        } else {
            sh = Long.toString(playHours);
        }

        displayTime = String.format("%s:%s:%s",sh,sm,ss);
    }

    @Override
    public void render(Graphics g) {
        int tempX = x;
        int tempY = y;

        g.setFont(font);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);

        y += increaseAmount;
        g.drawString("Games Played: " + game.games, x, y);
        g.drawString("Play Time: " + displayTime, game.getWidth() / 2, y);
        y += increaseAmount;
        g.drawString("Games Won: " + game.gamesWon, x, y);
        y += increaseAmount;
        g.drawString("Games Lost: " + game.gamesLost, x, y);
        y += increaseAmount;
        g.drawString("Games Abandoned: " + (game.games - (game.gamesWon + game.gamesLost)), x, y);
        y += increaseAmount;
        g.drawString("Easy Games: " + game.easyTimes, x, y);
        y += increaseAmount;
        g.drawString("Normal Games: " + game.normalTimes, x, y);
        y += increaseAmount;
        g.drawString("Hard Games: " + game.hardTimes, x, y);

        x = game.getWidth() / 2;
        y = game.getHeight() / 2;

        g.drawString("Mario Games: " + game.marioTimes, x, y);
        y += increaseAmount;
        g.drawString("Luigi Games: " + game.luigiTimes, x, y);
        y += increaseAmount;
        g.drawString("Fawful Games: " + game.fawfulTimes, x, y);
        y += increaseAmount;
        g.drawString("Toadette Games: " + game.toadetteTimes, x, y);
        y += increaseAmount;
        g.drawString("Sans Games: " + game.sansTimes, x, y);
        y += increaseAmount;
        g.drawString("Shy Guy Games: " + game.shyGuyTimes, x, y);

        x = tempX;
        y = tempY;
    }
}
