package hu.domparse.nlfua8;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DomQueryNLFUA8 {
    public static void main(String[] args) {
        try {
            // XML fájl beolvasása
            File inputFile = new File("XMLNLFUA8.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            // 1. Lekérdezés: Összes jármű márkája
            System.out.println("1. Lekérdezés: Összes jármű márkája");
            NodeList nodeList = doc.getElementsByTagName("Gepjarmu");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                System.out.println("- Márka: " + element.getElementsByTagName("Marka").item(0).getTextContent());
            }
            System.out.println();

            // 2. Lekérdezés: Melyik járművek típusai
            System.out.println("2. Lekérdezés: Melyik járművek típusai");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                System.out.println("- Típus: " + element.getElementsByTagName("JarmuTipus").item(0).getTextContent());
            }
            System.out.println();

            // 3. Lekérdezés: Karbantartás szintjei járművenként
            System.out.println("3. Lekérdezés: Karbantartás szintjei járművenként");
            NodeList karbantartasList = doc.getElementsByTagName("Karbantartas");
            for (int i = 0; i < karbantartasList.getLength(); i++) {
                Element element = (Element) karbantartasList.item(i);
                System.out.println("- Jármű ID: " + element.getAttribute("JarmuID"));
                System.out.println("  Karbantartási szint: " + element.getElementsByTagName("KarbantartasiSzint").item(0).getTextContent());
            }
            System.out.println();

            // 4. Lekérdezés: Melyik városokban készültek a járművek
            System.out.println("4. Lekérdezés: Melyik városokban készültek a járművek");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                System.out.println("- Gyártási hely: " + element.getElementsByTagName("GyartasiHely").item(0).getTextContent());
            }
            System.out.println();

            // 5. Lekérdezés: Legtöbbet megtett kilométerű jármű adatai
            System.out.println("5. Lekérdezés: Legtöbbet megtett kilométerű jármű adatai");
            int maxKilometer = 0;
            Element maxKilometerCar = null;
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                int kilometer = Integer.parseInt(element.getElementsByTagName("MegtettKilometer").item(0).getTextContent());
                if (kilometer > maxKilometer) {
                    maxKilometer = kilometer;
                    maxKilometerCar = element;
                }
            }
            if (maxKilometerCar != null) {
                System.out.println("- Rendszám: " + maxKilometerCar.getElementsByTagName("Rendszam").item(0).getTextContent());
                System.out.println("- Típus: " + maxKilometerCar.getElementsByTagName("JarmuTipus").item(0).getTextContent());
                System.out.println("- Kilométer: " + maxKilometer);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
