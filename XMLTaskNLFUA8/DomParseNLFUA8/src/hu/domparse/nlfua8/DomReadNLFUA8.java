package hu.domparse.nlfua8;

import java.io.File;
import java.io.FileWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DomReadNLFUA8 {
    public static void main(String[] args) {
        Document doc = null; // Dokumentum változó a try-catch előtt
        try {
            // XML fájl beolvasása
            File inputFile = new File("XMLNLFUA8.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            // Root elem kiírása
            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());

            // Fájl író inicializálása
            FileWriter writer = new FileWriter("OutputRead.txt");

            // Összes gépjármű kiíratása konzolra és fájlba
            NodeList nodeList = doc.getElementsByTagName("Gepjarmu");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String output = "Jármű ID: " + element.getAttribute("JarmuID") + "\n"
                            + "Rendszám: " + element.getElementsByTagName("Rendszam").item(0).getTextContent() + "\n"
                            + "Típus: " + element.getElementsByTagName("JarmuTipus").item(0).getTextContent() + "\n"
                            + "Márka: " + element.getElementsByTagName("Marka").item(0).getTextContent() + "\n"
                            + "Kilométer: " + element.getElementsByTagName("MegtettKilometer").item(0).getTextContent() + "\n";

                    // Kiírás a konzolra
                    System.out.println(output);

                    // Kiírás fájlba
                    writer.write(output + "\n");
                }
            }

            // Fájl írásának befejezése
            writer.close();
            System.out.println("Adatok sikeresen mentve az OutputRead.txt fájlba!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
