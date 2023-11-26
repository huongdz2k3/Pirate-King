package UI;

import main.Game;
import main.MyButton;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Pause extends JPanel{
    private Rectangle buttonRec=new Rectangle(250,290,30,30);
    private Rectangle buttonRec1=new Rectangle(305,290,30,30);
    private Rectangle buttonRec2=new Rectangle(360,290,30,30);
    private Rectangle buttonRec3=new Rectangle(250,250,30,30);
    private JSlider volume;
    tuanButton resume=new tuanButton(buttonRec,"assets/pause/icon/green.png");
    tuanButton on=new tuanButton(buttonRec3,"assets/pause/icon/Small Icons/13.png");
    tuanButton mute=new tuanButton(buttonRec3,"assets/pause/icon/Small Icons/14.png");


    tuanButton home=new tuanButton(buttonRec1,"assets/pause/icon/yello.png");
    tuanButton restart=new tuanButton(buttonRec2,"assets/pause/icon/green.png");
    BufferedImage background;
    public Pause() {
        volume = new JSlider(0, 0, 100, Game.getVolume());
        try{
            background= ImageIO.read(new File("assets/pause/6.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
        setLayout(null);
        setSize(640,480);
        //setPreferredSize(new Dimension(640,480));
        add(resume);
        add(home);
        add(restart);
        volume.setBounds(280,255,110,20);
        volume.setBorder(null);
        volume.setBackground(new Color(0,0,0,0));
        add(volume);
        add(mute);
        add(on);
        setBackground(new Color(0,0,0,60));
        setVisible(true) ;
    }


    private void draw(Graphics g){
        g.drawImage(background,220,140,200,200,null);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }
      /*public static void main(String argsp[]){
        Pause tuan=new Pause(new Game());
        tuan.a.setVisible(true);
        tuan.a.setSize(640,480);
        tuan.a.add(tuan);
    }*/
}
