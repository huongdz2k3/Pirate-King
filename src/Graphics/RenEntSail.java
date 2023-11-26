package Graphics;

import Utils.ImageFinder;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class RenEntSail extends RenderEntity {
    static private BufferedImage i;
    public RenEntSail(int state, int frame) {
        super(28, 50, state, frame);
        if(i==null) {
            InputStream is = ImageFinder.findAndOpenStream("sprites/sail.png");
            try {
                i = ImageIO.read(is);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        img=i;
        this.stateFrameLen= new int[]{7};
        this.framePerAnimation= new int[]{10};
        this.soundList = new Clip[1];
        this.soundReset = new boolean[] {true};
        this.soundStartFrame = new int[] {0};

        try {
            for(int i=0;i<1;++i) {
                InputStream iis = ImageFinder.findAndOpenStreamCanNull("sounds/sail/"+i+".wav");
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
    
    public void fullSpeed() {
    	img = i.getSubimage(56, 0, 140, 50);
    	stateFrameLen[0]=5;
    	curFrameX=0;
    	framePerAnimation[0]=5;
    	frameCount = 0;
    }
}
