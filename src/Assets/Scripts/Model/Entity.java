package Model;

import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Entity {
    public static double SQRT2 = Math.sqrt(2);
    public int recW = 30;
    public int recH = 30;

    // Position and movement
    public double posX, posY;
    public double speed;
    public String direction;

    // Default value for monster & projectile
    public double targetPosX, targetPosY; // Store target position
    public double startPosX, startPosY; // Store start position
    public int spawnSide; // Side where projectile will spawn
    public String state; // State that projectile is

    // Render image
    public BufferedImage up1, up2, up3, up4;
    public BufferedImage down1, down2, down3, down4;
    public BufferedImage left1, left2, left3, left4;
    public BufferedImage right1, right2, right3, right4;
    public BufferedImage shadow;

    // Handle animations
    public int spriteNum = 1;
    public int spriteCount = 0;

    // Collision
    public Rectangle2D.Double hitbox = new Rectangle2D.Double(posX, posY, recW, recH);

    public Entity() {
    }

    // BEGIN: Animation
    public void setAnimations() {
        spriteCount++;
        if (spriteCount > 13) {
            if (spriteNum == 1 || spriteNum == 5 || spriteNum == 9) {
                spriteNum = 2;
            } else if (spriteNum == 2 || spriteNum == 6 || spriteNum == 10) {
                spriteNum = 3;
            } else if (spriteNum == 3 || spriteNum == 7 || spriteNum == 11) {
                spriteNum = 4;
            } else if (spriteNum == 4 || spriteNum == 8 || spriteNum == 12) {
                spriteNum = 1;
            }
            spriteCount = 0;
        }
    }

    public BufferedImage drawAnimation() {
        BufferedImage image = null;

        switch (direction) {
            case "up":
            case "up_right":
            case "up_left":
                if (spriteNum == 1 || spriteNum == 5 || spriteNum == 9)
                    image = up1;
                if (spriteNum == 2 || spriteNum == 6 || spriteNum == 10)
                    image = up2;
                if (spriteNum == 3 || spriteNum == 7 || spriteNum == 11)
                    image = up3;
                if (spriteNum == 4 || spriteNum == 8 || spriteNum == 12)
                    image = up4;
                break;
            case "down":
            case "down_right":
            case "down_left":
                if (spriteNum == 1 || spriteNum == 5 || spriteNum == 9)
                    image = down1;
                if (spriteNum == 2 || spriteNum == 6 || spriteNum == 10)
                    image = down2;
                if (spriteNum == 3 || spriteNum == 7 || spriteNum == 11)
                    image = down3;
                if (spriteNum == 4 || spriteNum == 8 || spriteNum == 12)
                    image = down4;
                break;
            case "left":
                if (spriteNum == 1 || spriteNum == 5 || spriteNum == 9)
                    image = left1;
                if (spriteNum == 2 || spriteNum == 6 || spriteNum == 10)
                    image = left2;
                if (spriteNum == 3 || spriteNum == 7 || spriteNum == 11)
                    image = left3;
                if (spriteNum == 4 || spriteNum == 8 || spriteNum == 12)
                    image = left4;
                break;
            case "right":
                if (spriteNum == 1 || spriteNum == 5 || spriteNum == 9)
                    image = right1;
                if (spriteNum == 2 || spriteNum == 6 || spriteNum == 10)
                    image = right2;
                if (spriteNum == 3 || spriteNum == 7 || spriteNum == 11)
                    image = right3;
                if (spriteNum == 4 || spriteNum == 8 || spriteNum == 12)
                    image = right4;
                break;
        }
        return image;
    }
    // END: Animation


    // BEGIN: MOVEMENT
    public void moveUp() {
        posY = posY - speed;
        hitbox.y -= speed;
    }

    public void moveDown() {
        posY = posY + speed;
        hitbox.y += speed;
    }

    public void moveLeft() {
        posX = posX - speed;
        hitbox.x -= speed;
    }

    public void moveRight() {
        posX = posX + speed;
        hitbox.x += speed;
    }


    public void moveUpLeft() {
        posY -= speed / SQRT2;
        hitbox.y -= speed / SQRT2;
        posX -= speed / SQRT2;
        hitbox.x -= speed / SQRT2;
    }

    public void moveUpRight() {
        posY -= speed / SQRT2;
        hitbox.y -= speed / SQRT2;
        posX += speed / SQRT2;
        hitbox.x += speed / SQRT2;
    }

    public void moveDownLeft() {
        posY += speed / SQRT2;
        hitbox.y += speed / SQRT2;
        posX -= speed / SQRT2;
        hitbox.x -= speed / SQRT2;
    }

    public void moveDownRight() {
        posY += speed / SQRT2;
        hitbox.y += speed / SQRT2;
        posX += speed / SQRT2;
        hitbox.x += speed / SQRT2;
    }
    // END: MOVEMENT
}
