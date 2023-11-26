package main;

import Listener.Mouse;
import Utils.ImageFinder;
import com.sun.tools.javac.Main;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.ImageFilter;
import java.awt.image.Kernel;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class MainMenu extends JPanel {
    public static MainMenu m;
    static
    {
        BufferedImage tmp=null;
        File f = ImageFinder.findFileNotNull("BackGroundMenu.png");
        try {
            tmp =ImageIO.read(f);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int radius = 11;
        int size = radius * 2 + 1;
        float weight = 1.0f / (size * size);
        float[] data = new float[size * size];

        for (int i = 0; i < data.length; i++) {
            data[i] = weight;
        }

        Kernel kernel = new Kernel(size, size, data);
        ConvolveOp op = new ConvolveOp(kernel, ConvolveOp.EDGE_ZERO_FILL, null);
        //tbi is BufferedImage
        backgroundImage = op.filter(tmp, null);

        m = new MainMenu();
    }
    public Game game;

    JLabel jlabel;
    public static int GAMEWIDTH = 640;
    public static int GAMEHEIGHT = 480;
    public static final int BUTTON_WIDTH = 225;
    public static final int BUTTON_HEIGHT = 85;
    private static BufferedImage backgroundImage;

    public MainMenu() {
        addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                GAMEWIDTH = Integer.parseInt(e.getSource().toString().split(",")[3].split("x")[0]);
                GAMEHEIGHT = Integer.parseInt(e.getSource().toString().split(",")[3].split("x")[1]);
                createFeatureButton();
            }

            @Override
            public void componentMoved(ComponentEvent e) {

            }

            @Override
            public void componentShown(ComponentEvent e) {

            }

            @Override
            public void componentHidden(ComponentEvent e) {

            }
        });
        createUI();
        setSize(new Dimension(Game.GAME_WIDTH, Game.GAME_HEIGHT));
        setVisible(true);
        setBackground(new Color(100, 100, 100));
        setLayout(null);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, null);
    }

    private void createUI() {
//        createMenuBar();
        createFeatureButton();
    }

    void createFeatureButton() {
        if (jlabel != null) {
            remove(jlabel);
        }
        jlabel = new JLabel();
        jlabel.setBounds(new Rectangle(GAMEWIDTH, GAMEHEIGHT));

        MyButton newgame = new MyButton(new Rectangle((GAMEWIDTH - BUTTON_WIDTH) / 2, 50, BUTTON_WIDTH, BUTTON_HEIGHT), ImageFinder.findFileNotNull("playbutton2.png"));
        MyButton option = new MyButton(new Rectangle((GAMEWIDTH - BUTTON_WIDTH) / 2, 150, BUTTON_WIDTH, BUTTON_HEIGHT), ImageFinder.findFileNotNull("optionbutton.png"));
        MyButton about = new MyButton(new Rectangle((GAMEWIDTH - BUTTON_WIDTH) / 2, 250, BUTTON_WIDTH, BUTTON_HEIGHT), ImageFinder.findFileNotNull("aboutbutton.png"));
        MyButton exit = new MyButton(new Rectangle((GAMEWIDTH - BUTTON_WIDTH) / 2, 350, BUTTON_WIDTH, BUTTON_HEIGHT), ImageFinder.findFileNotNull("exitbutton.png"));

        newgame.addMouseListener(Mouse.startGame);
        option.addMouseListener(Mouse.Option);
        about.addMouseListener(Mouse.about);
        exit.addMouseListener(Mouse.exit);

        newgame.setMnemonic(KeyEvent.VK_N);
        option.setMnemonic(KeyEvent.VK_O);
        about.setMnemonic(KeyEvent.VK_A);
        exit.setMnemonic(KeyEvent.VK_X);

        jlabel.add(newgame);
        jlabel.add(option);
        jlabel.add(about);
        jlabel.add(exit);

        add(jlabel);
    }


}





