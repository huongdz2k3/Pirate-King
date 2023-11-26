package Graphics;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import Utils.ImageFinder;

public class RenEntCrab extends RenderEntity {
	static final int DEFAULT = 0;
	static final int RUN = 1;
	static final int ATTACK = 2;
	static final int HITTED = 3;
	static final int DIE = 4;

	static private BufferedImage i;
	public RenEntCrab(int state, int frame) {
		super(72, 32, state, frame);
		if(i==null) {
			InputStream is = ImageFinder.findAndOpenStream("sprites/crabby_sprite.png");
			try {
				i = ImageIO.read(is);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		img=i;
		this.stateFrameLen= new int[]{9,6,7,4,9};
		this.framePerAnimation= new int[]{5,5,5,5,5};
		this.soundList = new Clip[5];
		this.soundReset = new boolean[] {false, false, true, true, true};
		this.soundStartFrame = new int[] {0,0,5,1,0};
		
		try {
			for(int i=0;i<5;++i) {
				InputStream iis = ImageFinder.findAndOpenStreamCanNull("sounds/crab/"+i+".wav");
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
