package Physics.World;

import Physics.Box.AttackBox;
import Physics.Box.Box;
import Physics.Box.HitBox;

import java.util.HashMap;
import java.util.Vector;

import static Entity.Entity.ENEMY;
import static Entity.Entity.PLAYER;

public class AttackWorld extends World{
    public static HashMap<Integer, Vector<Box>> boxes = new HashMap<>();
    public static void print() {
        //System.out.println(boxes.size());
    }
    public static void init() {
        boxes.clear();
        boxes.put(ENEMY, new Vector<>());
        boxes.put(PLAYER, new Vector<>());
    }
    public static void add(HitBox box) {
        boxes.get(box.typeMask).add(box);
    }
    public static void remove(HitBox box) {
        boxes.get(box.typeMask).remove(box);
    }
    public static void attack(AttackBox attBox) {
        for(int i : boxes.keySet()) {
            if((attBox.collisionMask & i) != 0) {
                HitBox hitBox = (HitBox) getCollideBox(boxes.get(i), attBox);
                if(hitBox != null) {
                    int dir = (attBox.centre.x > hitBox.centre.x) ? -1 : 1;
                    hitBox.receiveDamage(attBox.getDamage(), dir);
                }
            }
        }

    }
}

