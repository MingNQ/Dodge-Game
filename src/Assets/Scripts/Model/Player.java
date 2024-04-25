package Model;

import Controller.Tool.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    public Player() {
    }

    // Upload image
    public void getPlayerImage(String character) {
        down1 = setUp(character, "down_1");
        down2 = setUp(character, "down_2");
        down3 = setUp(character, "down_3");
        down4 = setUp(character, "down_4");

        up1 = setUp(character, "up_1");
        up2 = setUp(character, "up_2");
        up3 = setUp(character, "up_3");
        up4 = setUp(character, "up_4");

        left1 = setUp(character, "left_1");
        left2 = setUp(character, "left_2");
        left3 = setUp(character, "left_3");
        left4 = setUp(character, "left_4");

        right1 = setUp(character, "right_1");
        right2 = setUp(character, "right_2");
        right3 = setUp(character, "right_3");
        right4 = setUp(character, "right_4");

        shadow = setUp(character, "Shadow_player");
    }

    public BufferedImage setUp(String character, String path) {
        BufferedImage image = null;
        UtilityTool uTool = new UtilityTool();

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/Characters/" + character + "/" + path + ".png"));
            image = uTool.scaleImage(image, 48, 48);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }

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
                spriteNum = 1;
            }
            spriteCount = 0;
        }
    }

    // Draw animation per frame
    public BufferedImage drawAnimation() {
        BufferedImage image = null;
        switch (direction) {
            case "up":
                if (spriteNum == 1)
                    image = up1;
                if (spriteNum == 2)
                    image = up2;
                if (spriteNum == 3)
                    image = up3;
                if (spriteNum == 4)
                    image = up4;
                break;
            case "down":
                if (spriteNum == 1)
                    image = down1;
                if (spriteNum == 2)
                    image = down2;
                if (spriteNum == 3)
                    image = down3;
                if (spriteNum == 4)
                    image = down4;
                break;
            case "left":
                if (spriteNum == 1)
                    image = left1;
                if (spriteNum == 2)
                    image = left2;
                if (spriteNum == 3)
                    image = left3;
                if (spriteNum == 4)
                    image = left4;
                break;
            case "right":
                if (spriteNum == 1)
                    image = right1;
                if (spriteNum == 2)
                    image = right2;
                if (spriteNum == 3)
                    image = right3;
                if (spriteNum == 4)
                    image = right4;
                break;
        }
        return image;
    }
}
