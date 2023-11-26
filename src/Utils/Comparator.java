package Utils;

public class Comparator {
    public static final float eps = (float) 0.0001;
    public static int compareFloat(float x, float y) {
        if(x > y + eps) {
            return 1;
        }
        if(x < y - eps) {
            return -1;
        }
        return 0;
    }
}
