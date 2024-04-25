package Controller.Tool;

import View.GamePanel;

import java.awt.*;
import java.io.Serializable;
import java.text.DecimalFormat;

public class TimeManager implements Serializable {
    private final DecimalFormat decimalFormat = new DecimalFormat("#0");
    GamePanel gp;
    private double timeToUpgrade = 5;
    private double countDown;
    private double timeCounterBall;
    private double timeCounterLaser;
    private double timeSpawnBall;
    private double timeSpawnLaser;
    // Handle Score
    private double playTime;
    private double highestScore;

    public TimeManager(GamePanel gp) {
        this.gp = gp;
        this.timeSpawnBall = 0.7;
        this.timeSpawnLaser = 5;
        this.timeCounterBall = 0;
        this.timeCounterLaser = 0;
        this.highestScore = 0;
    }

    public double getPlayTime() {
        return playTime * 100;
    }

    public double getHighestScore() {
        return highestScore;
    }

    public void setHighestScore(double score) {
        highestScore = Math.max(score, highestScore);
    }

    public boolean isTimeSpawnBall() {
        if (playTime - timeCounterBall >= timeSpawnBall) {
            timeCounterBall += timeSpawnBall;
            return true;
        }
        return false;
    }

    public boolean isTimeSpawnLaser() {
        if (playTime - timeCounterLaser >= timeSpawnLaser) {
            timeCounterLaser += timeSpawnLaser;
            return true;
        }
        return false;
    }

    public void update() {
        playTime += (double) 2 / 70;

        // Increase the hard mode
        if (playTime - countDown >= timeToUpgrade && timeSpawnBall > 0) {
            timeSpawnBall -= 0.1; // Decrease time spawn ball
            timeToUpgrade += 5; // Increase time to update
            countDown += timeToUpgrade;
        }
    }

    public void draw(Graphics2D g2) {
        g2.setFont(new Font("Brick Sans", Font.BOLD, 25));
        g2.setColor(Color.WHITE);

        String timePlay = decimalFormat.format(playTime * 100);
        int length = (int) g2.getFontMetrics().getStringBounds(timePlay, g2).getWidth();
        int x = gp.screenWidth / 2 - length / 2;
        g2.drawString(timePlay, x, 50);

        String highest = decimalFormat.format(highestScore);
        g2.drawString("Highest: " + highest, 10, 50);
    }
}
