package Model;

import Controller.Tool.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Laser extends Projectile {
    public BufferedImage startImage, endImage;

    public Laser() {
        super();
    }

    @Override
    public void getImage() {
        startImage = setUp(1);
        endImage = setUp(2);
    }

    @Override
    public BufferedImage setUp(int num) {
        BufferedImage image = null;
        UtilityTool uTool = new UtilityTool();
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/Monsters/MonsterProjectile/laser_0" + num + ".png"));
            image = uTool.scaleImage(image, imageWidth, imageHeight);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    @Override
    public void setAnimations() {
        spriteCount++;

        if (spriteCount > 5) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 3;
            } else if (spriteNum == 3) {
                spriteNum = 4;
            } else if (spriteNum == 4) {
                spriteNum = 5;
            }
            spriteCount = 0;
        }
    }

    @Override
    public BufferedImage drawAnimation() {
        BufferedImage image = null;

        switch (state) {
            case "idle":
                if (spriteNum == 1 || spriteNum == 2 || spriteNum == 3 || spriteNum == 4 || spriteNum == 5) {
                    image = startImage;
                }
                break;
            case "fire":
                if (spriteNum == 1 || spriteNum == 2 || spriteNum == 3 || spriteNum == 4 || spriteNum == 5) {
                    image = endImage;
                }
                break;
            case "death":
                break;
        }

        return image;
    }

}
