package Entity;

import Physics.Box.Box;
import Physics.Box.CollisionBox;
import Utils.Artist;

import java.awt.*;
import java.util.Vector;

import static Utils.Util.getRectangle;
import static main.Game.*;

public class Environment extends Entity{
    private Vector<CollisionBox> boxes;
    public Environment() {
        boxes = new Vector<>();

    }
    public void add(CollisionBox box) {
        boxes.add(box);
    }

    @Override
    public void render(Graphics g) {
//        for(Box box : boxes) {
//            // Rectangle rect = getRectangle(box.centre.x, box.centre.y, box.width, box.height);
//            Artist.draw(g, box.centre.x, box.centre.y, box.width, box.height, new Color(0, 0, 0));
//
//        }
    }

    public boolean update() {
        return true;
    }

}
