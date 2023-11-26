package Physics.Box;

import Entity.Living.Living;
import Physics.World.AttackWorld;
import Utils.Artist;
import Utils.Vector2D;

import java.awt.*;
import java.util.Vector;

public class AttackBox extends Box {
    public float relX;
    public float relY;
    public int damage=1;
    protected Vector<Float> attackSequence;
    private int tickPerAction = 20;
    public AttackBox(Living entity, float width, float height, int typeMask, int colMask) {
        super();
        this.entity = entity;
        this.width = width;
        this.height = height;
        this.typeMask = typeMask;
        this.collisionMask = colMask;
    }


    @Override
    public Vector2D getCentre() {
        return centre;
    }

    public int getDamage() {
        return this.damage;
    }

    public void setDamage(int damage){
        this.damage=damage;
    }
    public void attack() {
        AttackWorld.attack(this);
    }
    public void setAttackSequence(Vector<Float> attS) {
        this.attackSequence = attS;
    }
}
