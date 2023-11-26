package Entity.Living.Enemy.State;

import Entity.Living.Enemy.Enemy;
import Entity.Living.State.State;

import java.awt.*;

public abstract class EnemyState extends State {
    public static final int IDLE = 0;
    public static final int NORMAL = 1;
    public static final int CHASING = 2;
    public static final int ATTACK = 3;
    public static final int DYING = 4;
    public static final float FALLING = 3.5F;
    public static final float HIT = 3.75F;

    public Enemy enemy;
    public EnemyState(Enemy enemy) {
        this.enemy = enemy;
    }
}
