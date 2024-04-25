package Controller.Item;

import Controller.Character.PlayerController;
import Model.Item;

import java.awt.*;
import java.text.DecimalFormat;

public class ItemGhostController {
    private double ghostCoolDown = 30 * 70; // Cooldown
    private double ghostCoolDownCurr; // Counter cooldown
    private double ghostTimeUse = 9 * 70; // Time using
    private double ghostTimeUseCurr = -1; // Counter time using
    private boolean timeOut = true;
    private PlayerController player;
    private Item item;

    public ItemGhostController(PlayerController player) {
        this.player = player;
        this.item = new Item();
        this.ghostCoolDownCurr = 0;
    }

    public double getGhostCoolDown() {
        return ghostCoolDown;
    }

    public void setGhostCoolDown(double ghostCoolDown) {
        this.ghostCoolDown = ghostCoolDown;
    }

    public double getGhostCoolDownCurr() {
        return ghostCoolDownCurr;
    }

    public void setGhostCoolDownCurr(double ghostCoolDownCurr) {
        this.ghostCoolDownCurr = ghostCoolDownCurr;
    }

    public double getGhostTimeUse() {
        return ghostTimeUse;
    }

    public void setGhostTimeUse(double ghostTimeUse) {
        this.ghostTimeUse = ghostTimeUse;
    }

    public double getGhostTimeUseCurr() {
        return ghostTimeUseCurr;
    }

    public void setGhostTimeUseCurr(double ghostTimeUseCurr) {
        this.ghostTimeUseCurr = ghostTimeUseCurr;
    }

    public boolean isTimeOut() {
        return timeOut;
    }

    public void setTimeOut(boolean timeOut) {
        this.timeOut = timeOut;
    }

    public boolean isAvailable() {
        return ghostCoolDownCurr <= 0;
    }

    // When player use setup time and start countdown
    public void use() {
        if (isAvailable() && player.isUsed) {
            ghostCoolDownCurr = ghostCoolDown;
            ghostTimeUseCurr = ghostTimeUse;
        }
    }

    // Countdown
    public void countDown() {
        if (ghostTimeUseCurr > 0) {
            ghostTimeUseCurr -= 2;
            timeOut = false;
        } else if (ghostTimeUseCurr < 0) {
            timeOut = true;
        }
        ghostCoolDownCurr -= 2;
//        System.out.println("Ghost: " + ghostTimeUseCurr + " " + ghostCoolDownCurr);
    }

    public void draw(Graphics2D g2) {
        int frameX = player.gp.tileSize * 9 - 12;
        int frameY = player.gp.tileSize * 11 - 12;
        int frameWidth = player.gp.tileSize;
        int frameHeight = player.gp.tileSize;

        String time = new DecimalFormat("#00").format(ghostCoolDownCurr / 100);
        int itemFrameY = player.gp.tileSize / 2 + 6;
        int itemFrameX = player.gp.tileSize / 2 - 12;
        Font font = new Font("Brick Sans", Font.BOLD, 20);

        g2.drawImage(item.getGhostImg(), frameX, frameY, frameWidth, frameHeight, null);

        if (ghostCoolDownCurr > 0) {
            Color c = new Color(0, 0, 0, 100);
            g2.setColor(c);
            g2.fillRect(frameX, frameY, frameWidth, frameHeight);

            c = new Color(255, 255, 255);
            g2.setColor(c);
            g2.setFont(font);
            g2.drawString("" + time, frameX + itemFrameX, frameY + itemFrameY);
        }
    }
}
