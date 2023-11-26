package Entity.Living.Enemy;

import Entity.EntitiesManager;
import Entity.Living.Enemy.State.*;
import Entity.Living.Living;
import Entity.Living.Player.Player;
import Graphics.Animator;
import Physics.Signal.Signal;
import Utils.Vector2D;

import java.awt.*;
import java.util.Vector;

public class Enemy extends Living {
    public float left = 0;
    public float right = 200;
    // MOVING
    public boolean switchDirection = false;

    // FOR BACk UP
    public boolean preOnGround = false;
    public Vector2D prePos = new Vector2D(0, 0);

    // STATE
    public EnemyState currentState;
    protected EnemyState previousState;
    public EnemyState defaultState;
    protected Vector<EnemyState> states;

    public Vector2D detectRange = new Vector2D(110, 110);
    public Enemy() {
        states = new Vector<>();
        states.add(new Normal(this));
        defaultState = states.lastElement();
        states.add(new Chasing(this));
        states.add(new Falling(this));
        currentState = states.lastElement();
        states.add(new Dying(this));
        states.add(new Hit(this));
        currentState.init();
        alive = true;
    }
    // POSITION
    public void backUpPos() {
        preOnGround = onGround;
        prePos.x = collisionBox.centre.x;
        prePos.y = collisionBox.centre.y;
    }
    public void restorePos() {
        onGround = preOnGround;
        collisionBox.centre.x = 3 * prePos.x - 2 * collisionBox.centre.x;
        collisionBox.centre.y = prePos.y;
    }
    public void setCentre(Vector2D centre) {
        collisionBox.setCentre(centre);
    }
    // ATTACK

    public void move() {
        backUpPos();
        Signal signal = generalMove();
        if(preOnGround && !onGround ) {//&& currentState != states.lastElement()) {	//TODO can fall while pain
            restorePos();
            switchDirection = true;
        }
        if(signal.horizontal != 0) {
            switchDirection = true;
        }
    }

    public void die() {
        super.die();
    }
    public void setDefaultSpeed(float x, float y) {
       super.setDefaultSpeed(x, y);
    }
    public boolean update() {
        FSM();
        currentState.update();
        move();
        return alive;
    }
    public void FSM() {
        previousState = currentState;
        if(!currentState.satisfy()) {
            currentState = defaultState;
        }
        for (EnemyState state : states) {
            if (state.satisfy() && state.priority > currentState.priority) {
                currentState = state;
            }
        }
        if (currentState != previousState) {
            currentState.init();
        }
    }
    public void render(Graphics g) {
        renEnt.setCenter(this.getCentre().x, this.getCentre().y+5);
		if(this.currentSpeed.x<0)
			Animator.frame(g, renEnt);
		else if(this.currentSpeed.x>0) Animator.flipFrame(g, renEnt);
		else {
			Player p=EntitiesManager.getPlayer();
			if(p==null) Animator.frame(g,renEnt);
			else if(this.getCentreX() >= p.getCentreX()) Animator.frame(g, renEnt);
			else Animator.flipFrame(g, renEnt);
		}
		if(currentState != previousState || currentState.getAnimationState() != renEnt.getState()) {
			renEnt.setState(currentState.getAnimationState());
		}   
    }
}
