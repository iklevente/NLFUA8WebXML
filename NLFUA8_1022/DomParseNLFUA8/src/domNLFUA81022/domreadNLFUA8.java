package domNLFUA81022;

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class domreadNLFUA8 {
    public static void main(String[] args) {
        try {
            File inputFile = new File("NLFUA8.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName("hallgato");

            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);
                System.out.println("\nCurrent Element: " + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    System.out.println("Hallgato ID: " + eElement.getAttribute("id"));
                    System.out.println("Keresztnev: " + eElement.getElementsByTagName("keresztnev").item(0).getTextContent());
                    System.out.println("Vezeteknev: " + eElement.getElementsByTagName("vezeteknev").item(0).getTextContent());
                    System.out.println("Foglalkozas: " + eElement.getElementsByTagName("foglalkozas").item(0).getTextContent());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}