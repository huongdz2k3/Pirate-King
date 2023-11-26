package States;

import SignalProcessor.SignalProcessor;
import UI.UI;

import java.awt.*;

public abstract class GameState implements SignalProcessor {
    private UI ui;

    public abstract void update();
    public abstract void render(Graphics g);
}
