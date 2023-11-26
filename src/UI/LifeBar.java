package UI;

import Utils.ImageFinder;

import javax.imageio.ImageIO;
import javax.swing.*;

import Entity.EntitiesManager;
import Entity.Living.Player.Player;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class LifeBar {
    public LifeBar(){
        getSource();
    }
    private BufferedImage img_lifeBar1;
    private BufferedImage img_lifeBar2;
    private BufferedImage img_lifeBar3;
    private BufferedImage img_mau;
    void getSource() {
        try {
            InputStream is = ImageFinder.findAndOpenStream("/UI/1.png");
            img_lifeBar1= ImageIO.read(is);
            is=ImageFinder.findAndOpenStream("/UI/barmid.png");
            img_lifeBar2=ImageIO.read(is);
            is=ImageFinder.findAndOpenStream("/UI/barEnd.png");
            img_lifeBar3=ImageIO.read(is);
            is=ImageFinder.findAndOpenStream("/UI/c_1.png");
            img_mau=ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private int mau=3;
    void hit(){
        mau--;
    }
    public void draw(Graphics g){
    	int mau=0;
    	Player p = EntitiesManager.getPlayer();
    	if(p!=null) mau = p.getHealth();
        g.drawImage(img_lifeBar1,0,0,64,50,null);
        g.drawImage(img_lifeBar2,25,0,8*Player.MAX_HEALTH,50,null);
        g.drawImage(img_lifeBar3,25+8*Player.MAX_HEALTH,0,10,50,null);
        g.drawImage(img_mau,25,20,8*mau,5,null);
    }  
}


