package Controller.Projectile;

import View.GamePanel;

import java.util.LinkedList;
import java.util.Queue;

public class BallPool {
    GamePanel gp;
    private Queue<BallController> pool;

    public BallPool(GamePanel gp) {
        this.gp = gp;
        this.pool = new LinkedList<>();

        for (int i = 0; i < 15; i++) {
            pool.add(new BallController(gp));
        }
    }

    public BallController getBall() {
        return pool.isEmpty() ? new BallController(gp) : pool.poll();
    }

    public void returnBall(BallController ball) {
        ball.setDefaultValue();
        pool.add(ball);
    }
}
