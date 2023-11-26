package Physics.Box;

import Physics.Signal.CollisionSignalListener;
import Physics.Signal.Signal;
import Physics.World.CollisionWorld;
import Utils.Vector2D;

import java.util.Vector;

public class CollisionBox extends Box {
    public Vector<CollisionSignalListener> listeners;
    public CollisionBox(float centreX, float centreY, float width, float height, int typeMask, int collisionMask) {
        super();
        centre = new Vector2D(centreX, centreY);
        this.width = width;
        this.height = height;
        this.typeMask = typeMask;
        this.collisionMask = collisionMask;
        CollisionWorld.add(this);
        listeners = new Vector<>();
    }
    // COLLISION
    public Signal detectCollision(Vector2D speed) {
        return CollisionWorld.detectCollision(this, speed);
    }

    // POSITION
    @Override
    public Vector2D getCentre() {
        return centre;
    }
    public void setCentre(Vector2D centre) {
        this.centre = centre;
    }
    public void receiveSignal(Signal signal) {
        for(CollisionSignalListener listener : listeners) {
            listener.handle(signal);
        }
    }
}
