package Entity.Living.Player;

import Entity.EntitiesManager;
import Physics.World.AttackWorld;
import Physics.World.CollisionWorld;
import Graphics.RenEntPirate;

import java.awt.*;

public class Die extends PlayerState {
    public Die(Player player) {
        super(player);
        priority = DIE;
        duration = 60;
    }
    @Override
    public int getAnimationState() {
        return RenEntPirate.DIE;
    }

    @Override
    public void update() {
        super.update();
        if(EntitiesManager.getPlayer() == null) {
            return;
        }
        if(tick >= duration) {
            die();
        }

    }
    public void die() {
        player.alive = false;
        releaseResource();
        player.endgame();
    }
    public void releaseResource() {
        CollisionWorld.remove(player.collisionBox);
        EntitiesManager.setPlayer(null);
    }
    @Override
    public void render(Graphics g) {
        if(!player.alive) {
            return;
        }
    }
    @Override
    public boolean satisfy() {
        return player.health < 0 || player.collisionBox.centre.y < -10;
    }
    public void init() {
        super.init();
        player.receiveCommand = false;
        AttackWorld.remove(player.hitBox);
        if(player.collisionBox.centre.y < -10) {
            die();
        }
        player.currentSpeed.x = 0;
    }
}
