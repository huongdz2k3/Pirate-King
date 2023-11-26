package LevelBuilder;
import java.util.Vector;

import Entity.EntitiesManager;
import Entity.Entity;
import Entity.Environment;
import Entity.Living.Enemy.Crab.Crab;
import Entity.Living.Enemy.Star.Star;
import Entity.Living.Living;
import Entity.Living.Player.Player;
import Entity.Ship;
import Graphics.*;
import Graphics.Tile.AniTile;
import Graphics.Tile.Tile;
import Graphics.Tile.TileMap;
import Graphics.Tile.TileSet;
import Physics.Box.CollisionBox;
import States.GamePlay;
import Utils.Rect;
import Utils.Vector2D;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import static Entity.Entity.*;
import static Utils.Util.getRootElement;

public class LevelBuilder {
    public static float levelWidth;
    public static float levelHeight;
    public static float titleSize;
    public static int heightInTile;
    public static int widthInTile;
    public static void configLevel(Element element) {
        widthInTile = Integer.parseInt(element.getAttribute("width"));
        heightInTile  = Integer.parseInt(element.getAttribute("height"));
        titleSize = Float.parseFloat(element.getAttribute("tileheight"));
        levelWidth = widthInTile * titleSize;
        levelHeight = heightInTile * titleSize;
    }
    public static Vector2D getCentre(float x, float y, float width, float height) {
        float cX = x + width / 2;
        float cY = y + height / 2;
        cY = levelHeight - cY;
        return new Vector2D(cX, cY);
    }
    public static Rect getRect(Element element) {
        float x = Float.parseFloat(element.getAttribute("x"));
        float y = Float.parseFloat(element.getAttribute("y"));
        float w = Float.parseFloat(element.getAttribute("width"));
        float h = Float.parseFloat(element.getAttribute("height"));
        Vector2D centre = getCentre(x, y, w, h);
        return new Rect(centre, w, h);
    }
    public static void buildEnv(Element element) {
        Environment environment = new Environment();
        NodeList eList = element.getElementsByTagName("object");
        for(int i = 0; i < eList.getLength(); i ++) {
            Element eNode = (Element) eList.item(i);
            Rect rect = getRect(eNode);
            CollisionBox box = new CollisionBox(rect.centre.x, rect.centre.y, rect.width, rect.height, ENV, PLAYER|ENEMY);
            environment.add(box);
        }
        EntitiesManager.add((Entity) environment);
    }

