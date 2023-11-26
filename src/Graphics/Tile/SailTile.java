package Graphics.Tile;

import Utils.Vector2D;

import java.awt.image.BufferedImage;
import java.util.Vector;

public class SailTile extends AniTile{
    int catchWindThreshold;
    int fullWindCount;
    public SailTile(Vector2D lowerLeft, Vector<BufferedImage> images) {
        super(lowerLeft, images);
        catchWindThreshold = 2;
        fullWindCount = images.size() - catchWindThreshold;
    }
    public void updImage() {
        currentTick --;
        if(currentTick == 0) {
            currentTick = tickPerFrame;
            currentFrameId ++;
            if(currentFrameId >= catchWindThreshold) {
                currentFrameId = catchWindThreshold + (currentFrameId-catchWindThreshold)%fullWindCount;
            }
            image = images.get(currentFrameId);
        }
    }
}
