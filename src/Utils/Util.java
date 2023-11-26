package Utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static main.Game.GAME_HEIGHT;

public class Util {
    public static boolean intersect(Rectangle a, Rectangle b) {
        a.y = GAME_HEIGHT - a.y;
        b.y = GAME_HEIGHT - b.y;
        if(a.intersects(b)) {
            return true;
        }
        return false;
    }
    public static Rectangle getRectangle(float centreX, float centreY, float width, float height) {
        float topLeftX = centreX - width/2;
        float topLeftY = centreY + height/2;
        return new Rectangle((int) topLeftX, (int)topLeftY, (int)width, (int)height);
    }
    public static float cap(float value, float min, float max) {
        return Math.max(Math.min(value, max), min);
    }
    public static BufferedImage readImage(String path) {
        BufferedImage image = null;
        try{
            image = ImageIO.read(ImageFinder.findAndOpenStream(path));
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return image;
    }
    public static Element getRootElement(String path) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
            File file = Utils.ImageFinder.findFileNotNull(path);
				if(file == null) System.exit(-1);
            Document doc = builder.parse(file);
            doc.getDocumentElement().normalize();
            Element root = doc.getDocumentElement();
            return root;
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static int sign(float x) {
        if(x > 0) return 1;
        if(x < 0) return -1;
        return 0;
    }
    public static boolean inRange(float val, float l, float r) {
        return (l <= val && val <= r);
    }
}
