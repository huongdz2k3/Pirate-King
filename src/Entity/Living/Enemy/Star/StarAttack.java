package Entity.Living.Enemy.Star;

import Entity.Living.Enemy.Enemy;
import Entity.Living.Enemy.State.Attacking;
import Physics.Box.AttackBox;

import static Entity.Entity.ENEMY;
import static Entity.Entity.PLAYER;
import static Utils.Util.sign;

public class StarAttack extends Attacking {
    public float defaultChargeSpeed = 3;
    public float defaultAcceleration = 0;
    public float acceleration;
    public StarAttack(Enemy enemy) {
        super(enemy);
        AttackBox b1 = new AttackBox(enemy, 40, 40, ENEMY, PLAYER);
        attBox = b1;
        anticipation = 15;
        attDuration = 20;
        duration = 40;
    }
    public void initAttack() {
        enemy.currentSpeed.x = enemy.direction * defaultChargeSpeed;
        acceleration = -enemy.direction * defaultAcceleration;
    }
    @Override
    public void attackPhase() {
        updateAttackBox();
        attBox.attack();
        enemy.currentSpeed.x += acceleration;
        if(sign(enemy.currentSpeed.x) * sign(acceleration) > 0) {
            enemy.currentSpeed.x = 0;
        }
    }

    @Override
    public void updateAttackBox() {
        attBox.centre.x = enemy.collisionBox.centre.x;
        attBox.centre.y = enemy.collisionBox.centre.y;
    }
}
