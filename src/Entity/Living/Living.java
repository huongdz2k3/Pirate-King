package Entity.Living;

import Entity.Entity;
import Entity.EntitiesManager;
import Physics.Box.AttackBox;
import Physics.Box.CollisionBox;
import Physics.Box.HitBox;
import Physics.Signal.Signal;
import Utils.Vector2D;

import java.util.Vector;

import static Physics.Signal.Signal.DOWN;

public abstract class Living extends Entity {
    public int health = 5;
    public static final float GRAVITY = 1.5f;
    public Vector2D defaultSpeed;
    public Vector2D currentSpeed;
    public static final float MINSPEEDY = -50;
    public HitBox hitBox;
    protected boolean onGround;
    public boolean jumping;
    public boolean allowJumping;
    public boolean alive = true;
    public CollisionBox collisionBox;
    public int direction;
    public boolean attack;
    public boolean hit = false;
    public int hitDirection = 0;
    // CONSTRUCTOR
    public Living() {
        defaultSpeed = new Vector2D(0, 0);
        currentSpeed = new Vector2D(0, 0);
        onGround = false;
        jumping = false;
        allowJumping = false;

    }

    // all about SPEED
    public Vector2D getDefaultSpeed() {
        return defaultSpeed;
    }
    public void setCurrentSpeedX(float x) {currentSpeed.x = x;}
    // all about POSITION
    public float getCentreX() {return collisionBox.centre.x;};
    public boolean isOnGround() {return onGround;}
    // all about BOXES
    public CollisionBox getCollisionBox() {return collisionBox;}
    public float getWidth() {return collisionBox.width;}
    // all about HEALTH
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    protected Vector<AttackBox> aBoxes;
    public void setDefaultSpeed(float x, float y) {
        defaultSpeed = new Vector2D(x, y);
        currentSpeed = new Vector2D(x, 0);
    }
    public void setHitBox(HitBox hitBox) {
        this.hitBox = hitBox;
    }

    public float getX() {
        return collisionBox.centre.x;
    }
    public Signal generalMove() {
        currentSpeed.y -= GRAVITY;
        currentSpeed.y = Math.max(currentSpeed.y, MINSPEEDY);
        Signal signal = collisionBox.detectCollision(currentSpeed);
        if(signal.vertical != 0) {
            currentSpeed.y = 0;
        }
        if(signal.vertical == DOWN) {
            onGround = true;
            allowJumping = true;
        } else {
            onGround = false;
        }
        return signal;
    }
    protected boolean checkIfAlive() {
        return hitBox.getHealth() > 0;
    }
    public void die() {
        /*
        this function only remove this entity out of EntityManager, the rest of resource
        must be free manually
         */
        EntitiesManager.remove(this);
    }
    public HitBox getHitBox() {
        return hitBox;
    }
    public void setAlive(boolean value) {
        alive = value;
    }
    public Vector2D getCentre() {
        return collisionBox.centre;
    }
    public void setCentre(float x, float y) {
        collisionBox.centre.x = x;
        collisionBox.centre.y = y;
    }


}
