package Entity.Living.Enemy.State;

import Entity.Living.Enemy.Enemy;

import java.awt.*;

public class Falling extends EnemyState {
    public Falling(Enemy enemy) {
        super(enemy);
        priority = FALLING;
    }
    @Override
    public void update() {
    }

    @Override
    public void render(Graphics g) {

    }

    @Override
    public boolean satisfy() {
        return false;
    }

	 @Override
	 public int getAnimationState() {
		 return 0;
	 }
}
