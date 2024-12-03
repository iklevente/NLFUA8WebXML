package hu.domparse.nlfua8;

import java.io.File;
import java.io.FileWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DomReadNLFUA8 {
    public static void main(String[] args) {
        try {
            // XML fájl beolvasása
            File inputFile = new File("XMLNLFUA8.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            // Root elem kiírása
            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());

            // Összes gépjármű kiíratása
            NodeList nodeList = doc.getElementsByTagName("Gepjarmu");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    System.out.println("Jármű ID: " + element.getAttribute("JarmuID"));
                    System.out.println("Rendszám: " + element.getElementsByTagName("Rendszam").item(0).getTextContent());
                    System.out.println("Típus: " + element.getElementsByTagName("JarmuTipus").item(0).getTextContent());
                    System.out.println("Márka: " + element.getElementsByTagName("Marka").item(0).getTextContent());
                    System.out.println("Kilométer: " + element.getElementsByTagName("MegtettKilometer").item(0).getTextContent());
                    System.out.println();
                }
            }

            // Mentés fájlba
            FileWriter writer = new FileWriter("OutputRead.txt");
            writer.write("Az XML tartalmának kiírása blokk formában.\n");
            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
