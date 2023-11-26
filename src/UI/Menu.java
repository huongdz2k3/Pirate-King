package UI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import Utils.ImageFinder;
public class Menu implements MouseListener, MouseMotionListener {
    final String srcFrame = "./frameButton.png";
    final String srcButton = "./button.png";
    final int positionFrameX = 0;
    final int positionFrameY = 0;
    final int positionFirstButtonX = 0;
    final int positionFirstButtonY = 0;
    final int sizeButtonX = 0;
    final int sizeButtonY = 0;
    final int spacingButton = 0;
    final int PLAY = 1;
    final int OPTION = 2;
    final int QUIT = 3;
    BufferedImage imgFrame, imgPlay, imgOption, imgQuit;


    private boolean check(int x, int y, int state) {
        int topButton = positionFirstButtonY + (state - 1) * (sizeButtonY + spacingButton);
        if (positionFirstButtonX < x && x < positionFirstButtonX + sizeButtonX)
            if (topButton < y && y < topButton + sizeButtonY)
                return true;
        return false;
    }

    private void setZero() {
        for (int ele : frameButton) {
            ele = 0;
        }
    }

    private void buttonHover(int x, int y) {
        setZero();
        if (check(x, y, PLAY))
            frameButton[PLAY]++;
        if (check(x, y, OPTION))
            frameButton[OPTION]++;
        if (check(x, y, QUIT))
            frameButton[QUIT]++;
    }

    private int lastHover = 0;
    private int frameButton[] = {0, 0, 0, 0};

    private void getImage() {
        InputStream is;
        try {
            is = getClass().getResourceAsStream(srcFrame);
            imgFrame = ImageIO.read(is);
            is = getClass().getResourceAsStream(srcButton);
            BufferedImage imgButton = ImageIO.read(is);
            imgPlay = imgButton.getSubimage(0, 0, sizeButtonX * 3, sizeButtonY);
            imgOption = imgButton.getSubimage(0, sizeButtonY, sizeButtonX * 3, sizeButtonY);
            imgQuit = imgButton.getSubimage(0, sizeButtonY * 2, sizeButtonX * 3, sizeButtonX);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Menu() {
        getImage();
        setDimension();
    }
    private BufferedImage getFrameButton(BufferedImage img,int frame){
        return img.getSubimage(frame*sizeButtonX,0,sizeButtonX,sizeButtonY);
    }
    private void reDraw(Graphics g) {
        g.drawImage(imgFrame,positionFrameX,positionFrameY,null);
        g.drawImage(getFrameButton(imgPlay,frameButton[PLAY]),positionFirstButtonX,positionFrameY,null);
        g.drawImage(getFrameButton(imgOption,frameButton[OPTION]),positionFirstButtonX,positionFrameY+sizeButtonY+spacingButton,null);
        g.drawImage(getFrameButton(imgOption,frameButton[OPTION]),positionFirstButtonX,positionFrameY+(sizeButtonY+spacingButton)*2,null);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        buttonHover(e.getX(), e.getY());
    }
    JFrame jf=new JFrame("Menu");
    JPanel jp=new JPanel();
    private void setDimension(){
        Dimension d=new Dimension(640,480);
        jp.setPreferredSize(d);
    }
    public static  void main(String args[]){
        Menu menu=new Menu();
    }
}
