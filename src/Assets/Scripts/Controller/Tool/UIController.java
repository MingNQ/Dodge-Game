package Controller.Tool;

import View.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class UIController {
    // State and Sub Window
    public int subState = 0;
    public int commandNum = 0;
    public boolean isMusicOn;
    public boolean isSoundOn;
    GamePanel gp;
    Graphics2D g2;
    Font brickSans_36;
    // Handle music
    BufferedImage musicOn;
    BufferedImage musicOff;
    BufferedImage arrow;

    public UIController(GamePanel gp) {
        this.gp = gp;
        try {
            brickSans_36 = Font.createFont(Font.PLAIN, new File("./src/Assets/Sprites/Font/BrickSans-Bold.otf")).deriveFont(36f);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FontFormatException e) {
            e.printStackTrace();
        }

        musicOn = getMusicButtonImage("Music_On.png");
        musicOff = getMusicButtonImage("Music_Off.png");
        arrow = getMusicButtonImage("Arrow.png");
        isMusicOn = true;
        isSoundOn = true;
    }

    public BufferedImage getMusicButtonImage(String path) {
        BufferedImage image = null;
        UtilityTool uTool = new UtilityTool();
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/Sound/" + path));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        g2.setFont(brickSans_36);
        g2.setColor(Color.WHITE);

        if (gp.gameState == gp.pauseState) {
            drawPauseScreen();
        }
    }

    public void drawPauseScreen() {
        int frameX = gp.tileSize * 4;
        int frameY = gp.tileSize;
        int frameWidth = gp.tileSize * 8;
        int frameHeight = gp.tileSize * 10;

        Color c = new Color(0, 0, 0, 210);

        g2.setColor(c);
        g2.fillRoundRect(frameX, frameY, frameWidth, frameHeight, 35, 35);

        // Draw outline
        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(frameX + 5, frameY + 5, frameWidth - 10, frameHeight - 10, 25, 25);

        switch (subState) {
            case 0:
                optionTop(frameX, frameY);
                break;
            case 1:
                optionConfirm(frameX, frameY);
                break;
        }
    }

    public void optionTop(int frameX, int frameY) {
        int textX;
        int textY;

        // Title
        String text = "Pause";
        textX = getXForCenterText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);


        // Music
        textX = frameX + gp.tileSize;
        textY += gp.tileSize * 2;
        g2.drawString("Music", textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX - 30, textY);
        }

        // Sound Effect
        textY += gp.tileSize * 3;
        g2.drawString("Sound", textX, textY);
        if (commandNum == 1) {
            g2.drawString(">", textX - 30, textY);
        }

        // Back to main menu
        text = "Back To Menu";
        textX = getXForCenterText(text);
        textY += gp.tileSize * 3;
        g2.drawString(text, textX, textY);
        if (commandNum == 2) {
            g2.drawString(">", textX - 30, textY);
        }

        // Music Volume Change
        int imageX = frameX + gp.tileSize * 4;
        int imageY = frameY + gp.tileSize * 2 + 12;
        textX = frameX + gp.tileSize * 6;
        textY = frameY + gp.tileSize * 3;
        if (isMusicOn) {
            g2.drawImage(musicOn, imageX, imageY, null);
            g2.drawString("On", textX, textY);
            if (gp.keyH.musicPressed) {
                isMusicOn = false;
                gp.music.stop();
            }
        } else {
            g2.drawImage(musicOff, imageX, imageY, null);
            g2.drawString("Off", textX, textY);
            if (!gp.keyH.musicPressed) {
                isMusicOn = true;
                gp.music.play();
            }
        }
        textX = frameX + gp.tileSize * 4;
        textY += gp.tileSize;
        g2.setStroke(new BasicStroke(3));
        g2.drawRect(textX, textY, 150, 20);
        int volumeWidth = 30 * gp.music.volumeScale;
        g2.fillRect(textX, textY, volumeWidth, 20);

        // Sound Volume Change
        imageX = frameX + gp.tileSize * 4;
        imageY = frameY + gp.tileSize * 5 + 12;
        textX = frameX + gp.tileSize * 6;
        textY = frameY + gp.tileSize * 6;
        if (isSoundOn) {
            g2.drawImage(musicOn, imageX, imageY, null);
            g2.drawString("On", textX, textY);
            if (gp.keyH.soundPressed) {
                isSoundOn = false;
            }
        } else {
            g2.drawImage(musicOff, imageX, imageY, null);
            g2.drawString("Off", textX, textY);
            if (!gp.keyH.soundPressed) {
                isSoundOn = true;
            }
        }
    }

    public void optionConfirm(int frameX, int frameY) {
        String message = "Are you sure back to \nmenu";
        int textX;
        int textY;
        textX = frameX + gp.tileSize;
        textY = frameY + gp.tileSize * 2;
        for (String line : message.split("\n")) {
            g2.drawString(line, textX, textY);
            textY += 40;
        }

        // Yes
        String text = "Yes";
        textX = getXForCenterText(text);
        textY += gp.tileSize * 3;
        g2.drawString(text, textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX - 30, textY);
        }

        // No
        text = "No";
        textX = getXForCenterText(text);
        textY += gp.tileSize;
        g2.drawString(text, textX, textY);
        if (commandNum == 1) {
            g2.drawString(">", textX - 30, textY);
        }
    }

    // Center String
    public int getXForCenterText(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - length / 2;

        return x;
    }

}
