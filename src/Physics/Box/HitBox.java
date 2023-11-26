package Physics.Box;

import Entity.Living.Living;
import Physics.World.AttackWorld;
import Utils.Vector2D;

public class HitBox extends Box{
    public int health=4;

    public void setHeath(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }
    public HitBox(Living entity, float width, float height, int typeMask) {
        this.width = width;
        this.height = height;
        this.entity = entity;
        this.typeMask = typeMask;
        AttackWorld.add(this);
    }

    @Override
    public Vector2D getCentre() {
        Vector2D tmp = entity.getCentre();
        centre.x = tmp.x;
        centre.y = tmp.y;
        return centre;
    }
    public void receiveDamage(int damage, int direction) {
        entity.health-=damage;
        entity.hit = true;
        entity.hitDirection = direction;
        //if(health<0) this.entity.die();
    }
}
