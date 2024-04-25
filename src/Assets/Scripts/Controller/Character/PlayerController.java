package Controller.Character;

import Controller.Event.KeyHandler;
import Controller.Item.ItemFlashController;
import Controller.Item.ItemGhostController;
import Model.Player;
import View.GamePanel;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class PlayerController {
    public Player player;
    public GamePanel gp;
    // Item
    public boolean isUsed;
    public ItemFlashController flash = new ItemFlashController(this);
    public ItemGhostController ghost = new ItemGhostController(this);
    KeyHandler keyH;

    public PlayerController(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        this.player = new Player();
//        this.player.getPlayerImage("Character_05");
        this.setDefaultValues();
    }

    // Default value
    public void setDefaultValues() {
        player.posX = gp.screenWidth / 2;
        player.posY = gp.screenHeight / 2;
        player.speed = 5;
        player.direction = "down";
        isUsed = false;

        double playerRecPosX = player.posX + 12;
        double playerRecPosY = player.posY + 20;
        player.hitbox = new Rectangle2D.Double(playerRecPosX, playerRecPosY, player.recW - 8, player.recH - 8);
    }

    // Update is called 60 per frame
    public void update() {
        useItem(); // Check if player use item
        countDown(); // Countdown time using item

        // Check player movement
        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
            // Movement
            if (keyH.upPressed) {
                if (keyH.leftPressed) {
                    player.direction = "left";
                    player.moveUpLeft();
                } else if (keyH.rightPressed) {
                    player.direction = "right";
                    player.moveUpRight();
                } else {
                    player.direction = "up";
                    player.moveUp();
                }
            } else if (keyH.downPressed) {
                if (keyH.leftPressed) {
                    player.direction = "left";
                    player.moveDownLeft();
                } else if (keyH.rightPressed) {
                    player.direction = "right";
                    player.moveDownRight();
                } else {
                    player.direction = "down";
                    player.moveDown();
                }
            } else if (keyH.leftPressed) {
                player.direction = "left";
                player.moveLeft();
            } else {
                player.direction = "right";
                player.moveRight();
            }

            // Animations
            player.setAnimations();
        }
    }

    // Render image on game view
    public void draw(Graphics2D g2) {
        // Handle direction property
        BufferedImage image = player.drawAnimation();

        double tmpPosX = player.posX + 8;
        double tmpPosY = player.posY + 28;
        double shadowScaleX = (gp.tileSize / player.shadow.getWidth()) * 0.75;
        double shadowScaleY = (gp.tileSize / player.shadow.getHeight()) * 0.75;

        // Draw player's image
        AffineTransform at = AffineTransform.getTranslateInstance(tmpPosX, tmpPosY);
        at.scale(shadowScaleX, shadowScaleY);
        g2.drawImage(player.shadow, at, null);

        at = AffineTransform.getTranslateInstance(player.posX, player.posY);
        g2.drawImage(image, at, null);

        // Draw Item Image
        flash.draw(g2);
        ghost.draw(g2);
    }

    // Player use item
    public void useItem() {
        isUsed = true;
        // Use first item
        if (keyH.fistSkillPressed && flash.isAvailable()) {
            flash.use();
            switch (player.direction) {
                case "up" -> player.posY = player.posY - gp.tileSize * 2;
                case "down" -> player.posY = player.posY + gp.tileSize * 2;
                case "left" -> player.posX = player.posX - gp.tileSize * 2;
                case "right" -> player.posX = player.posX + gp.tileSize * 2;
            }
            System.out.println("Player use Flash!");
        }
        // Use second item
        if (keyH.secondSkillPressed && ghost.isAvailable()) {
            double buff = 1.2;
            ghost.use();
            player.speed = player.speed * buff;
            System.out.println("Player use Ghost! Speed is " + player.speed);
        }
    }

    // Countdown time when timeout using item
    public void countDown() {
        flash.countDown();
        ghost.countDown();
        if (ghost.getGhostTimeUseCurr() <= 0 && !ghost.isTimeOut()) {
            player.speed = player.speed / 1.2;
            ghost.setTimeOut(true);
            System.out.println("Timeout Ghost! Speed is " + player.speed);
        }
    }
}
