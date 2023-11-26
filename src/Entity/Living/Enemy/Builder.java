package Entity.Living.Enemy;

import org.w3c.dom.Element;

import static Utils.Util.getRootElement;

public class Builder {
    public static Element getAttr(Element element, String name) {
        return (Element) element.getElementsByTagName(name).item(0);
    }
    public static Element get(Element e, String attr) {
        return (Element) e.getElementsByTagName(attr).item(0);
    }
    public static Enemy buildCrab(Element element) {
//        Crab crab = new Crab();
//        float defaultSpeedX = (float) get(element, "defaultSpeed").getAttribute("x");
//        crab.setDefaultSpeedX(defaultSpeedX);
//
//        Element chasing = (Element) element.getElementsByTagName("Chasing").item(0);
//        Element attack = (Element) element.getElementsByTagName("Attack").item(0);
//
//
//        System.out.println(name);
        return null;
    }
    public static Enemy buildStar(Element element) {
        return null;

    }
    public static Enemy buildShark(Element element) {
        return null;
    }
    public static Enemy build(String path) {
        Element root = getRootElement(path);
        String name = root.getAttribute("name");
        Enemy enemy = null;
        switch (name) {
            case "Crab":
                enemy = buildCrab(root);
                break;
            case "Star":
                enemy = buildStar(root);
                break;
            case "Shark":
                enemy = buildShark(root);
                break;
        }
        return enemy;
    }
}
