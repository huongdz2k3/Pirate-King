package main;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import Utils.ImageFinder;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageFilter;
import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

public class MyDialog extends JDialog{
    public static JSlider jSlider2;
    public static JSlider jSlider;
    public static JTextField jTextField;
    public static JTextField jTextField2;
    public static MyDialog jDialogPlay;
    public static MyDialog jDialogOption;
    public static MyDialog jDialogAbout;
    public static String level=null;
    public MyDialog(JFrame jFrame,String title){
        super(jFrame,title);
        setLayout(null);
        setBounds(0,0,Game.GAME_WIDTH/2,Game.GAME_HEIGHT/2);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        if(Objects.equals(title, "OPTION")){
            resizeandaddcomponent();
        }
    }
    public void addJComboBoxLevel(){
    	File file = ImageFinder.findFileNotNull("levels");
        JComboBox j = new JComboBox<>(file.list());
        MyDialog.level=file.list()[0];
        j.setVisible(true);
        j.setBounds(Game.GAME_WIDTH/4-80,60,160,40);
        add(j);
        j.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                level = Objects.requireNonNull(j.getSelectedItem()).toString();
            }
        });
    }
    public void addButtonPlay(){
        JButton j = new JButton("LET'S GO");
        j.setBounds(Game.GAME_WIDTH/4-50,110,100,30);
        j.addMouseListener(Listener.Mouse.LETSGO);
        add(j);
    }
    public void addLevelComponent(){
        addButtonPlay();
        addJComboBoxLevel();
    }
    public void addJTextSoundComponent(){
        addJSliderSound();
        addJTextFieldSound();
        addSoundButton();
    }
    public void addINFOGroup(){
        JTextArea j = new JTextArea();
        j.setText("NHÓM 6:\nBÙI THẾ HƯỚNG\nTRẦN MINH HIẾU\nHÀ VIỆT HOÀNG\nĐÀM TIẾN QUÂN\nNGUYỄN HOÀNG HẢI");
        j.setEditable(false);
        add(j);
        j.setBounds(0,0,400,400);
        j.setFont(new Font("Consolas",Font.BOLD,25));
    }
    public void addJTextFieldSound(){
        jTextField = new JTextField("VOLUME : "+Game.getVolume());
        jTextField.setBounds(Game.GAME_WIDTH/4-70,30,140,20);
        jTextField.setFont(new Font("Default",Font.BOLD,16));
        add(jTextField);
        jTextField.setBorder(null);
        jTextField.setForeground(Color.BLACK);
        jTextField.setEditable(false);
    }
    public void addJTextFieldSound2(){
        jTextField2 = new JTextField("BG VOLUME : "+Game.getBgVolume());
        jTextField2.setBounds(Game.GAME_WIDTH/4-90,130,180,20);
        jTextField2.setFont(new Font("Default",Font.BOLD,16));
        add(jTextField2);
        jTextField2.setBorder(null);
        jTextField2.setForeground(Color.BLACK);
        jTextField2.setEditable(false);
    }
    public void addJSliderSound(){

        jSlider= new JSlider(JSlider.HORIZONTAL,0,100,50);
        jSlider.setValue(Game.getVolume());
        jSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Game.adjustVolume(jSlider.getValue()); 
                jTextField.setText("VOLUME : "+Game.getVolume());
            }
        });
        jSlider.setBounds(30,60,260,60);
        add(jSlider);
        jSlider.setVisible(true);
        jSlider.setMajorTickSpacing(20);
        jSlider.setMinorTickSpacing(5);
        jSlider.setPaintTicks(true);
        jSlider.setPaintTrack(true);
        jSlider.setPaintLabels(true);
        jSlider.setFont(new Font("Default",Font.ITALIC,15));
    }

    public void addSoundButton(){
        JButton jButton1 = new JButton("APPLY");
        jButton1.setBounds(40,230,100,30);
        add(jButton1);
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        JButton jButton2 = new JButton("DEFAULT");
        jButton2.setBounds(180,230,100,30);
        jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jSlider.setValue(50);
                Game.adjustVolume(50);
                jSlider2.setValue(50);
                Game.adjustBgVolume(50);
            }
        });
        add(jButton2);
    }
    public void addJSliderSound2(){

        jSlider2= new JSlider(JSlider.HORIZONTAL,0,100,50);
        jSlider2.setValue(Game.getBgVolume());
        jSlider2.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Game.adjustBgVolume(jSlider2.getValue());
                jTextField2.setText("BG VOLUME : "+Game.getBgVolume());

            }
        });
        jSlider2.setBounds(30,160,260,60);
        add(jSlider2);
        jSlider2.setVisible(true);
        jSlider2.setMajorTickSpacing(20);
        jSlider2.setMinorTickSpacing(5);
        jSlider2.setPaintTicks(true);
        jSlider2.setPaintTrack(true);
        jSlider2.setPaintLabels(true);
        jSlider2.setFont(new Font("Default",Font.ITALIC,15));
    }
    public void resizeandaddcomponent(){
        setSize(Game.GAME_WIDTH/2,Game.GAME_HEIGHT/2+100);
        addJTextFieldSound2();
        addJSliderSound2();
    }
}
