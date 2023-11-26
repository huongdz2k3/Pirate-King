package Physics.World;

import Physics.Box.Box;
import Physics.Box.CollisionBox;
import Physics.Signal.CollisionSignalListener;
import Physics.Signal.Signal;
import Utils.Comparator;
import Utils.Vector2D;

import java.util.Vector;

import static Physics.Signal.Signal.*;

public class CollisionWorld extends World{
    private static final float STEP_SIZE = 32;
    public static Vector<Box> boxes = new Vector<Box>();
    public static void add(Box box) {
        boxes.add(box);
    }
    public static Signal moveAStep(CollisionBox box, Vector2D step) {
        Signal signal = new Signal();
        Vector2D oldCentre = new Vector2D(box.centre);
        CollisionBox collideBox;
        box.centre.y += step.y;
        collideBox = (CollisionBox) getCollideBox(boxes, box);
        if(collideBox != null) {
            float deltaY = (collideBox.height + box.height) / 2;
            if (Comparator.compareFloat(oldCentre.y, collideBox.centre.y) >= 0) {
                box.centre.y = collideBox.centre.y + deltaY;
                signal.vertical = DOWN;
            } else {
                box.centre.y = collideBox.centre.y - deltaY;
                signal.vertical = UP;
            }
            collideBox.receiveSignal(new Signal(0, signal.vertical));
        }
        box.centre.x += step.x;
        collideBox = (CollisionBox) getCollideBox(boxes, box);
        if(collideBox != null) {
            float deltaX = (collideBox.width + box.width)/2;
            if(Comparator.compareFloat(oldCentre.x,  collideBox.centre.x) >= 0) {
                box.centre.x = collideBox.centre.x + deltaX;
                signal.horizontal = LEFT;
            } else {
                box.centre.x = collideBox.centre.x - deltaX;
                signal.horizontal = RIGHT;
            }
            collideBox.receiveSignal(new Signal(signal.horizontal, 0));
        }

        return signal;
    }
    public static Signal detectCollision(CollisionBox box, Vector2D speed) {
        float targetLength = speed.getLength();

        Vector2D step = new Vector2D(speed.x/targetLength*STEP_SIZE, speed.y/targetLength*STEP_SIZE);
        float stepLength = step.getLength();

        float currentLength = 0;
        Vector2D currentDelta = new Vector2D(0, 0);
        Vector2D originalCentre = new Vector2D(box.centre);
        boolean ok = false;
        Signal lastSignal;
        while(true) {
            if(Comparator.compareFloat(currentLength + stepLength, targetLength) >= 0) {
                step = Vector2D.minus(speed, currentDelta);
                currentLength = targetLength;
                ok = true;
            } else {
                currentLength += stepLength;
                currentDelta.add(step);
            }
            lastSignal = moveAStep(box, step);
            if(ok) {
                break;
            }
        }
        return lastSignal;
    }
    public static void remove(CollisionBox box) {
        boxes.remove(box);
    }
    public static void init() {boxes.clear();}
}
