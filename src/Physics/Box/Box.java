package Physics.Box;

import Entity.Entity;
import Entity.Living.Living;
import Utils.Artist;
import Utils.Vector2D;

import java.awt.*;

public abstract class Box {
    public Vector2D centre;
    public float width, height;
    public int typeMask;
    public int collisionMask;
    protected Living entity;
    public Box() {
        centre = new Vector2D(0, 0);
    }
    public boolean canCollide(Box otherBox) {

        if((otherBox.typeMask & collisionMask) != 0) {
            return true;
        }
        return false;
    }
    public abstract Vector2D getCentre();
    public void setCentre() {}
    public void render(Graphics g) {
        Artist.draw(g, centre.x, centre.y, width, height, new Color(0, 125, 0));

    }
}
