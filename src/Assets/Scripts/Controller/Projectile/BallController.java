package Controller.Projectile;

import Model.Ball;
import View.GamePanel;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Random;

public class BallController extends ProjectileController {
    // Constructor
    public BallController(GamePanel gp) {
        super(gp);
        this.gp = gp;
        this.projectile = new Ball();
        this.rd = new Random();
        setDefaultValue();
        projectile.getImage(rd.nextInt(4));
    }

    // Set default value when call
    public void setDefaultValue() {
        super.setDefaultValue(); // Call parent method

        double projectileRecPosX = projectile.posX + 14;
        double projectileRecPosY = projectile.posY + 16;
        double projectileRecW = projectile.recW - 12;
        double projectileRecH = projectile.recH - 12;
        projectile.hitbox = new Rectangle2D.Double(projectileRecPosX, projectileRecPosY, projectileRecW, projectileRecH);
    }

    // Check if projectile is out of screen
    public boolean isOutRange() {
        return super.isOutRange();
    }

    // Set where target distance for projectile move to
    public void setTargetDistance() {
        super.setTargetDistance();
    }

    // Spawn from random side
    public void spawn() {
        super.spawn();
    }

    // Draw image
    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        // If alive can draw and vice versa
        image = projectile.drawAnimation();
        AffineTransform at = AffineTransform.getTranslateInstance(projectile.posX, projectile.posY);
        g2.drawImage(image, at, null);
    }

    // Update per frame
    public void update() {
        // Check if ball is in screen => can update and vice versa
        if (!isOutRange()) {
            projectile.posX += xDir * projectile.speed;
            projectile.hitbox.x += xDir * projectile.speed;

            projectile.posY += yDir * projectile.speed;
            projectile.hitbox.y += yDir * projectile.speed;
        }

        projectile.setAnimations();
    }
}
