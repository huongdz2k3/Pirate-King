package main;

import Entity.EntitiesManager;
import Entity.Entity;
import Entity.Living.Player.Player;
import Panels.GamePanel;
import Panels.GameWindow;
import States.GamePlay;
import States.GameState;
import States.LevelMenu;
import UI.Pause;
import Utils.ImageFinder;
import main.MainMenu;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.CompoundControl;
import javax.sound.sampled.Control;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;

public class Game implements Runnable{

    public GameState currentState;

    public final static int TILES_DEFAULT_SIZE = 32;
    public final static float SCALE = 1f;
    public final static int TILES_IN_WIDTH = 10;
    public final static int TILES_IN_HEIGHT = 10;
    public final static int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
    public final static int GAME_WIDTH = 640;
    public final static int GAME_HEIGHT = 480;
    public float FPS = 60;
    public float UPS = 60;
    public GamePlay gamePlay;
    public GameWindow gameWindow;
    public GamePanel gamePanel;
    public boolean RUNNING;
    static Clip bgMusicClip = null; 
    /**
     * load MainMenu
     * load LevelsMenu
     *
     */
    static {
    	InputStream iis = ImageFinder.findAndOpenStreamCanNull("sounds/bg.wav");
		if(iis != null) {
			try {
				bgMusicClip = AudioSystem.getClip();
				bgMusicClip.open(AudioSystem.getAudioInputStream(new BufferedInputStream(iis)));
				bgMusicClip.setLoopPoints(0, -1);
			} catch (Throwable e) {
				System.err.println("Error: " + e.getMessage());
				e.printStackTrace();
			}
		}
    }
    public Game() {
        /*mainMenu = new MainMenu();
        levelMenu = new LevelMenu();*/
    	
        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();
        gamePanel.add(MainMenu.m);
        MainMenu.m.repaint();
       // gameWindow.add(tuan);
        //startGameLoop();
    }
    
    public void loseGame() {
    	endGame();
    }
    
    public void winGame() {
    	endGame();
    }
    
    public void endGame() {
        toMenu();
    }
    
    public void toMenu() {
    	pauseLogic();
    	MainMenu.m.setVisible(true);
    	MainMenu.m.requestFocus();
    	gamePanel.repaint();
    }
    
    static private int bgMusicVol=50;
    public static void adjustBgVolume(int f) {
    	if(f<0||f>100) System.err.println("code volume UI sai, adjustVolume="+f);
    	bgMusicVol = f;
    	if(bgMusicClip == null) return;
		FloatControl gainControl = (FloatControl) bgMusicClip.getControl(FloatControl.Type.MASTER_GAIN);
		float min = gainControl.getMinimum();
		float max = gainControl.getMaximum();
		gainControl.setValue(min+(max-min)/100*f);
    }
    public static int getBgVolume() {
    	return bgMusicVol;
    }
    
    static private int volume=50;
    public static void adjustVolume(int f) {
    	if(f<0||f>100) System.err.println("code volume UI sai, adjustVolume="+f);
    	volume=f;
    	for(var i:EntitiesManager.entities)
    		if(i!=null&&i.getRenEnt()!=null) i.getRenEnt().adjustVolume(f);
    	Player p = EntitiesManager.getPlayer();
    	if(p!=null)p.getRenEnt().adjustVolume(volume);
    }
    
    public static int getVolume() {
    	return volume;
    }
    
    public void setCurrentState(GameState currentState) {
        this.currentState = currentState;
    }

    /**
     * Call currentState update
     */
    public void update() {
        if(!RUNNING)
            return;
        currentState.update();
    }

    /**
     * Call currentState render
     * @param g
     */
    public void render(Graphics g) {
        if(!RUNNING)
            return;
        currentState.render(g);
    }

    Thread thread = new Thread(this);

    public void startGameLoop() {
        thread = new Thread(this);
        RUNNING = true;
        thread.start();
    }

    public void unpauseGameUI() {
        gamePanel.remove(tuan);
    	continueGame();
    }
    Pause tuan=new Pause();
    public void pauseGameUI() {
        pauseLogic();
        gamePanel.add(tuan);
        tuan.requestFocus();
        gamePanel.repaint();
    }
    public void pauseLogic() {
    	if(!RUNNING) return;
        thread.interrupt();
        RUNNING = false;
        gamePanel.repaint();
    }

    public void continueGame() {
        startGameLoop();
    }

    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / FPS;
        double timePerUpdate = 1000000000.0 / UPS;
        long previousTime = System.nanoTime();
        double deltaU = 0;
        double deltaF = 0;
        if(bgMusicClip != null) bgMusicClip.start();
        while (!Thread.currentThread().isInterrupted()) {
            long currentTime = System.nanoTime();
            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if (deltaU >= 1) {
                update();
                deltaU--;
            }

            if (deltaF >= 1) {
                gamePanel.repaint();
                deltaF--;
            }         
        }
        if(bgMusicClip != null) bgMusicClip.stop();
        System.out.println("end thread");

    }
    public void windowFocusLost() {
        Player player = EntitiesManager.getPlayer();
        if(player != null) {
            EntitiesManager.getPlayer().resetDir();
        }
    }
}
