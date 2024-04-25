package Controller.Projectile;

import Model.Laser;
import View.GamePanel;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Random;

public class LaserController extends ProjectileController {
    public final double PI = Math.PI;
    public double angle; // in radian
    public Rectangle2D rectangle;
    public double coolDown = 0.5 * 70;
    public double coolDownCurr;
    public boolean isFired;

    // Position that is center of image and will at after rotate
    double backX;
    double backY;
    double recBackX;
    double recBackY;

    // Constructor
    public LaserController(GamePanel gp) {
        super(gp);
        this.gp = gp;
        this.rd = new Random();
        this.projectile = new Laser();
        projectile.getImage();
        setDefaultValue();
    }

    // Set angle will rotate
    public void setAngle() {
        double argX = projectile.targetPosX - projectile.posX;
        double argY = projectile.targetPosY - projectile.posY;

        angle = Math.atan2(argY, argX) - PI / 2;
    }

    // Set where target distance for projectile move to
    public void setTargetDistance() {
        super.setTargetDistance();
    }

    // Set default value when call
    public void setDefaultValue() {
        spawn();
        projectile.state = "idle"; // idle that do nothing
        isFired = false; // Spawn but can fire
        setAngle(); // Set angle from side
        resetHitbox(); // Reset hit box when spawn
    }

    // Set hit box to handle collision
    public void setHitBox() {
        double projectileRecW = projectile.recW * 2;
        double projectileRecH = projectile.imageHeight;
        recBackX = -(projectile.recW) * Math.cos(angle);
        recBackY = -(projectile.recW) * Math.sin(angle);

        switch (projectile.spawnSide) {
            case 0: // Up
                projectile.hitbox = new Rectangle2D.Double(projectile.posX + recBackX, projectile.posY + recBackY, projectileRecW, projectileRecH);
                break;
            case 1: // Down
                projectile.hitbox = new Rectangle2D.Double(projectile.posX - recBackX, projectile.posY - gp.screenHeight, projectileRecW, projectileRecH);
                break;
            case 2: // Left
                projectile.hitbox = new Rectangle2D.Double(projectile.posX, projectile.posY - recBackY, projectileRecH, projectileRecW);
                break;
            case 3: // Right
                projectile.hitbox = new Rectangle2D.Double(projectile.posX + recBackX - gp.screenWidth, projectile.posY + recBackY, projectileRecH, projectileRecW);
                break;
        }
    }

    // Spawn from random side
    public void spawn() {
        // Random side
        projectile.spawnSide = rd.nextInt(4);
        projectile.targetPosX = gp.playerController.player.posX;
        projectile.targetPosY = gp.playerController.player.posY;

        switch (projectile.spawnSide) {
            case 0: // Spawn from up
                projectile.direction = "down";
                projectile.posX = projectile.targetPosX;
                projectile.posY = -1;
                break;
            case 1: // Spawn from down
                projectile.direction = "up";
                projectile.posX = projectile.targetPosX;
                projectile.posY = gp.screenHeight;
                break;
            case 2: // Spawn from left
                projectile.direction = "right";
                projectile.posX = -1;
                projectile.posY = projectile.targetPosY;
                break;
            case 3: // Spawn from right
                projectile.direction = "left";
                projectile.posX = gp.screenWidth;
                projectile.posY = projectile.targetPosY;
                break;
        }
    }

    // Draw image
    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        if (!projectile.state.equals("death")) {
            image = projectile.drawAnimation();

            AffineTransform at = new AffineTransform();
            // Rotate image
            backX = -(image.getWidth() / 2) * Math.cos(angle);
            backY = -(image.getWidth() / 2) * Math.sin(angle);
            at.translate(projectile.posX + backX, projectile.posY + backY);
            at.rotate(angle);

            g2.drawImage(image, at, null);

//            g2.draw(projectile.hitbox);
        }
    }

    // Update per frame
    public void update() {
        // Spawn and do nothing
        if (gp.canSpawnBall()) {
            projectile.state = "idle";
        }
        // Fire laser
        if (!isFired) {
            coolDownCurr++; // Count time can fire
            if (coolDownCurr >= coolDown) {
                projectile.state = "fire";
                isFired = true;
                setHitBox();
            }
        }

        // Timeout Laser
        if (isFired) {
            coolDownCurr--;
            if (coolDownCurr <= 0) {
                projectile.state = "death";
            }
        }

        projectile.setAnimations();
    }
}
