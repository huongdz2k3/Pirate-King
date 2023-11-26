package Graphics;

import Graphics.Tile.TileSet;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Vector;

import static LevelBuilder.LevelBuilder.buildAtlas;

public class TextureAtlas {
    private static Vector<TileSet> tileSets;
    private static Vector<Integer> IdToTileset;
    public static HashMap<String, TileSet> nameTileSet = new HashMap<>();
    public static void init() {
        tileSets = new Vector<>();
        IdToTileset = new Vector<>();
        IdToTileset.add(0);
        nameTileSet.clear();
        nameTileSet.put("sail", buildAtlas("Ship/sail.tsx"));
        nameTileSet.put("hull", buildAtlas("Ship/hull.tsx"));

    }
    public static Vector<BufferedImage>  getAniTileFromNameTileset(String setName, int id) {
        return nameTileSet.get(setName).animatedTiles.get(id);
    }
    public static BufferedImage getTileFromNameTileset(String setName, int id) {
        return nameTileSet.get(setName).getTile(id);
    }
    public static void add(TileSet tileSet) {
        tileSets.add(tileSet);
        for(int i = tileSet.firstID; i < tileSet.firstID + tileSet.tileCount; i ++) {
            IdToTileset.add(tileSets.size() - 1);
        }

    }
    public static BufferedImage getTileImage(int value) {
        for(int i = 0; i < tileSets.size(); i ++) {
            TileSet tmp = tileSets.get(i);
            if(tmp.firstID <= value && value < tmp.firstID+tmp.tileCount) {
                return tmp.getTile(value-tmp.firstID);
            }
        }
        System.out.println("value="+value);
        return null;
    }
    public static Vector<BufferedImage> getAniTile(int value) {
        int tileSetID = IdToTileset.get(value);
        TileSet ts = tileSets.get(tileSetID);
        return ts.animatedTiles.get(value-ts.firstID);

    }
    public static boolean isAnimated(int value) {
        for(int i = 0; i < tileSets.size(); i ++) {
            TileSet tmp = tileSets.get(i);
            if (tmp.firstID <= value && value < tmp.firstID + tmp.tileCount) {
                return tmp.isAnimatedTile(value-tmp.firstID);
            }
        }
        return false;
    }
}
