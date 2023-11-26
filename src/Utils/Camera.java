package Utils;

import Entity.EntitiesManager;
import Entity.Living.Player.Player;
import LevelBuilder.LevelBuilder;
import main.Game;

import java.util.Vector;

import static Utils.Artist.camera;
import static Utils.Util.cap;

public class Camera {
    private static float width = Game.GAME_WIDTH;
    private static float height = Game.GAME_HEIGHT;
    private static Vector2D centre = new Vector2D(0, 0);
    private static Vector2D offset = new Vector2D(300, 100);
    private static float minX, maxX, minY, maxY;
    public static void init() {
        minX = width/2;
        maxX = LevelBuilder.levelWidth - width/2;
        minY = height/2;
        maxY = LevelBuilder.levelWidth - height/2;
    }
    public static void updateCentre() {
        Player player = EntitiesManager.getPlayer();
        if(player == null) {
            return;
        }
        Vector2D playerCentre = player.getCentre();
        centre.x = playerCentre.x;
        centre.x = cap(centre.x, minX, maxX);
        centre.y = cap(centre.y, playerCentre.y - offset.y, playerCentre.y + offset.y);
        centre.y = cap(centre.y, minY, maxY);
    }
    public static Vector2D toCameraCoordinate(Vector2D position) {
        Vector2D res = new Vector2D(0, 0);
        res.x = position.x - (centre.x - width/2);
        res.y = (centre.y + height/2) - position.y;
        return res;
    }
}
