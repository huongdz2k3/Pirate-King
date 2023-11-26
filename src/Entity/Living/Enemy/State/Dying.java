package Entity.Living.Enemy.State;

import Entity.EntitiesManager;
import Entity.Living.Enemy.Enemy;
import Entity.Living.Player.Attack;
import Physics.World.AttackWorld;
import Physics.World.CollisionWorld;

import java.awt.*;

public class Dying extends EnemyState {

    public Dying(Enemy enemy) {
        super(enemy);
        priority = DYING;
    }

    public void init() {
        super.init();
        enemy.currentSpeed.x = 0;
        duration = enemy.getRenEnt().getStateDuration(getAnimationState());
    }

    @Override
    public boolean satisfy() {

        return enemy.getHealth() < 0;
    }

    @Override
    public void update() {
        tick ++;
        if(tick > duration) {
            die();
        }
    }

    @Override
    public void render(Graphics g) {

    }
    public void die() {
        enemy.alive = false;
        releaseResource();
    }
    public void releaseResource() {
        CollisionWorld.remove(enemy.collisionBox);
        AttackWorld.remove(enemy.hitBox);
    }

	 @Override
	 public int getAnimationState() {
		 return 4;
	 }
}
