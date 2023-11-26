package Entity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Background {
    private BufferedImage img;
    private BufferedImage background;
    private BufferedImage bigCloud;
    private BufferedImage mediumCloud;
    private BufferedImage smallCloud;
    private BufferedImage water;
    private void io() {
        InputStream input = this.getClass().getResourceAsStream("/background.png");
        try {
            this.img = ImageIO.read(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void getElementFromImg(){
        background=img.getSubimage(63,0,422,125);
        bigCloud=img.getSubimage(0,128,448,101);
        smallCloud=img.getSubimage(0,229,77,28);
        mediumCloud=img.getSubimage(76,229,133,36);
        water=img.getSubimage(0,268,447,23);
    }
    private void draw(Graphics g){
        g.drawImage(background,0,0,640,480, null);
    }
    public Background(Graphics g){
        io();
        getElementFromImg();
    }

}
