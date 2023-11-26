package Entity.Living.Enemy.Crab;

import Entity.Living.Enemy.Enemy;
import Entity.Living.Enemy.State.Attacking;
import Physics.Box.AttackBox;
import Utils.Vector2D;

import java.util.Vector;

import static Entity.Entity.ENEMY;
import static Entity.Entity.PLAYER;

public class CrabAttack extends Attacking {
    public int timeBetweenAttack = 10;
    public CrabAttack(Enemy enemy) {
        super(enemy);
        AttackBox b1 = new AttackBox(enemy, 128, 48, ENEMY, PLAYER);
        attBox = b1;
        anticipation = 15;
        attDuration = 15;
        timeBetweenAttack = 15;
        duration = 35;
    }

    @Override
    public void attackPhase() {
        int attTick = tick - anticipation;
        if(attTick % timeBetweenAttack == 1) {
            updateAttackBox();
            attBox.attack();
        }
    }

    @Override
    public void updateAttackBox() {
        attBox.centre.x = enemy.collisionBox.centre.x;
        attBox.centre.y = enemy.collisionBox.centre.y;
    }
}
