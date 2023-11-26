package Entity.Living.Player;

import Physics.Box.AttackBox;
import Utils.Vector2D;

import java.awt.*;
import java.net.PortUnreachableException;
import Graphics.RenEntPirate;

import static Entity.Entity.ENEMY;
import static Entity.Entity.PLAYER;

public class Attack extends PlayerState {
    public AttackBox attackBox;
    public float reach;
    public Attack(Player player) {
        super(player);
        priority = ATTACK;
        attackBox = new AttackBox(player,32, 40, PLAYER, ENEMY);
        reach = 32;
        duration = 21;
        tickPerAction = 21;
    }
    @Override
    public int getAnimationState() {
        return RenEntPirate.SLASH;
    }

    @Override
    public void update() {
        super.update();
        float direction = player.lastMove ? 1 : -1;
        if((tick-7)% tickPerAction == 0) {
            Vector2D centre = player.getCentre();
            attackBox.centre.x = centre.x + direction * reach/2;
            attackBox.centre.y = centre.y;
            attackBox.width = player.getWidth() + reach;
            attackBox.attack();
        }
    }

    @Override
    public void render(Graphics g) {

    }

    @Override
    public boolean satisfy() {
        return player.attack;
    }
}
