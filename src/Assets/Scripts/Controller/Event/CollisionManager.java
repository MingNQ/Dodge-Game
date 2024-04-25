package Controller.Event;

import Model.Entity;
import View.GamePanel;

public class CollisionManager {
    GamePanel gp;

    public CollisionManager(GamePanel gp) {
        this.gp = gp;
    }

    public boolean checkCollide(Entity player, Entity monster) {
        return player.hitbox.intersects(monster.hitbox);
    }

}
