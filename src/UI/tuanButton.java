package UI;

import javax.imageio.ImageIO;
import javax.swing.*;

import Utils.ImageFinder;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class tuanButton extends JButton {
    File f;
    ImageIcon img;

    public tuanButton(Rectangle rec,String path){
        try{
            System.out.println(path);
            f=ImageFinder.findFileNotNull(path);
            Image img=ImageIO.read(f);
            img=img.getScaledInstance(rec.width,rec.height,Image.SCALE_SMOOTH);
            this.img=new ImageIcon(img);
        }catch(IOException e){
            e.printStackTrace();
        }
        setBorder(null);
        setContentAreaFilled(false);
        setBounds(rec);
        setIcon(this.img);
    }

}
