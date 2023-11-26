package Entity.Living.Enemy.State;

import Entity.Living.Enemy.Enemy;

import java.awt.*;

public class Normal extends EnemyState {
    public Normal(Enemy enemy) {
        super(enemy);
        priority = NORMAL;
    }
    public void init() {
        super.init();
        enemy.getRenEnt().setStateSpeed(1, 5);
    }
    @Override
    public boolean satisfy() {
        return true;
    }

    public void update() {
        int currentDir = enemy.currentSpeed.x >= 0 ? 1 : -1;
        enemy.currentSpeed.x = currentDir * enemy.defaultSpeed.x;
        if(enemy.switchDirection) {
            enemy.currentSpeed.x *= -1;
            enemy.switchDirection = false;
        }
                                        
    }

    @Override
    public void render(Graphics g) {

    }

	 @Override
	 public int getAnimationState() {
		 if(enemy.currentSpeed.x == 0) return 0;
		 else return 1;
	 }
}
