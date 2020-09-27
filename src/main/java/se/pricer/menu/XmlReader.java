package se.pricer.menu;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XmlReader {

    private List<MenuItem> list;

    public List<MenuItem> readFile(String fileName) {
        File xmlFile = new File(fileName);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        Document document = null;
        try {
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            document = dBuilder.parse(xmlFile);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        list = new ArrayList<>();
        NodeList nodeList = document.getElementsByTagName("food");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            Element element = (Element) node;
            parseMenuItem(element);
        }
        return list;
    }

    private void parseMenuItem(Element element) {
        MenuItem menu = new MenuItem();
        menu.setName(element.getElementsByTagName("name").item(0).getTextContent());
        menu.setPrice(element.getElementsByTagName("price").item(0).getTextContent());
        menu.setDescription(element.getElementsByTagName("description").item(0).getTextContent());
        menu.setCalories(element.getElementsByTagName("calories").item(0).getTextContent());
        list.add(menu);
    }
}
