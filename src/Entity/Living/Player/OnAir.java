package Entity.Living.Player;

import Entity.Living.State.State;

import java.awt.*;
import Graphics.RenEntPirate;

public class OnAir extends PlayerState {
    public OnAir(Player player) {
        super(player);
        priority = ONAIR;
    }
    public void debug() {
        System.out.println("On Air");
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
        boolean res = false;
        if(player.isOnGround() && player.jumping) {
            res = true;
        } else if(!player.isOnGround()) {
            res = true;
        }
        return res;
    }

    @Override
    public int getAnimationState() {
        return RenEntPirate.JUMP;
    }
}
