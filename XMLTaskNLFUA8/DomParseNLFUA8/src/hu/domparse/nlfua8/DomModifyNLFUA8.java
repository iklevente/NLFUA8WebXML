package hu.domparse.nlfua8;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DomModifyNLFUA8 {
    
    public static void main(String[] args) {
        try {
            File inputFile = new File("XMLNLFUA8.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(inputFile);

            NodeList gepjarmuvek = document.getElementsByTagName("Gepjarmu");
            for (int i = 0; i < gepjarmuvek.getLength(); i++) {
                Element gepjarmu = (Element) gepjarmuvek.item(i);
                if (gepjarmu.getAttribute("JarmuID").equals("2")) {
                    gepjarmu.getElementsByTagName("MegtettKilometer").item(0).setTextContent("95000");
                    System.out.println("Jármű ID=2 megtett kilométer frissítve: 95000");
                }
            }

            Element ujGepjarmu = document.createElement("Gepjarmu");
            ujGepjarmu.setAttribute("JarmuID", "4");
            Element ujRendszam = document.createElement("Rendszam");
            ujRendszam.setTextContent("JKL-456");
            ujGepjarmu.appendChild(ujRendszam);
            document.getDocumentElement().appendChild(ujGepjarmu);
            System.out.println("Új jármű hozzáadva: JarmuID=4");

            NodeList kolcsonzesek = document.getElementsByTagName("Kolcsonzes");
            for (int i = 0; i < kolcsonzesek.getLength(); i++) {
                Element kolcsonzes = (Element) kolcsonzesek.item(i);
                if (kolcsonzes.getAttribute("KolcsonzesID").equals("100")) {
                    kolcsonzes.getElementsByTagName("KolcsonzesAra").item(0).setTextContent("16000");
                    System.out.println("Kölcsönzés ID=100 ára frissítve: 16000");
                }
            }

            NodeList szerelok = document.getElementsByTagName("Szerelo");
            for (int i = 0; i < szerelok.getLength(); i++) {
                Element szerelo = (Element) szerelok.item(i);
                if (szerelo.getAttribute("SzereloID").equals("10")) {
                    szerelo.getElementsByTagName("Nev").item(0).setTextContent("Kovács Péter");
                    System.out.println("Szerelő ID=10 neve frissítve: Kovács Péter");
                }
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File("Modified_XMLNLFUA8.xml"));
            transformer.transform(source, result);

            System.out.println("\nAz XML dokumentum frissítve és mentve: 'Modified_XMLNLFUA8.xml'");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
