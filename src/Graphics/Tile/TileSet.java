package Graphics.Tile;

import Utils.Vector2D;
import main.Game;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Vector;

import static Utils.Util.readImage;

public class TileSet {
    public BufferedImage image;
    public int firstID;
    public int tileWidth, tileHeight;
    public int width, height;
    public HashMap<Integer, Vector<BufferedImage>> animatedTiles;
    public int tileCount;
    public String path;
    public TileSet() {
        animatedTiles = new HashMap<>();
    }
    public void setImage(String path) {
        this.path = path;
        System.out.println(path);
        image = readImage(path);
        if(image == null) {
            // System.out.println(path);
        }
    }
    public void setAnimatedTiles(int id, Vector<Integer> tilesId) {
        Vector<BufferedImage> tmp = new Vector<>();
        for(int i = 0; i < tilesId.size(); i ++) {
            tmp.add(getTile(tilesId.get(i)));
        }
        animatedTiles.put(id, tmp);
    }

    public boolean isAnimatedTile(int id) {
        return animatedTiles.containsKey(id);
    }

    public BufferedImage getTile(int id) {
        // NO SUPPORT FOR ANIMATED TILES
        int ncol = width / tileWidth;
        int rowid = id / ncol;
        int colid = id % ncol;
        return image.getSubimage(colid*tileWidth, rowid*tileHeight, tileWidth, tileHeight);
    }
    public BufferedImage debug(int id) {
        //
        int ncol = width / tileWidth;
        int rowid = id / ncol;
        int colid = id % ncol;
        return image.getSubimage(colid*tileWidth, rowid*tileHeight, tileWidth, tileHeight);
    }

}
