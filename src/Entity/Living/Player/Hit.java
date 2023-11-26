package Entity.Living.Player;

import java.awt.*;

import static Utils.Util.sign;
import Graphics.RenEntPirate;

public class Hit extends PlayerState{
    public float defaultAcceralation;
    public float acceleration;
    public float defaultHitSpeed;
    @Override
    public int getAnimationState() {
        return RenEntPirate.PAIN;
    }
    public void debug() {
        System.out.println("hit");
    }
    public void update() {
        super.update();
        player.currentSpeed.x += acceleration;
        int newSign = sign(player.currentSpeed.x);
        if(newSign * sign(acceleration) > 0) {
            player.currentSpeed.x = 0;
        }
        if(tick == duration) {
            endState();
        }
    }
    public void endState() {
        player.currentState = player.defaultState;
        player.currentSpeed.x = 0;
        player.receiveCommand = true;
    }

    @Override
    public void render(Graphics g) {

    }
    @Override
    public boolean satisfy() {
        return player.hit || (0 <   tick && tick < duration);
    }

    public Hit(Player player) {
        super(player);
        priority = HIT;
        defaultAcceralation = 0.1f;
        defaultHitSpeed = 8;
        duration = 15;
    }
    public void init() {
        super.init();
        player.hit = false;
        player.reinit = false;
        player.receiveCommand = false;
        player.currentSpeed.x = player.hitDirection*defaultHitSpeed;
        acceleration = -player.hitDirection*defaultAcceralation;
    }
}
