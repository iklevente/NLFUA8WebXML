package hu.domparse.nlfua8;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class DomQueryNLFUA8 {

    public static void main(String[] args) {
        try {
            // Az XML fájl elérési útja
            File xmlFile = new File("src/resources/XMLNLFUA8.xml");

            // DOM dokumentum létrehozása
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            listAllVehicles(doc);
            listAllCustomers(doc);
            listAllRentalsAbovePrice(doc, 180.00);
            listAllMaintenancesBeforeDate(doc, "2023-01-01");
            listAllWorkingRelations(doc);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 1. Minden gépjármű listázása (JarmuID, Rendszam, Marka)
     */
    private static void listAllVehicles(Document doc) {
        System.out.println("1. Összes Gépjármű:");
        NodeList gepjarmuvek = doc.getElementsByTagName("Gepjarmu");
        for (int i = 0; i < gepjarmuvek.getLength(); i++) {
            Element gepjarmu = (Element) gepjarmuvek.item(i);
            String jarmuID = gepjarmu.getAttribute("JarmuID");
            String rendszam = gepjarmu.getElementsByTagName("Rendszam").item(0).getTextContent();
            String marka = gepjarmu.getElementsByTagName("Marka").item(0).getTextContent();

            System.out.println("  - JarmuID: " + jarmuID + ", Rendszam: " + rendszam + ", Marka: " + marka);
        }
    }

    /**
     * 2. Minden vásárló listázása (VasarloID, Nev, Telefonszam)
     */
    private static void listAllCustomers(Document doc) {
        System.out.println("\n2. Összes Vásárló:");
        NodeList vasarlok = doc.getElementsByTagName("Vasarlo");
        for (int i = 0; i < vasarlok.getLength(); i++) {
            Element vasarlo = (Element) vasarlok.item(i);
            String vasarloID = vasarlo.getAttribute("VasarloID");
            String nev = vasarlo.getElementsByTagName("Nev").item(0).getTextContent();
            String tel = vasarlo.getElementsByTagName("Telefonszam").item(0).getTextContent();

            System.out.println("  - VasarloID: " + vasarloID + ", Név: " + nev + ", Telefonszam: " + tel);
        }
    }

    /**
     * 3. Minden kölcsönzés, ahol a KolcsonzesiAr meghalad egy adott értéket
     */
    private static void listAllRentalsAbovePrice(Document doc, double priceThreshold) {
        System.out.println("\n3. Kölcsönzések, ahol a KolcsonzesiAr > " + priceThreshold + ":");
        NodeList kolcsonzesek = doc.getElementsByTagName("Kolcsonzes");
        for (int i = 0; i < kolcsonzesek.getLength(); i++) {
            Element kolcsonzes = (Element) kolcsonzesek.item(i);
            double kolcsonzesiAr = Double.parseDouble(kolcsonzes.getElementsByTagName("KolcsonzesiAr").item(0).getTextContent());

            if (kolcsonzesiAr > priceThreshold) {
                String gepjarmuID = kolcsonzes.getAttribute("GepjarmuID");
                String vasarloID = kolcsonzes.getAttribute("VasarloID");
                String tol = kolcsonzes.getElementsByTagName("Tol").item(0).getTextContent();
                String ig = kolcsonzes.getElementsByTagName("Ig").item(0).getTextContent();
                String elteltNapok = kolcsonzes.getElementsByTagName("ElteltNapok").item(0).getTextContent();
                System.out.println("  - GepjarmuID: " + gepjarmuID + ", VasarloID: " + vasarloID 
                    + ", Tol: " + tol + ", Ig: " + ig + ", ElteltNapok: " + elteltNapok + ", Ár: " + kolcsonzesiAr);
            }
        }
    }

    /**
     * 4. Minden karbantartás, melynek dátuma egy adott időpont (2023-01-01) előtt van
     */
    private static void listAllMaintenancesBeforeDate(Document doc, String dateLimit) {
        System.out.println("\n4. Karbantartások " + dateLimit + " előtt:");
        NodeList karbantartasok = doc.getElementsByTagName("Karbantartas");
        for (int i = 0; i < karbantartasok.getLength(); i++) {
            Element karbantartas = (Element) karbantartasok.item(i);
            String datum = karbantartas.getElementsByTagName("Datum").item(0).getTextContent();
            if (datum.compareTo(dateLimit) < 0) {
                String gepjarmuID = karbantartas.getAttribute("GepjarmuID");
                String szereloID = karbantartas.getAttribute("SzereloID");
                String km = karbantartas.getElementsByTagName("MegtettKilometer").item(0).getTextContent();
                System.out.println("  - Datum: " + datum + ", JarmuID: " + gepjarmuID 
                    + ", SzereloID: " + szereloID + ", KM: " + km);
            }
        }
    }

    /**
     * 5. Minden "Dolgozik" kapcsolat (SzereloID, CegID, KezdoDatum)
     */
    private static void listAllWorkingRelations(Document doc) {
        System.out.println("\n5. Dolgozik kapcsolatok:");
        NodeList dolgozikLista = doc.getElementsByTagName("Dolgozik");
        for (int i = 0; i < dolgozikLista.getLength(); i++) {
            Element dolgozik = (Element) dolgozikLista.item(i);
            String szereloID = dolgozik.getAttribute("SzereloID");
            String cegID = dolgozik.getAttribute("CegID");
            String kezdoDatum = dolgozik.getElementsByTagName("KezdoDatum").item(0).getTextContent();

            System.out.println("  - SzereloID: " + szereloID + ", CegID: " + cegID + ", KezdoDatum: " + kezdoDatum);
        }
    }
}
