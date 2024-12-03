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
            File inputFile = new File("XMLNLFUA8.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(inputFile);
            document.getDocumentElement().normalize();

            System.out.println("Jármű típusok:");
            NodeList gepjarmuvek = document.getElementsByTagName("Gepjarmu");
            for (int i = 0; i < gepjarmuvek.getLength(); i++) {
                Element gepjarmu = (Element) gepjarmuvek.item(i);
                System.out.println("  " + gepjarmu.getElementsByTagName("JarmuTipus").item(0).getTextContent());
            }

            System.out.println("\nKölcsönző cégek nevei:");
            NodeList kolcsonzok = document.getElementsByTagName("KolcsonzoCeg");
            for (int i = 0; i < kolcsonzok.getLength(); i++) {
                Element ceg = (Element) kolcsonzok.item(i);
                System.out.println("  " + ceg.getElementsByTagName("Nev").item(0).getTextContent());
            }

            String keresettID = "2";
            System.out.println("\nAdott ID-jű jármű adatai (ID = " + keresettID + "):");
            for (int i = 0; i < gepjarmuvek.getLength(); i++) {
                Element gepjarmu = (Element) gepjarmuvek.item(i);
                if (gepjarmu.getAttribute("JarmuID").equals(keresettID)) {
                    System.out.println("  Rendszám: " + gepjarmu.getElementsByTagName("Rendszam").item(0).getTextContent());
                    System.out.println("  Típus: " + gepjarmu.getElementsByTagName("JarmuTipus").item(0).getTextContent());
                    System.out.println("  Gyártási hely: " + gepjarmu.getElementsByTagName("GyartasiHely").item(0).getTextContent());
                }
            }

            System.out.println("\nKarbantartások listája:");
            NodeList karbantartasok = document.getElementsByTagName("Karbantartas");
            for (int i = 0; i < karbantartasok.getLength(); i++) {
                Element karbantartas = (Element) karbantartasok.item(i);
                System.out.println("  Jármű ID: " + karbantartas.getAttribute("JarmuID") +
                        ", Szerelő ID: " + karbantartas.getAttribute("SzereloID") +
                        ", Szint: " + karbantartas.getElementsByTagName("KarbantartasiSzint").item(0).getTextContent());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
