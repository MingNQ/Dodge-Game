package Controller.Projectile;

import Model.Projectile;
import View.GamePanel;

import java.awt.geom.Rectangle2D;
import java.util.Random;

public class ProjectileController {
    public Projectile projectile = new Projectile();
    public double xDir = 4; // x direction
    public double yDir = 4; // y direction
    Random rd;
    GamePanel gp;

    public ProjectileController(GamePanel gp) {
        this.gp = gp;
    }

    // Reset hit box
    public void resetHitbox() {
        projectile.hitbox = new Rectangle2D.Double(0, 0, 0, 0);
    }

    // Set default value when call
    public void setDefaultValue() {
        spawn();
        projectile.speed = 6.0;
        setTargetDistance();

        projectile.startPosX = projectile.posX;
        projectile.startPosY = projectile.posY;
    }

    // Set where target distance for projectile move to
    public void setTargetDistance() {
        double distance = Math.sqrt(Math.pow(projectile.posX - projectile.targetPosX, 2) + Math.pow(projectile.posY - projectile.targetPosY, 2));

        xDir = (projectile.targetPosX - projectile.posX) / distance;
        yDir = (projectile.targetPosY - projectile.posY) / distance;
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
                projectile.posX = rd.nextDouble(gp.screenWidth);
                projectile.posY = -1;
                break;
            case 1: // Spawn from down
                projectile.direction = "up";
                projectile.posX = rd.nextInt(gp.screenWidth);
                projectile.posY = gp.screenHeight;
                break;
            case 2: // Spawn from left
                projectile.direction = "right";
                projectile.posX = -1;
                projectile.posY = rd.nextInt(gp.screenHeight);
                break;
            case 3: // Spawn from right
                projectile.direction = "left";
                projectile.posX = gp.screenWidth;
                projectile.posY = rd.nextInt(gp.screenHeight);
                break;
        }
    }

    // Check if projectile is out of screen
    public boolean isOutRange() {
        if (projectile.posX < -1 || projectile.posX > gp.screenWidth || projectile.posY < -1 || projectile.posY > gp.screenHeight)
            return true;
        return false;
    }
}