    public static void buildPlayer(Element element) {
        NodeList list = element.getElementsByTagName("object");
        Element e = (Element) list.item(0);
        float x = Float.parseFloat(e.getAttribute("x"));
        float y = levelHeight - Float.parseFloat(e.getAttribute("y"));
        // debug
        EntitiesManager.addPlayer(new Player(x, y));
    }
    public static void buildShip(Element element) {
        NodeList list = element.getElementsByTagName("object");
        Element e = (Element) list.item(0);
        float x = Float.parseFloat(e.getAttribute("x"));
        float y = levelHeight - Float.parseFloat(e.getAttribute("y"));
        System.out.println(x);
        System.out.println(y);
        EntitiesManager.add(new Ship(x, y));
    }
    public static void buildTileMap(Element element, TileMap tileMap) {
        Node node = element.getElementsByTagName("data").item(0);
        String tmp = node.getTextContent();
        String []tiles = tmp.split(",");
        float speed = element.hasAttribute("speed") ? Float.parseFloat(element.getAttribute("speed")) : 0;
        for(int i = 0; i < heightInTile; i ++) {
            for(int j = 0; j < widthInTile; j ++) {
                String tS = tiles[i * widthInTile + j].replace("\n", " ").strip();
                int value = Integer.parseInt(tS);
                if(value > 0) {
                    int row = heightInTile - i - 1;
                    float lowerLeftY = row * titleSize;
                    float lowerLeftX = j * titleSize;
                    if(TextureAtlas.isAnimated(value)) {
                        AniTile tile = new AniTile(new Vector2D(lowerLeftX, lowerLeftY), value);
                        tile.speed = speed;
                        tileMap.addAniTile(tile);
                    }
                    else {
                        Tile tile = new Tile(new Vector2D(lowerLeftX, lowerLeftY), value);
                        tile.speed = speed;
                        tileMap.add(tile);
                    }
                }
            }
        }
    }
    public static TileSet buildAtlas(String path) {
        System.out.println(path);
        Element root = getRootElement(path);
        TileSet tileSet = new TileSet();
        tileSet.tileWidth = Integer.parseInt(root.getAttribute("tilewidth"));
        tileSet.tileHeight = Integer.parseInt(root.getAttribute("tileheight"));
        Element img = (Element) root.getElementsByTagName("image").item(0);
        tileSet.setImage(img.getAttribute("source"));
        tileSet.width = Integer.parseInt(img.getAttribute("width"));
        tileSet.height = Integer.parseInt(img.getAttribute("height"));
        // tileSet.firstID = Integer.parseInt(element.getAttribute("firstgid"));
        tileSet.tileCount = Integer.parseInt(root.getAttribute("tilecount"));
        // TextureAtlas.add(tileSet);
        // Animation tile
        NodeList list = root.getElementsByTagName("tile");
        for(int i = 0; i < list.getLength(); i ++) {
            Element tile = (Element) list.item(i);
            int id = Integer.parseInt(tile.getAttribute("id"));
            Element ani = (Element) tile.getElementsByTagName("animation").item(0);
            NodeList listFrame = ani.getElementsByTagName("frame");
            Vector<Integer> frames = new Vector<>();
            for(int j = 0; j < listFrame.getLength(); j ++) {
                Element frame = (Element)listFrame.item(j);
                frames.add(Integer.parseInt(frame.getAttribute("tileid")));
            }
            tileSet.setAnimatedTiles(id, frames);
        }
        return  tileSet;

    }
    public static void buildLevel(String path, GamePlay gamePlay) {
        // THE SAME FOR EVERY DOC. DON'T HAVE TO CARE
        Element root = getRootElement(path);
        // GET METADATA FOR LEVEL. GAME WIDTH GAME HEIGHT
        configLevel(root);
        NodeList list;
        // TEXTURE ATLAS
        list = root.getElementsByTagName("tileset");
        for (int i = 0; i < list.getLength(); i++) {
            Element tmp = (Element) list.item(i);
            String tsxPath = tmp.getAttribute("source");
            TileSet tileSet = buildAtlas(tsxPath);
            tileSet.firstID = Integer.parseInt(tmp.getAttribute("firstgid"));
            TextureAtlas.add(tileSet);
        }
        // BUILD MAP
        list = root.getElementsByTagName("layer");
        for (int i = 0; i < list.getLength(); i++) {
            gamePlay.tileMaps.add(new TileMap());
            Element tmp = (Element) list.item(i);
            buildTileMap(tmp, gamePlay.tileMaps.get(i));
        }

        list = root.getElementsByTagName("objectgroup");
        for (int i = 0; i < list.getLength(); i++) {
            Element element = (Element) list.item(i);
            String name = element.getAttribute("name");
            // Environment
            if (name.equals("Environment")) {
                buildEnv(element);
            }
            // Player
            if (name.equals("Player")) {
                buildPlayer(element);
            }
            if (name.equals("Ship")) {
                buildShip(element);
            }

        }
        // ENEMY
        list = ((Element) root.getElementsByTagName("group").item(0)).getElementsByTagName("objectgroup");
        for (int i = 0; i < list.getLength(); i++) {
            Element element = (Element) list.item(i);
            NodeList list1 = element.getElementsByTagName("object");
            String className = element.getAttribute("name");
            for (int j = 0; j < list1.getLength(); j++) {
                element = (Element) list1.item(j);
                float x = Float.parseFloat(element.getAttribute("x"));
                float y = levelHeight - Float.parseFloat(element.getAttribute("y"));
                Living entity;
                if(className.equals("Crab")) {
                    entity = new Crab();
                } else {
                    entity = new Star();
                }
                entity.setCentre(x, y);
                EntitiesManager.add(entity);
            }
        }
    }


    public static void main(String[] args) {

    }
}
