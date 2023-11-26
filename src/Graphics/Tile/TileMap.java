package Graphics.Tile;

import Graphics.Tile.Tile;
import Graphics.Tile.AniTile;

import java.awt.*;
import java.util.Vector;

public class TileMap {
    public Vector<Tile> tiles;
    public Vector<AniTile> aniTiles;
    public TileMap() {

        tiles = new Vector<>();
        aniTiles = new Vector<>();
    }
    public void update() {
        for(Tile tile : tiles) {
            tile.update();
        }
        for(AniTile tile : aniTiles) {
            tile.update();
        }
    }
    public void render(Graphics g) {
        for(Tile tile : tiles) {
            tile.render(g);
        }
        for(AniTile tile : aniTiles) {
            tile.render(g);
        }
    }
    public void add(Tile tile) {
        tiles.add(tile);
    }
    public void addAniTile(AniTile tile) {
        aniTiles.add(tile);
    }
}
