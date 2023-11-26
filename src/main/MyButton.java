package main;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

public class MyButton extends JButton {

    public MyButton(Rectangle r, File f) {
        setContentAreaFilled(false);
        setBorderPainted(false);
        ImageIcon icon;
        setBounds(r);
        try {
            icon = new ImageIcon(ImageIO.read(f));
            Image img = icon.getImage();
            Image imgnew =img.getScaledInstance(MainMenu.BUTTON_WIDTH, MainMenu.BUTTON_HEIGHT, Image.SCALE_SMOOTH);
            icon= new ImageIcon(imgnew);
            setIcon(icon);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}