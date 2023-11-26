package Panels;


import Entity.EntitiesManager;
import main.Game;

import javax.swing.*;

import UI.LifeBar;

import java.awt.*;

public class GamePanel extends JPanel {
    private Game game;
    private LifeBar lifeBar = new LifeBar();
    public GamePanel(Game game) {
        this.game = game;
        setLayout(null);
        setPanelSize();
        requestFocus();
    }
    private void setPanelSize() {
        Dimension dimension = new Dimension(Game.GAME_WIDTH, Game.GAME_HEIGHT);
        setPreferredSize(dimension);
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.render(g);
        lifeBar.draw(g);
    }
    public Game getGame() {
        return game;
    }
}
