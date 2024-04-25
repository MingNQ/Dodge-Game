package Model;

import Controller.Tool.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Projectile extends Entity {
    public int tileSize = 48;
    public int imageWidth = tileSize * 4;
    public int imageHeight = 960; // Sqrt(screenWidth*2 + screenHeight*2)

    public Projectile() {
        super();
    }

    public void getImage(int num) {
        up1 = setUp(num);
        up2 = setUp(num);
        up3 = setUp(num);
        up4 = setUp(num);

        down1 = setUp(num);
        down2 = setUp(num);
        down3 = setUp(num);
        down4 = setUp(num);

        left1 = setUp(num);
        left2 = setUp(num);
        left3 = setUp(num);
        left4 = setUp(num);

        right1 = setUp(num);
        right2 = setUp(num);
        right3 = setUp(num);
        right4 = setUp(num);
    }

    public void getImage() {

    }

    public BufferedImage setUp(int num) {
        BufferedImage image = null;
        UtilityTool uTool = new UtilityTool();

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/Monsters/MonsterProjectile/projectile_0" + num + ".png"));
            image = uTool.scaleImage(image, tileSize, tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
