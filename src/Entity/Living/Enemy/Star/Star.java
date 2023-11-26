package Entity.Living.Enemy.Star;

import Entity.Living.Enemy.Enemy;
import Physics.Box.CollisionBox;
import Physics.Box.HitBox;
import Graphics.*;
import java.awt.Graphics;

public class Star extends Enemy {
    public Star() {
        // State
        defaultSpeed.x = 2;
        currentSpeed.x = 2;
        // Box
        this.collisionBox = new CollisionBox(600, 400, 32, 32, ENEMY, PLAYER|ENV);
        this.hitBox = new HitBox(this, 40, 40, ENEMY);
        // Attack
        states.add(new StarAttack(this));
        renEnt = new RenEntStar(0,1);
		renEnt.setOutSize(50, 48);
    }
}
