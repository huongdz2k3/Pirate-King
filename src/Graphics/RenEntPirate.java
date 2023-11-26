package Graphics;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import Utils.ImageFinder;

public class RenEntPirate extends RenderEntity {
    static public final int STAND=0;
    static public final int RUN_JUMP=1;
    static public final int JUMP=2;
    static public final int JUMP_BACK=3;
    static public final int SLASH=4;
    static public final int PAIN=5;
    static public final int DIE=6;

    public RenEntPirate(int state, int frame) {
        super(64,40, state,frame);
        InputStream is=ImageFinder.findAndOpenStream("sprites/player_sprites.png");
        try {
            img= ImageIO.read(is);
        } catch(IOException e) {
            e.printStackTrace();
        }
        this.stateFrameLen = new int[]{5,6,3,1,3,3,7};
        this.soundReset = new boolean[]{false, false, true, true, true, true, true};
		this.framePerAnimation = new int[]{5,5,5,5,7,10,10};
		this.soundList = new Clip[7];
		this.soundStartFrame = new int[] {0,0,1,1,0,0,0};
		try {
			for(int i=0;i<7;++i) {
				InputStream iis = ImageFinder.findAndOpenStreamCanNull("sounds/player/"+i+".wav");
				if(iis != null) {
					soundList[i] = AudioSystem.getClip();
					soundList[i].open(AudioSystem.getAudioInputStream(new BufferedInputStream(iis)));
					if(!this.soundReset[i]) {
						soundList[i].setLoopPoints(0, -1);
					}
				}
			}
		} catch (Throwable e) {
			System.err.println("Audio kho qua: " + e.getMessage());
			e.printStackTrace();
		}
    }
}
