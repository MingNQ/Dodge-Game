package Model;

import java.awt.image.BufferedImage;

public class Tile {
    public BufferedImage image;
    public boolean isCollision = false;

    public Tile() {
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public boolean isCollision() {
        return isCollision;
    }

    public void setCollision(boolean collision) {
        isCollision = collision;
    }
}
