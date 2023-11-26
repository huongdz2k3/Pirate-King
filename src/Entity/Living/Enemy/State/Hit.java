package Entity.Living.Enemy.State;

import Entity.Living.Enemy.Enemy;
import Entity.Living.Player.Player;

import java.awt.*;

import static Utils.Util.sign;

public class Hit extends EnemyState{
    public float defaultAcceralation = 0;
    public float defaultHitSpeed = 0;
    public float acceleration = 0;
    public void update() {
        super.update();
        enemy.currentSpeed.x += acceleration;
        int newSign = sign(enemy.currentSpeed.x);
        if(newSign * sign(acceleration) > 0) {
            enemy.currentSpeed.x = 0;
        }
        if(tick == duration) {
            endState();
        }
    }
    public void endState() {
        enemy.currentState = enemy.defaultState;
        enemy.currentSpeed.x = 0;
    }
    @Override
    public void render(Graphics g) {

    }

    @Override
    public boolean satisfy() {
        return enemy.hit || 0 < tick && tick < duration;
    }

    @Override
    public int getAnimationState() {
        return 3;
    }
    public void init() {
        tick = 0;
        enemy.hit = false;
        enemy.currentSpeed.x = enemy.hitDirection * defaultHitSpeed;
        acceleration = -enemy.hitDirection * defaultAcceralation;
    }
    public Hit(Enemy enemy) {
        super(enemy);
        priority = HIT;
        defaultAcceralation = 1;
        defaultHitSpeed = 12;
        duration = 20;
    }
}
