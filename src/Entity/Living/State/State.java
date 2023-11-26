package Entity.Living.State;


import java.awt.*;

public abstract class State {
    protected int tick;
    protected int tickPerAction = 10;
    protected int duration;
    public float priority;
    public void update() {
        tick ++;
    }
    public abstract void render(Graphics g);
    public void init() {
        tick = 0;
    }
    public abstract boolean satisfy();
	public abstract int getAnimationState();
}
