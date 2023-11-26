package Physics.Signal;

public class Signal {
    public static final int UP = 1;
    public static final int DOWN = -1;
    public static final int LEFT = -1;
    public static final int RIGHT = 1;
    public int vertical;
    public int horizontal;
    public Signal() {
        vertical = 0;
        horizontal = 0;
    }
    public Signal(int vertical, int horizontal) {
        this.vertical = vertical;
        this.horizontal = horizontal;

    }
}
