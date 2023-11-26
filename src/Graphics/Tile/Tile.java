package Graphics.Tile;

import Utils.Artist;
import Utils.Vector2D;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;
import Graphics.TextureAtlas;

import static LevelBuilder.LevelBuilder.levelWidth;

public class Tile {
    public float speed = 0;
    public Vector2D centre;
    public Vector2D lowerLeft;
    public BufferedImage image;
    public float width;
    public float height;

    public Tile() {}
    public Tile(Vector2D lowerLeft, int value) {
        this.lowerLeft = lowerLeft;
        image = TextureAtlas.getTileImage(value);
        width = image.getWidth();
        height = image.getHeight();
        this.centre =  new Vector2D(lowerLeft.x + width/2, lowerLeft.y+ height/2);
    }
    public Tile(Vector2D lowerLeft, BufferedImage image) {
        this.lowerLeft = lowerLeft;
        this.image = image;
        width = image.getWidth();
        height = image.getHeight();
        this.centre =  new Vector2D(lowerLeft.x + width/2, lowerLeft.y+ height/2);
    }

    public void render(Graphics g) {
        Artist.drawImage(g, centre, image);
    }
    public void update() {
        centre.x += speed;
        if(centre.x > levelWidth +  width/2) {
            centre.x -= (levelWidth + width);
        }
        if(centre.x < -width/2) {
            centre.x += (levelWidth + width);
        }
    }

}
