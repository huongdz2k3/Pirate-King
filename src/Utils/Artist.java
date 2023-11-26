package Utils;

import Entity.EntitiesManager;
import LevelBuilder.LevelBuilder;
import jdk.jfr.consumer.RecordedStackTrace;
import main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Vector;

import static Utils.Util.getRectangle;

public class Artist {
    public static final int HEIGHT = Game.GAME_HEIGHT;
    public static Vector2D camera = new Vector2D(HEIGHT/2, HEIGHT/2);
    public static float offsetX = 50;
    public static float offsetY = 80;
    public static void draw(Graphics g, float cX, float cY, float width, float height, Color color) {
        Vector2D v = Camera.toCameraCoordinate(new Vector2D(cX, cY));
        g.setColor(color);
        g.fillRect((int) (v.x - width/2), (int)(v.y - height/2), (int)width, (int)height);
    }
    public static void drawImage(Graphics g, Vector2D centre, Image image) {
        centre = Camera.toCameraCoordinate(centre);
        g.drawImage(image, (int) (centre.x - image.getWidth(null)/2), (int) (centre.y - image.getHeight(null)/2), null);

    }
}
