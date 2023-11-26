package Entity.Living.Enemy.State;

import Entity.EntitiesManager;
import Entity.Living.Enemy.Enemy;
import Entity.Living.Player.Player;
import Utils.Vector2D;

import java.awt.*;

import static Utils.Util.inRange;

public class Chasing extends EnemyState {
    private float chaseSpeedX;
    public Chasing(Enemy enemy) {
        super(enemy);
        priority = CHASING;
        chaseSpeedX = 5;
    }

	 public void init() {
		  super.init();
		  if(enemy.getRenEnt()!=null) enemy.getRenEnt().setStateSpeed(1, 3);
	 }

    @Override
    public void update() {
        Player player = EntitiesManager.getPlayer();
        float dir = player.getCentreX() > enemy.getCentreX() ? 1 : -1;
        enemy.currentSpeed.x = dir * chaseSpeedX;
    }

    @Override
    public void render(Graphics g) {

    }

    @Override
    public boolean satisfy() {
        Player player = EntitiesManager.getPlayer();
        if(player == null) {
            return false;
        }
        Vector2D pPos = player.getCentre();
        Vector2D ePos = enemy.getCentre();
        if(inRange(pPos.x, ePos.x-enemy.detectRange.x, ePos.x+enemy.detectRange.x) &&
        inRange(pPos.y, ePos.y-enemy.detectRange.y, ePos.y+enemy.detectRange.y)) {
            return true;
        }
        return false;
    }

	 @Override
	 public int getAnimationState() {
		 return 1;
	 }
}
