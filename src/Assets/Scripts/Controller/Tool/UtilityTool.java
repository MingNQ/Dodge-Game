package Controller.Tool;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UtilityTool {

    public BufferedImage scaleImage(BufferedImage image, int width, int height) {
        GraphicsConfiguration gfxConfig = GraphicsEnvironment.
                getLocalGraphicsEnvironment().getDefaultScreenDevice().
                getDefaultConfiguration();

        /*
         * if image is already compatible and optimized for current system
         * settings, simply return it
         */
        if (image.getColorModel().equals(gfxConfig.getColorModel()))
            return image;

        // image is not optimized, so create a new image that is
        BufferedImage newImage = gfxConfig.createCompatibleImage(
                width, height, image.getTransparency());

        // get the graphics context of the new image to draw the old image on
        Graphics2D g2d = newImage.createGraphics();

        // actually draw the image and dispose of context no longer needed
        g2d.drawImage(image, 0, 0, width, height, null);
        g2d.dispose();

        // return the new optimized image
        return newImage;
    }
}
