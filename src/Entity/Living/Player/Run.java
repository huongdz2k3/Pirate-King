package Entity.Living.Player;

import Entity.Living.State.State;

import java.awt.*;
import Graphics.RenEntPirate;

public class Run extends PlayerState {
    @Override
    public int getAnimationState() {
        return RenEntPirate.RUN_JUMP;
    }
    public Run(Player player) {
        super(player);
        priority = RUN;
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
        if(player.isOnGround() && (player.movingLeft ^ player.movingRight)) {
            res = true;
        }
        return res;
    }
}
