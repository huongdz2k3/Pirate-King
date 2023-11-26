package Entity.Living.Player;

import Entity.Living.State.State;

public abstract class PlayerState extends State {
    public Player player;
    public static final float ATTACK = 3;
    public static final float IDLE = 0;
    public static final float ONAIR = 2;
    public static final float RUN = 1;
    public static final float DIE = 4;
    public static final float HIT = 3.5F;

    public PlayerState(Player player) {
        this.player = player;
    }
    abstract public int getAnimationState();
}
