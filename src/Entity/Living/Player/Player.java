package Entity.Living.Player;

import Entity.Living.Living;
import Entity.Living.State.State;
import LevelBuilder.LevelBuilder;
import Physics.Box.AttackBox;
import Physics.Box.CollisionBox;
import Physics.Box.HitBox;
import Utils.Artist;
import Utils.Camera;
import Utils.Vector2D;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;
import Graphics.*;
import main.Game;
import main.GameLauncher;

public class Player<puclic> extends Living implements KeyListener {
    boolean movingLeft=false;
    boolean movingRight=false;
    boolean lastMove=false;
    // WARNING: initialize must be collision free
    private boolean attacking = false;
    private AttackBox attackBox;
    private float speedBoost=6;
    private boolean boost = false;
    // STATE
    public PlayerState currentState;
    public PlayerState previousState;
    public PlayerState defaultState;
    public Vector<PlayerState> states;
    static public final int MAX_HEALTH=20;

    public boolean receiveCommand = true;
    public boolean reinit = false;
    public Player(float centreX, float centreY) {
        renEnt = new RenEntPirate(0, 0);
        renEnt.setOutSize(80, 48);
        defaultSpeed = new Vector2D(5, 25);
        currentSpeed = new Vector2D(0, 0);
        collisionBox = new CollisionBox(centreX, centreY, 32, 32, PLAYER, ENV|ENEMY|SHIP);
        attackBox = new AttackBox(this, 30, 30, PLAYER, ENEMY);
        attackBox.damage = 2;
        hitBox = new HitBox(this, 32, 32, PLAYER);
        states = new Vector<>();
        Idle idle = new Idle(this);
        states.add(idle);
        OnAir onAir = new OnAir(this);
        states.add(onAir);
        states.add(new Run(this));
        states.add(new Die(this));
        states.add(new Hit(this));
        states.add(new Attack(this));
        defaultState = idle;
        currentState = onAir;
        freeToSwitchState = true;
        health = MAX_HEALTH;
    }
    public void refresh() {
        currentSpeed.x = 0;
    }
    public void processCommand() {
        if(!receiveCommand) {
            return;
        }
        if(movingRight && !movingLeft) {
            currentSpeed.x = defaultSpeed.x;
        } else if(movingLeft && !movingRight) {
            currentSpeed.x = -defaultSpeed.x;
        }
        if(allowJumping && jumping) {
            allowJumping = false;
            currentSpeed.y = defaultSpeed.y;
        }
    }
    int debug = 0;
    public boolean update() {
        processCommand();
        FSM();
        currentState.update();
        generalMove();
        renEnt.setCenter((int)this.getCentre().x, (int)(this.getCentre().y));
        if(!currentState.equals(previousState))
            renEnt.setState(currentState.getAnimationState());
        return alive;
    }
    @Override
    public void render(Graphics g) {
        if(lastMove) Animator.frame(g, renEnt);
        else Animator.flipFrame(g,renEnt);
    }
    public void resetDir() {
        movingLeft = false;
        movingRight = false;
    }
    public void move() {

    }

    public void FSM() {
        previousState = currentState;
        if(!currentState.satisfy()) {
            currentState = defaultState;
        }
        for (PlayerState state : states) {
            if (state.satisfy() && state.priority > currentState.priority) {
                currentState = state;
            }
        }
        if (currentState != previousState || reinit) {
            currentState.init();
        }
    }
    public void endgame() {
        GameLauncher.game.endGame();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_P:
                if(GameLauncher.game.RUNNING)
                    GameLauncher.game.pauseLogic();
                else GameLauncher.game.continueGame();
                break;

            case KeyEvent.VK_ESCAPE:
//                GameLauncher.game.pauseGameUI();
                GameLauncher.game.toMenu();
                //GameLauncher.game.toMenu();
            	break;

            case KeyEvent.VK_D:
                movingRight = true;
                lastMove = true;
                direction = 1;
                break;
            case KeyEvent.VK_A:
                movingLeft = true;
                lastMove = false;
                direction = -1;
                break;
            case KeyEvent.VK_W:
                jumping = true;
                break;
            case KeyEvent.VK_SPACE:
                attack = true;
                break;
            case KeyEvent.VK_F:
                hit = true;
                reinit = true;
                hitDirection = -1;
                break;
            case KeyEvent.VK_G:
                hit = true;
                reinit = true;
                hitDirection = 1;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_D:
                movingRight = false;
                break;
            case KeyEvent.VK_A:
                movingLeft = false;
                break;
            case KeyEvent.VK_W:
                jumping = false;
                break;
            case KeyEvent.VK_SPACE:
                attack = false;
                break;
            case KeyEvent.VK_F:
                hit = false;
                reinit = false;
                break;
            case KeyEvent.VK_G:
                hit = false;
                reinit = false;
                break;
        }
    }

}
