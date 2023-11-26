package Graphics;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import Utils.ImageFinder;

public class RenEntStar extends RenderEntity {
	static final int DEFAULT = 0;
	static final int RUN = 1;
	static final int ATTACK = 2;
	static final int HITTED = 3;
	static final int DIE = 4;

	static private BufferedImage i;
	public RenEntStar(int state, int frame) {
		super(34, 30, state, frame);
		if(i==null) {
			InputStream is = ImageFinder.findAndOpenStream("sprites/star_sprites.png");
			try {
				i = ImageIO.read(is);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		img=i;
		//TODO
		this.stateFrameLen= new int[]{8,6,7,4,6};
		this.framePerAnimation= new int[]{5,5,5,5,5};
		this.soundList = new Clip[5];
		this.soundReset = new boolean[]{false, false, true, true, true, true, false};
		this.soundStartFrame = new int[] {0,0,0,1,0};
		
		try {
			for(int i=0;i<5;++i) {
				InputStream iis = ImageFinder.findAndOpenStreamCanNull("sounds/star/"+i+".wav");
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
