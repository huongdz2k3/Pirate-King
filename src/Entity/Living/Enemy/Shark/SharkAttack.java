package Entity.Living.Enemy.Shark;

import Entity.Living.Enemy.Enemy;
import Entity.Living.Enemy.State.Attacking;
import Physics.Box.AttackBox;
import Utils.Vector2D;

import java.util.Vector;

import static Entity.Entity.ENEMY;
import static Entity.Entity.PLAYER;

public class SharkAttack extends Attacking {
    public SharkAttack(Enemy enemy) {
        super(enemy);
        AttackBox b1 = new AttackBox(enemy, 32, 32, ENEMY, PLAYER);
        attSeq = new Vector<>(); attSeq.add(64F); attSeq.add(128F); attSeq.add(256F);
        ////////////////////////////////////
        attBox = b1;

    }

    @Override
    public void attackPhase() {

    }

    @Override
    public void updateAttackBox() {
        int id = (tick / 20) % attSeq.size();
        Vector2D centre = enemy.getCentre();
        attBox.width = attSeq.get(id);
        attBox.centre.x = centre.x + enemy.direction*attBox.width/2;
        attBox.centre.y = centre.y;
    }

}
