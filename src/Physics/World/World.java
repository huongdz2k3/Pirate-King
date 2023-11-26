package Physics.World;

import Physics.Box.Box;
import Utils.Vector2D;

import java.awt.*;
import java.util.Vector;

import static Utils.Util.getRectangle;
import static Utils.Util.intersect;

public abstract class World {

    public static boolean ifCollide(Box b1, Box b2) {
        Vector2D c1 = b1.getCentre();
        Vector2D c2 = b2.getCentre();
        Rectangle r1 = getRectangle(c1.x, c1.y, b1.width, b1.height);
        Rectangle r2 = getRectangle(c2.x, c2.y, b2.width, b2.height);
        return intersect(r1, r2);

    }
    public static Box getCollideBox(Vector<Box> boxes, Box box) {
        for(Box otherBox : boxes) {
            if(box.canCollide(otherBox) && ifCollide(box, otherBox)) {
                return otherBox;
            }
        }
        return null;
    }


//    public static void attack(AttackBox attBox) {
//        attBox.updCentre();
//        for(HitBox hBox : hBoxes) {
//            if(attBox.getEntity != hBox.getEntity) {
//                hBox.updCentre();
//                if(ifCollide(attBox, hBox)) {
//                    hBox.receiveDamage(attBox.damage);
//                }
//            }
//        }
//    }

}
