package Graphics;

import Utils.Camera;
import Utils.Vector2D;

import java.awt.*;

public class Animator {
	static private void inc(RenderEntity e) {
		if(e.soundReset[e.getState()] && e.frameCount==0 && e.curFrameX == e.soundStartFrame[e.getState()] && e.soundList[e.getState()] != null) {
        	e.playSound();
        }
        
        if(++e.frameCount == e.framePerAnimation[e.curFrameY]) {
            e.curFrameX++;
            e.curFrameX%=e.stateFrameLen[e.curFrameY];
            e.frameCount=0;
        }
	}
	
    static public void flipFrame(Graphics g, RenderEntity e) {
        Vector2D pos;
        pos=Camera.toCameraCoordinate(e.pos);
        g.drawImage(e.img,
                (int)pos.x, (int)pos.y,
                (int)pos.x + e.outWidth,
                (int)pos.y + e.outHeight,
                e.curFrameX * e.cellWidth + e.cellWidth,
                e.curFrameY * e.cellHeight,
                e.curFrameX * e.cellWidth,
                e.curFrameY * e.cellHeight + e.cellHeight,
                null);

        inc(e);
    }
    static public void frame(Graphics g, RenderEntity e) {
        Vector2D pos;
        pos=Camera.toCameraCoordinate(e.pos);
        g.drawImage(e.img,
                (int)pos.x, (int)pos.y,
                (int)pos.x + e.outWidth,
                (int)pos.y + e.outHeight,
                e.curFrameX * e.cellWidth,
                e.curFrameY * e.cellHeight,
                e.curFrameX * e.cellWidth + e.cellWidth,
                e.curFrameY * e.cellHeight + e.cellHeight,
                null);
        
        inc(e);
    }
}
