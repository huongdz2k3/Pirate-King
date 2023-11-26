package States;

import Entity.*;
import Entity.Living.Player.Player;
import LevelBuilder.LevelBuilder;
import Physics.World.AttackWorld;
import Physics.World.CollisionWorld;
import Utils.Camera;
import main.Game;
import Graphics.Tile.TileMap;
import Graphics.TextureAtlas;

import java.awt.*;
import java.awt.event.KeyListener;
import java.util.Vector;

public class GamePlay extends GameState{
    private EntitiesManager entitiesManager;
    public Vector<TileMap> tileMaps;
    public static boolean win;
    public GamePlay(Game game, String level) {
        init();
        LevelBuilder.buildLevel("levels/"+level, this);
        game.gamePanel.addKeyListener(EntitiesManager.player);
        // EntitiesManager.test(game.gamePanel);
        Camera.init();
        win = false;
    }

    public void init() {
        tileMaps = new Vector<>();
        AttackWorld.init();
        CollisionWorld.init();
        TextureAtlas.init();
        EntitiesManager.init();
        System.gc();
    }

    @Override
    public void update() {
        for(int i = 0; i < tileMaps.size(); i++) {
            tileMaps.get(i).update();
        }
        Camera.updateCentre();
        EntitiesManager.update();
        //if(!win) 
    }

    @Override
    public void render(Graphics g) {
        for(int i = 0; i < tileMaps.size(); i++) {
            tileMaps.get(i).render(g);
        }
        EntitiesManager.render(g);
    }

    @Override
    public void processSignal() {

    }
}
