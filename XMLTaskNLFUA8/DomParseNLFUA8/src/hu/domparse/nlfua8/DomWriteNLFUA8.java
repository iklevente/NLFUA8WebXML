package hu.domparse.nlfua8;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class DomWriteNLFUA8 {

    public static void main(String[] args) {
        try {
            createAndPrintXMLDocument();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Dokumentum létrehozása és kiíratása
    private static void createAndPrintXMLDocument() throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.newDocument();

        // Gyökérelem létrehozása
        Element rootElement = document.createElement("AutokolcsonzesRendszer");
        rootElement.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
        rootElement.setAttribute("xsi:noNamespaceSchemaLocation", "autokolcsonzes.xsd");
        document.appendChild(rootElement);

        // 3 Gepjarmu
        addGepjarmu(document, rootElement, "CAR1", "ABC100", "BMW", "2020", "Germany", "Sedan", "10000");
        addGepjarmu(document, rootElement, "CAR2", "ABC200", "Audi", "2021", "Hungary", "SUV", "20000");
        addGepjarmu(document, rootElement, "CAR3", "ABC300", "Toyota", "2019", "Japan", "Hatchback", "50000");

        // 3 EgyediNyilvantartas
        addEgyediNyilvantartas(document, rootElement, "CAR1", "ABC100", "KGFB12345", "Kis János");
        addEgyediNyilvantartas(document, rootElement, "CAR2", "ABC200", "KGFB54321", "Balogh Éva");
        addEgyediNyilvantartas(document, rootElement, "CAR3", "ABC300", "KGFB67890", "Farkas Péter");

        // 3 Vasarlo
        addVasarlo(document, rootElement, "V1", "Kovács István", "Budapest", "1234567");
        addVasarlo(document, rootElement, "V2", "Nagy Péter", "Debrecen", "2234567");
        addVasarlo(document, rootElement, "V3", "Tóth Anna", "Szeged", "3234567");

        // 3 Kolcsonzes
        addKolcsonzes(document, rootElement, "CAR1", "V1", "2021-06-01", "2021-06-10", "9", "200.50");
        addKolcsonzes(document, rootElement, "CAR2", "V2", "2021-07-05", "2021-07-09", "4", "150.00");
        addKolcsonzes(document, rootElement, "CAR3", "V3", "2021-08-15", "2021-08-20", "5", "180.75");

        // 3 Szerelo
        addSzerelo(document, rootElement, "SZ1", "Horváth Béla", "Debrecen", "2345678");
        addSzerelo(document, rootElement, "SZ2", "Szabó Katalin", "Szeged", "3456789");
        addSzerelo(document, rootElement, "SZ3", "Mészáros László", "Pécs", "4567890");

        // 3 KolcsonoCeg
        addKolcsonoCeg(document, rootElement, "CEG1", "Budapest, XY utca 10", "250.5", "10");
        addKolcsonoCeg(document, rootElement, "CEG2", "Győr, AB tér 5", "300.0", "20");
        addKolcsonoCeg(document, rootElement, "CEG3", "Miskolc, CD u. 7", "150.75", "15");

        // 3 Karbantartas
        addKarbantartas(document, rootElement, "CAR1", "SZ1", "2022-01-15", "15000");
        addKarbantartas(document, rootElement, "CAR2", "SZ2", "2022-03-20", "25000");
        addKarbantartas(document, rootElement, "CAR3", "SZ3", "2023-07-10", "60000");

        // 3 Dolgozik
        addDolgozik(document, rootElement, "SZ1", "CEG1", "2020-01-01");
        addDolgozik(document, rootElement, "SZ2", "CEG2", "2021-02-01");
        addDolgozik(document, rootElement, "SZ3", "CEG3", "2021-05-10");

        // 3 Kiadva
        addKiadva(document, rootElement, "CAR1", "CEG1", "2021-03-15");
        addKiadva(document, rootElement, "CAR2", "CEG2", "2021-08-20");
        addKiadva(document, rootElement, "CAR3", "CEG3", "2022-11-05");

        printDocumentToConsole(document);
        printToFile(document, "src/resources/AutokolcsonzesRendszer.xml");
    }

    private static void addGepjarmu(Document doc, Element root, String jarmuID, String rendszam, String marka, String evjarat,
                                   String gyartasiHely, String tipus, String megtettKm) {
        Element gepjarmu = doc.createElement("Gepjarmu");
        gepjarmu.setAttribute("JarmuID", jarmuID);

        appendElement(doc, gepjarmu, "Rendszam", rendszam);
        appendElement(doc, gepjarmu, "Marka", marka);
        appendElement(doc, gepjarmu, "Evjarat", evjarat);
        appendElement(doc, gepjarmu, "GyartasiHely", gyartasiHely);
        appendElement(doc, gepjarmu, "Tipus", tipus);
        appendElement(doc, gepjarmu, "MegtettKilometer", megtettKm);

        root.appendChild(gepjarmu);
    }

    private static void addEgyediNyilvantartas(Document doc, Element root, String jarmuID, String rendszam, String kgfb, String tulajdonos) {
        Element eny = doc.createElement("EgyediNyilvantartas");
        eny.setAttribute("JarmuID", jarmuID);

        appendElement(doc, eny, "Rendszam", rendszam);
        appendElement(doc, eny, "KGFB", kgfb);
        appendElement(doc, eny, "Tulajdonos", tulajdonos);

        root.appendChild(eny);
    }

    private static void addVasarlo(Document doc, Element root, String vasarloID, String nev, String lakcim, String telszam) {
        Element vasarlo = doc.createElement("Vasarlo");
        vasarlo.setAttribute("VasarloID", vasarloID);

        appendElement(doc, vasarlo, "Nev", nev);
        appendElement(doc, vasarlo, "Lakcim", lakcim);
        appendElement(doc, vasarlo, "Telefonszam", telszam);

        root.appendChild(vasarlo);
    }

    private static void addKolcsonzes(Document doc, Element root, String jarmuID, String vasarloID, String tol, String ig, String elteltNapok, String ar) {
        Element kolcsonzes = doc.createElement("Kolcsonzes");
        kolcsonzes.setAttribute("GepjarmuID", jarmuID);
        kolcsonzes.setAttribute("VasarloID", vasarloID);

        appendElement(doc, kolcsonzes, "Tol", tol);
        appendElement(doc, kolcsonzes, "Ig", ig);
        appendElement(doc, kolcsonzes, "ElteltNapok", elteltNapok);
        appendElement(doc, kolcsonzes, "KolcsonzesiAr", ar);

        root.appendChild(kolcsonzes);
    }

    private static void addSzerelo(Document doc, Element root, String szereloID, String nev, String lakcim, String telszam) {
        Element szerelo = doc.createElement("Szerelo");
        szerelo.setAttribute("SzereloID", szereloID);

        appendElement(doc, szerelo, "Nev", nev);
        appendElement(doc, szerelo, "Lakcim", lakcim);
        appendElement(doc, szerelo, "Telefonszam", telszam);

        root.appendChild(szerelo);
    }

    private static void addKolcsonoCeg(Document doc, Element root, String cegID, String cim, String alapterulet, String autokSzama) {
        Element ceg = doc.createElement("KolcsonoCeg");
        ceg.setAttribute("CegID", cegID);

        appendElement(doc, ceg, "Cim", cim);
        appendElement(doc, ceg, "Alapterulet", alapterulet);
        appendElement(doc, ceg, "AutokSzama", autokSzama);

        root.appendChild(ceg);
    }

    private static void addKarbantartas(Document doc, Element root, String jarmuID, String szereloID, String datum, String km) {
        Element k = doc.createElement("Karbantartas");
        k.setAttribute("GepjarmuID", jarmuID);
        k.setAttribute("SzereloID", szereloID);

        appendElement(doc, k, "Datum", datum);
        appendElement(doc, k, "MegtettKilometer", km);

        root.appendChild(k);
    }

    private static void addDolgozik(Document doc, Element root, String szereloID, String cegID, String kezdoDatum) {
        Element d = doc.createElement("Dolgozik");
        d.setAttribute("SzereloID", szereloID);
        d.setAttribute("CegID", cegID);

        appendElement(doc, d, "KezdoDatum", kezdoDatum);

        root.appendChild(d);
    }

    private static void addKiadva(Document doc, Element root, String jarmuID, String cegID, String datum) {
        Element k = doc.createElement("Kiadva");
        k.setAttribute("GepjarmuID", jarmuID);
        k.setAttribute("CegID", cegID);

        appendElement(doc, k, "Datum", datum);

        root.appendChild(k);
    }

    private static void appendElement(Document doc, Element parent, String tagName, String text) {
        Element elem = doc.createElement(tagName);
        elem.appendChild(doc.createTextNode(text));
        parent.appendChild(elem);
    }

    private static void printDocumentToConsole(Document document) throws Exception {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(document);
        StreamResult consoleResult = new StreamResult(System.out);
        transformer.transform(source, consoleResult);
    }

    private static void printToFile(Document document, String filePath) throws Exception {
        File outFile = new File(filePath);
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(document);
        StreamResult outputFile = new StreamResult(outFile);

        transformer.transform(source, outputFile);
        System.out.println("XML fájl sikeresen kiírva: " + outFile.getAbsolutePath());
    }
}
