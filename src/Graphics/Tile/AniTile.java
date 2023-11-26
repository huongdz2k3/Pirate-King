package Graphics.Tile;

import Utils.Vector2D;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Vector;
import Graphics.TextureAtlas;
import static LevelBuilder.LevelBuilder.levelWidth;

public class AniTile extends Tile {
    public Vector<BufferedImage> images;
    public static int tickPerFrame = 20;
    public int currentTick;
    public int currentFrameId;
    public int aniMode = 0;
    public AniTile(Vector2D lowerLeft, int value) {
        this.lowerLeft = lowerLeft;
        images = TextureAtlas.getAniTile(value);
        image = images.get(0);
        width = image.getWidth();
        height = image.getHeight();
        centre =  new Vector2D(lowerLeft.x + width/2, lowerLeft.y+ height/2);
        currentTick = tickPerFrame;
        // System.out.println(value);
    }
    public AniTile(Vector2D lowerLeft, Vector<BufferedImage> images) {
        this.lowerLeft = lowerLeft;
        this.images = images;
        image = images.get(0);
        width = image.getWidth();
        height = image.getHeight();
        centre =  new Vector2D(lowerLeft.x + width/2, lowerLeft.y+ height/2);
        currentTick = tickPerFrame;
    }
    public void updImage() {
        currentTick --;
        if(currentTick == 0) {
            currentTick = tickPerFrame;
            currentFrameId = (aniMode == 0) ? (currentFrameId + 1) % images.size() : Math.min(currentFrameId, images.size()-1);
            image = images.get(currentFrameId);
        }
    }
    public void update() {
        super.update();
        updImage();
    }

}
