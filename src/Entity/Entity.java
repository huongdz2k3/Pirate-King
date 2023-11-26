package Entity;

import Physics.Box.Box;
import Physics.Box.CollisionBox;
import Utils.Vector2D;

import java.awt.*;
import java.util.Vector;
import Graphics.RenderEntity;

public abstract class Entity {
    public static final int ENV = 1;
    public static final int PLAYER = 2;
    public static final int ENEMY = 4;
    public static final int SHIP = 8;
    protected RenderEntity renEnt;

    protected boolean freeToSwitchState;
    public Entity() {
        freeToSwitchState = false;
    }
    public enum EntityType {
        PLAYER,
        ENEMY,
        ENVIRONMENT,
        EFFECT,
    }
    protected EntityType entityType;
    public abstract void render(Graphics g);
    public abstract boolean update();

    public void setFreeToSwitchState(boolean freeToSwitchState) {
        this.freeToSwitchState = freeToSwitchState;
    }
	 public RenderEntity getRenEnt() {
		 return renEnt;
	 }
}
