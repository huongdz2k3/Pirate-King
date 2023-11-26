package Listener;

import main.Game;
import main.GameLauncher;
import main.MainMenu;
import main.MyDialog;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import States.GamePlay;
import States.GameState;

public class Mouse {
    public static MouseListener about = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
            MyDialog.jDialogAbout = new MyDialog(main.GameLauncher.game.gameWindow,"My Group");
            MyDialog.jDialogAbout.addINFOGroup();
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
    };
    public static MouseListener Option = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
            MyDialog.jDialogOption = new MyDialog(main.GameLauncher.game.gameWindow,"OPTION");
            MyDialog.jDialogOption.addJTextSoundComponent();
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
    };
    public static MouseListener exit = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
            System.exit(0);
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
    };
    public static MouseListener LETSGO = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
            MainMenu.m.setVisible(false);
            MyDialog.jDialogPlay.dispose();
            GameLauncher.game.gamePlay = new GamePlay(GameLauncher.game, MyDialog.level);
            GameLauncher.game.currentState = (GameState) GameLauncher.game.gamePlay;
            GameLauncher.game.startGameLoop();
            GameLauncher.game.gamePanel.requestFocus();
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
    };
    public static MouseListener startGame = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
            MyDialog.jDialogPlay =new MyDialog(main.GameLauncher.game.gameWindow,"PICK LEVEL");
            MyDialog.jDialogPlay.addLevelComponent();
//            MainMenu.m.setVisible(false);
//            GameLauncher.game.startGameLoop();
//            GameLauncher.game.gamePanel.requestFocus();
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
    };
}
