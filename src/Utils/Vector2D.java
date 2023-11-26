package Utils;

public class Vector2D {
    public float x, y;
    public Vector2D(float x, float y) {
        this.x = x;
        this.y = y;
    }
    public Vector2D(Vector2D vec) {
        x = vec.getX();
        y = vec.getY();
    }
    public float getLength() {
        return (float)Math.sqrt(x*x + y*y);
    }
    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }
    public void add(Vector2D otherVec) {
        x += otherVec.x;
        y += otherVec.y;
    }
    public static Vector2D add(Vector2D a, Vector2D b) {
        return new Vector2D(a.x+b.x, a.y+b.y);
    }
    public static Vector2D minus(Vector2D a, Vector2D b) {
        return new Vector2D(a.x-b.x, a.y-b.y);
    }
}
