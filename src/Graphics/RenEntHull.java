package Graphics;

import Utils.ImageFinder;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class RenEntHull extends RenderEntity {
    static private BufferedImage i;
    public RenEntHull(int state, int frame) {
        super(80, 26, state, frame);
        if(i==null) {
            InputStream is = ImageFinder.findAndOpenStream("sprites/hull.png");
            try {
                i = ImageIO.read(is);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        img=i;
        this.stateFrameLen= new int[]{1};
        this.framePerAnimation= new int[]{10000};
        this.soundList = new Clip[1];
        this.soundReset = new boolean[] {true};
        this.soundStartFrame = new int[] {0};

        try {
            for(int i=0;i<1;++i) {
                InputStream iis = ImageFinder.findAndOpenStreamCanNull("sounds/hull/"+i+".wav");
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
