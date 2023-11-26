package Entity.Living.Enemy.State;

import Entity.EntitiesManager;
import Entity.Living.Enemy.Enemy;
import Entity.Living.Player.Player;
import Physics.Box.AttackBox;
import Utils.Vector2D;

import java.awt.*;
import java.util.Vector;

import static Physics.Signal.Signal.LEFT;
import static Physics.Signal.Signal.RIGHT;
import static Utils.Util.inRange;

public abstract class Attacking extends EnemyState {
    protected AttackBox attBox;
    protected Vector<Float> attSeq;
    protected int anticipation;
    protected int attDuration;
    public Attacking(Enemy enemy) {
        super(enemy);
        priority = ATTACK;
    }
    public void init() {
        super.init();
        enemy.currentSpeed.x = 0;
        updateAttackDirection();
    }
    public void initAttack() {}
    public void update() {
        tick ++;
        if(tick == anticipation+1) {
            initAttack();
        }
        if(tick > anticipation && tick - anticipation <= attDuration) {
            attackPhase();
        } else if(tick == duration) {
            enemy.currentState = enemy.defaultState;
        }



    }
    public abstract void attackPhase();
    @Override
    public void render(Graphics g) {
        //debug attBox.render(g);
    }
    public void updateAttackDirection() {
        Player player = EntitiesManager.getPlayer();
        if(player == null) {
            return;
        }
        Vector2D c2 = player.getCentre();
        Vector2D c1 = enemy.getCentre();

        if(c1.x > c2.x) {
            enemy.direction = LEFT;
        } else {
            enemy.direction = RIGHT;
        }
    }
    public boolean satisfy() {
        Player player = EntitiesManager.getPlayer();
        if(player == null) {
            return false;
        }
        if(0 < tick && tick < duration) {
            return true;
        }
        Vector2D pPos = player.getCentre();
        Vector2D ePos = enemy.getCentre();
        if(inRange(pPos.x, ePos.x-enemy.detectRange.x, ePos.x+enemy.detectRange.x) &&
                inRange(pPos.y, ePos.y-enemy.detectRange.y, ePos.y+enemy.detectRange.y)) {
            return true;
        }
        return false;
    }
    public abstract void updateAttackBox();

	 @Override
	 public int getAnimationState() {
		 return 2;
	 }
}
