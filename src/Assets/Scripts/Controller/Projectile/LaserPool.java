package Controller.Projectile;

import View.GamePanel;

import java.util.LinkedList;
import java.util.Queue;

public class LaserPool {
    GamePanel gp;
    private Queue<LaserController> pool;

    public LaserPool(GamePanel gp) {
        this.gp = gp;
        this.pool = new LinkedList<>();

        for (int i = 0; i < 2; i++) {
            pool.add(new LaserController(gp));
        }
    }

    public LaserController getLaser() {
        return pool.isEmpty() ? new LaserController(gp) : pool.poll();
    }

    public void returnLaser(LaserController laser) {
        laser.setDefaultValue();
        pool.add(laser);
    }
}
