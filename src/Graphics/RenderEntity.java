package Graphics;

import java.awt.image.BufferedImage;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

import Utils.Vector2D;

public class RenderEntity {
    int[] stateFrameLen;
	int[] framePerAnimation;
    int curFrameX;
    int curFrameY;
    Vector2D pos = new Vector2D(0,0);
    int cellWidth;
    int cellHeight;
    int outWidth;
    int outHeight;
    int frameCount=0;
    BufferedImage img;
    Clip[] soundList;
    boolean[] soundReset;
    int[] soundStartFrame;
       	
    public void adjustVolume(float f) {
    	for(var i:soundList)
    	{
    		if(i == null) continue;
    		FloatControl gainControl = (FloatControl) i.getControl(FloatControl.Type.MASTER_GAIN);
    		float min = gainControl.getMinimum();
    		float max = gainControl.getMaximum();
    		gainControl.setValue(min+(max-min)/100*f);
    	}
    }
    
    public void playSound(int state) {
    	Clip cl = soundList[state];
    	Thread th=new Thread(new Runnable() {
    		@Override 
    		public void run() {	  			
    			cl.stop();
    			cl.setFramePosition(0);
    			cl.start();
    			try {
    				while(cl.isRunning())
						Thread.sleep(100);
    			} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    	});
    	th.start();
    }
    
    public void playSound() {
    	playSound(curFrameY);
    }
    
    public RenderEntity(int cellWidth, int cellHeight, int state, int frame)
    {
        this.cellHeight = cellHeight;
        this.cellWidth = cellWidth;
        this.curFrameX = state;
        this.curFrameY = frame;
		this.outHeight = cellHeight;
		this.outWidth = cellWidth;
    }

    public void setOutSize(int width, int height) {
        outWidth = width;
        outHeight = height;
    }

    public void setCenter(float x, float y) {
        pos.x=x-outWidth/2;
        pos.y=y+outHeight/2;
    }

    public void setState(int state) {
    	if(soundList[curFrameY]!=null && !soundReset[curFrameY])soundList[curFrameY].stop(); 	
        this.curFrameY = state;
        this.curFrameX = 0;
		this.frameCount=0;
		if(soundReset[curFrameY]) return;
		if(soundList[curFrameY]!=null && !soundReset[curFrameY])
		{
			 Clip cl = soundList[curFrameY];			 
			 Thread th=new Thread(new Runnable() {
		    		@Override 
		    		public void run() {	  			
		    			cl.start();
		    			cl.loop(Clip.LOOP_CONTINUOUSLY);
		    			try {
		    				while(cl.isRunning())
								Thread.sleep(100);
		    			} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		    		}
		    	});
		    	th.start();
		}
			
				
    }

	 public int getState() {
		 return this.curFrameY;
	 }

	 public void setStateSpeed(int state, int framePerAnimation) {
		 this.framePerAnimation[state] = framePerAnimation;
		 if(curFrameY == state)	 this.frameCount%=framePerAnimation;
	 }

	 public int getStateDuration(int state) {
		 return stateFrameLen[state]*framePerAnimation[state];
	 }
}
