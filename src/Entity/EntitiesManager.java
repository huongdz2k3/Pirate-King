package Entity;

import Entity.Living.Enemy.Crab.Crab;
import Entity.Living.Enemy.Star.Star;
import Entity.Living.Player.Player;
import Panels.GamePanel;
import main.Game;
import main.GameLauncher;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class EntitiesManager {
    public static ArrayList<Entity> entities = new ArrayList<Entity>();
    public static Player player;
    public static void test(GamePanel gamePanel) {

        gamePanel.addKeyListener(player);
//         Crab crab = new Crab();
//         entities.add(crab);
//         Star star = new Star();
//         entities.add(star);

    }
    public static void addPlayer(Player _player) {
        player = _player;
        add(_player);
    }
    public static void update() {
        Iterator<Entity> iterator = entities.iterator();
        while(iterator.hasNext())
        {
            Entity entity = iterator.next();
            if(!entity.update()) {
                iterator.remove();
            }
        }
    }
    public static void render(Graphics g) {
        for(Entity entity : entities) {
            entity.render(g);
        }
    }
    public static Player getPlayer() {
        return player;
    }
    public static void add(Entity entity) {
        entities.add(entity);
        if(entity.getRenEnt()!=null) entity.getRenEnt().adjustVolume(Game.getVolume());
    }
    public static void remove(Entity entity) {entities.remove(entity);}
    public static void setPlayer(Player p) { player = p; }
    public static void init() {entities.clear();}
}
