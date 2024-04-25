package Controller.Item;

import Controller.Character.PlayerController;
import Model.Item;

import java.awt.*;
import java.text.DecimalFormat;

public class ItemFlashController {
    private double flashCoolDown = 45 * 70; // Cooldown
    private double flashCoolDownCurr; // Counter cooldown
    private PlayerController player;
    private Item item;

    public ItemFlashController(PlayerController player) {
        this.player = player;
        this.item = new Item();
        this.flashCoolDownCurr = 0;
    }

    public boolean isAvailable() {
        return flashCoolDownCurr <= 0;
    }

    public double getFlashCoolDown() {
        return flashCoolDown;
    }

    public void setFlashCoolDown(double flashCoolDown) {
        this.flashCoolDown = flashCoolDown;
    }

    public double getFlashCoolDownCurr() {
        return flashCoolDownCurr;
    }

    public void setFlashCoolDownCurr(double flashCoolDownCurr) {
        this.flashCoolDownCurr = flashCoolDownCurr;
    }

    // When player use setup time and start countdown
    public void use() {
        if (isAvailable()) {
            flashCoolDownCurr = flashCoolDown;
        }
    }

    // Countdown
    public void countDown() {
        flashCoolDownCurr -= 2;
    }

    public void draw(Graphics2D g2) {
        int frameX = player.gp.tileSize * 7;
        int frameY = player.gp.tileSize * 11 - 12;
        int frameWidth = player.gp.tileSize;
        int frameHeight = player.gp.tileSize;

        String time = new DecimalFormat("#00").format(flashCoolDownCurr / 100);
        int length = (int) g2.getFontMetrics().getStringBounds(time, g2).getWidth();
        int height = (int) g2.getFontMetrics().getStringBounds(time, g2).getHeight();
        int itemFrameY = player.gp.tileSize / 2 + height / 2;
        int itemFrameX = player.gp.tileSize / 2 - length;
        Font font = new Font("Brick Sans", Font.BOLD, 20);

        g2.drawImage(item.getFlashImg(), frameX, frameY, frameWidth, frameHeight, null);
        if (flashCoolDownCurr > 0) {
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

