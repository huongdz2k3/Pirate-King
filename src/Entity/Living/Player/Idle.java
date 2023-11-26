package Entity.Living.Player;

import Entity.Living.State.State;

import java.awt.*;
import Graphics.RenEntPirate;

public class Idle extends PlayerState {
    public Idle(Player player) {
        super(player);
        priority = IDLE;
    }
    public void init() {
        super.init();
        player.currentSpeed.x = 0;
    }
    public void debug() {
        System.out.println("idle");
    }
    @Override
    public void update() {
        super.update();
    }

    @Override
    public void render(Graphics g) {

    }

    @Override
    public boolean satisfy() {
        return true;
    }

    @Override
    public int getAnimationState() {
        return RenEntPirate.STAND;
    }
}
