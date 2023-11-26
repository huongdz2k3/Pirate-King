package Entity;

import Graphics.*;
import Graphics.Tile.AniTile;
import Graphics.Tile.AniTile;
import Graphics.Tile.SailTile;
import Physics.Box.CollisionBox;
import Physics.Signal.CollisionSignalListener;
import Physics.Signal.Signal;
import States.GamePlay;
import States.GameState;
import Utils.Camera;
import Utils.Vector2D;
import main.Game;
import Graphics.Tile.Tile;
import main.GameLauncher;

import java.awt.*;
import java.awt.image.BufferedImage;

import static Graphics.TextureAtlas.getAniTileFromNameTileset;
import static Graphics.TextureAtlas.getTileFromNameTileset;

public class Ship extends Entity implements CollisionSignalListener {
    public CollisionBox colBox;
    float speed = 0;
    float acceleration = 0.02f;
    float maxSpeed = 1.5f;
    RenEntSail renEnt1;
    
    public Ship(float centreX, float centreY) {
        colBox = new CollisionBox(centreX, centreY, 100, 30, SHIP, 0);
        colBox.listeners.add(this);
        renEnt1 = new RenEntSail(0,0);
        renEnt = new RenEntHull(0,0);
        renEnt1.setOutSize(20, 65);
        renEnt.setOutSize(120, 30);
        updatePos();
    }
    public boolean update() {
        if(GamePlay.win) {
        	move();
        	EntitiesManager.getPlayer().setCentre(colBox.centre.x+20, colBox.centre.y);
            if(Camera.toCameraCoordinate(colBox.centre).x > Game.GAME_WIDTH) GameLauncher.game.winGame();
        }
        return true;
    }
    public void updatePos() {
    	float x = colBox.getCentre().x, y = colBox.getCentre().y;
    	renEnt1.setCenter(x-20, y+47);
    	renEnt.setCenter(x, y+5);
    }
    public void move() {
        speed += acceleration;
        speed = Math.min(speed, maxSpeed); 
        colBox.centre.x +=  speed;
        updatePos();
    }
    public void render(Graphics g) {
        Animator.frame(g,renEnt);
        Animator.frame(g,renEnt1);
    }

    @Override
    public void handle(Signal signal) {
        GamePlay.win = true;
        renEnt1.fullSpeed();
        colBox.listeners.set(0, new CollisionSignalListener() {
			
			@Override
			public void handle(Signal signal) {
				// TODO Auto-generated method stub
				
			}
		});
    }
}
