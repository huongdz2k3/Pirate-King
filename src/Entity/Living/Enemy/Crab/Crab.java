package Entity.Living.Enemy.Crab;

import Entity.Living.Enemy.Enemy;
import Physics.Box.CollisionBox;
import Physics.Box.HitBox;
import Utils.Artist;

import java.awt.*;
import Graphics.*;

public class Crab extends Enemy {
    public Crab() {
        super();
        // State
        defaultSpeed.x = 2;
        currentSpeed.x = 2;
        // BOX
        this.collisionBox = new CollisionBox(600, 400, 64, 32, ENEMY, PLAYER|ENV);
        this.hitBox = new HitBox(this, 64, 32, ENEMY);
        // CUSTOM ATTACK STATE
        states.add(new CrabAttack(this));
        // RENDER
        renEnt = new RenEntCrab(0,1);
        renEnt.setOutSize(160, 48);
    }
}
