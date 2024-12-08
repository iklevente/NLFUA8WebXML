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

public class DomReadNLFUA8 {
    public static void main(String[] args) {
        try {
            // Az XML fájl elérési útjának megadása
            File xmlFile = new File("src/resources/XMLNLFUA8.xml");

            // Az XML dokumentum felépítése (DOM objektum létrehozása)
            Document doc = buildDocument(xmlFile);

            // A gyökér elem kiírása
            System.out.println("Gyökér elem: " + doc.getDocumentElement().getNodeName());

            // Gepjarmu elemek beolvasása és kiírása
            System.out.println("\n<!-- Gépjármű példányok -->");
            NodeList gepjarmuList = doc.getElementsByTagName("Gepjarmu");
            for (int i = 0; i < gepjarmuList.getLength(); i++) {
                Node gepjarmuNode = gepjarmuList.item(i);
                if (gepjarmuNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element gepjarmu = (Element) gepjarmuNode;
                    kiirGepjarmuInfo(gepjarmu);
                }
            }

            // EgyediNyilvantartas elemek beolvasása és kiírása
            System.out.println("\n<!-- Egyedi Nyilvántartás példányok -->");
            NodeList egyediList = doc.getElementsByTagName("EgyediNyilvantartas");
            for (int i = 0; i < egyediList.getLength(); i++) {
                Node egyediNode = egyediList.item(i);
                if (egyediNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element egyedi = (Element) egyediNode;
                    kiirEgyediNyilvantartasInfo(egyedi);
                }
            }

            // Vasarlo elemek beolvasása és kiírása
            System.out.println("\n<!-- Vásárló példányok -->");
            NodeList vasarloList = doc.getElementsByTagName("Vasarlo");
            for (int i = 0; i < vasarloList.getLength(); i++) {
                Node vasarloNode = vasarloList.item(i);
                if (vasarloNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element vasarlo = (Element) vasarloNode;
                    kiirVasarloInfo(vasarlo);
                }
            }

            // Kolcsonzes elemek beolvasása és kiírása
            System.out.println("\n<!-- Kölcsönzés példányok -->");
            NodeList kolcsonzesList = doc.getElementsByTagName("Kolcsonzes");
            for (int i = 0; i < kolcsonzesList.getLength(); i++) {
                Node kolcsonzesNode = kolcsonzesList.item(i);
                if (kolcsonzesNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element kolcsonzes = (Element) kolcsonzesNode;
                    kiirKolcsonzesInfo(kolcsonzes);
                }
            }

            // Szerelo elemek beolvasása és kiírása
            System.out.println("\n<!-- Szerelő példányok -->");
            NodeList szereloList = doc.getElementsByTagName("Szerelo");
            for (int i = 0; i < szereloList.getLength(); i++) {
                Node szereloNode = szereloList.item(i);
                if (szereloNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element szerelo = (Element) szereloNode;
                    kiirSzereloInfo(szerelo);
                }
            }

            // KolcsonoCeg elemek beolvasása és kiírása
            System.out.println("\n<!-- Kölcsönző Cég példányok -->");
            NodeList cegList = doc.getElementsByTagName("KolcsonoCeg");
            for (int i = 0; i < cegList.getLength(); i++) {
                Node cegNode = cegList.item(i);
                if (cegNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element ceg = (Element) cegNode;
                    kiirCegInfo(ceg);
                }
            }

            // Karbantartas elemek beolvasása és kiírása
            System.out.println("\n<!-- Karbantartás példányok -->");
            NodeList karbList = doc.getElementsByTagName("Karbantartas");
            for (int i = 0; i < karbList.getLength(); i++) {
                Node karbNode = karbList.item(i);
                if (karbNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element karb = (Element) karbNode;
                    kiirKarbantartasInfo(karb);
                }
            }

            // Dolgozik elemek beolvasása és kiírása
            System.out.println("\n<!-- Dolgozik példányok -->");
            NodeList dolgozikList = doc.getElementsByTagName("Dolgozik");
            for (int i = 0; i < dolgozikList.getLength(); i++) {
                Node dolgozikNode = dolgozikList.item(i);
                if (dolgozikNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element dolgozik = (Element) dolgozikNode;
                    kiirDolgozikInfo(dolgozik);
                }
            }

            // Kiadva elemek beolvasása és kiírása
            System.out.println("\n<!-- Kiadva példányok -->");
            NodeList kiadvaList = doc.getElementsByTagName("Kiadva");
            for (int i = 0; i < kiadvaList.getLength(); i++) {
                Node kiadvaNode = kiadvaList.item(i);
                if (kiadvaNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element kiadva = (Element) kiadvaNode;
                    kiirKiadvaInfo(kiadva);
                }
            }

            // Fájlba írás (opcionális)
            printToFile(doc);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void printToFile(Document doc) throws Exception {
        File outputFile = new File("src/resources/AutokolcsonzesRendszer_output.xml");
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult outFile = new StreamResult(outputFile);
        transformer.transform(source, outFile);
        System.out.println("Sikeres kiírás a fájlba: " + outputFile.getAbsolutePath());
    }

    // XML dokumentum felépítése (beolvasás és normalizálás)
    private static Document buildDocument(File xmlFile) throws Exception {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = builder.parse(xmlFile);
        doc.getDocumentElement().normalize();
        return doc;
    }

    // Gépjármű információinak kiírása
    private static void kiirGepjarmuInfo(Element gepjarmu) {
        System.out.println("\n\tJelenlegi elem: Gepjarmu");
        String jarmuID = gepjarmu.getAttribute("JarmuID");
        System.out.println("Jármű ID: " + jarmuID);

        String rendszam = getTextContent(gepjarmu, "Rendszam");
        String marka = getTextContent(gepjarmu, "Marka");
        String evjarat = getTextContent(gepjarmu, "Evjarat");
        String gyartasiHely = getTextContent(gepjarmu, "GyartasiHely");
        String tipus = getTextContent(gepjarmu, "Tipus");
        String megtettKm = getTextContent(gepjarmu, "MegtettKilometer");

        System.out.println("Rendszám: " + rendszam);
        System.out.println("Márka: " + marka);
        System.out.println("Évjárat: " + evjarat);
        System.out.println("Gyártási hely: " + gyartasiHely);
        System.out.println("Típus: " + tipus);
        System.out.println("Megtett kilométer: " + megtettKm);
    }

    // EgyediNyilvantartas információinak kiírása
    private static void kiirEgyediNyilvantartasInfo(Element egyedi) {
        System.out.println("\n\tJelenlegi elem: EgyediNyilvantartas");
        String jarmuID = egyedi.getAttribute("JarmuID");
        System.out.println("Jármű ID (hivatkozás): " + jarmuID);

        String rendszam = getTextContent(egyedi, "Rendszam");
        String kgfb = getTextContent(egyedi, "KGFB");
        String tulajdonos = getTextContent(egyedi, "Tulajdonos");

        System.out.println("Rendszám: " + rendszam);
        System.out.println("KGFB: " + kgfb);
        System.out.println("Tulajdonos: " + tulajdonos);
    }

    // Vásárló információinak kiírása
    private static void kiirVasarloInfo(Element vasarlo) {
        System.out.println("\n\tJelenlegi elem: Vasarlo");
        String vasarloID = vasarlo.getAttribute("VasarloID");
        System.out.println("Vásárló ID: " + vasarloID);

        String nev = getTextContent(vasarlo, "Nev");
        String lakcim = getTextContent(vasarlo, "Lakcim");
        String telefonszam = getTextContent(vasarlo, "Telefonszam");

        System.out.println("Név: " + nev);
        System.out.println("Lakcím: " + lakcim);
        System.out.println("Telefonszám: " + telefonszam);
    }

    // Kölcsönzés információinak kiírása
    private static void kiirKolcsonzesInfo(Element kolcsonzes) {
        System.out.println("\n\tJelenlegi elem: Kolcsonzes");
        String jarmuID = kolcsonzes.getAttribute("GepjarmuID");
        String vasarloID = kolcsonzes.getAttribute("VasarloID");
        System.out.println("Gépjármű ID (ref): " + jarmuID);
        System.out.println("Vásárló ID (ref): " + vasarloID);

        String tol = getTextContent(kolcsonzes, "Tol");
        String ig = getTextContent(kolcsonzes, "Ig");
        String elteltNapok = getTextContent(kolcsonzes, "ElteltNapok");
        String kolcsonzesiAr = getTextContent(kolcsonzes, "KolcsonzesiAr");

        System.out.println("Bérlés - Tól: " + tol);
        System.out.println("Bérlés - Ig: " + ig);
        System.out.println("Eltelt napok: " + elteltNapok);
        System.out.println("Kölcsönzési ár: " + kolcsonzesiAr);
    }

    // Szerelő információinak kiírása
    private static void kiirSzereloInfo(Element szerelo) {
        System.out.println("\n\tJelenlegi elem: Szerelo");
        String szereloID = szerelo.getAttribute("SzereloID");
        System.out.println("Szerelő ID: " + szereloID);

        String nev = getTextContent(szerelo, "Nev");
        String lakcim = getTextContent(szerelo, "Lakcim");
        String telefonszam = getTextContent(szerelo, "Telefonszam");

        System.out.println("Név: " + nev);
        System.out.println("Lakcím: " + lakcim);
        System.out.println("Telefonszám: " + telefonszam);
    }

    // Kölcsönző Cég információinak kiírása
    private static void kiirCegInfo(Element ceg) {
        System.out.println("\n\tJelenlegi elem: KolcsonoCeg");
        String cegID = ceg.getAttribute("CegID");
        System.out.println("Cég ID: " + cegID);

        String cim = getTextContent(ceg, "Cim");
        String alapterulet = getTextContent(ceg, "Alapterulet");
        String autokSzama = getTextContent(ceg, "AutokSzama");

        System.out.println("Cím: " + cim);
        System.out.println("Alapterület: " + alapterulet);
        System.out.println("Autók száma: " + autokSzama);
    }

    // Karbantartás információinak kiírása
    private static void kiirKarbantartasInfo(Element karb) {
        System.out.println("\n\tJelenlegi elem: Karbantartas");
        String jarmuID = karb.getAttribute("GepjarmuID");
        String szereloID = karb.getAttribute("SzereloID");
        System.out.println("Gépjármű ID (ref): " + jarmuID);
        System.out.println("Szerelő ID (ref): " + szereloID);

        String datum = getTextContent(karb, "Datum");
        String megtettKm = getTextContent(karb, "MegtettKilometer");

        System.out.println("Dátum: " + datum);
        System.out.println("Megtett kilométer a karbantartáskor: " + megtettKm);
    }

    // Dolgozik információinak kiírása
    private static void kiirDolgozikInfo(Element dolgozik) {
        System.out.println("\n\tJelenlegi elem: Dolgozik");
        String szereloID = dolgozik.getAttribute("SzereloID");
        String cegID = dolgozik.getAttribute("CegID");
        System.out.println("Szerelő ID (ref): " + szereloID);
        System.out.println("Cég ID (ref): " + cegID);

        String kezdoDatum = getTextContent(dolgozik, "KezdoDatum");
        System.out.println("Kezdődátum: " + kezdoDatum);
    }

    // Kiadva információinak kiírása
    private static void kiirKiadvaInfo(Element kiadva) {
        System.out.println("\n\tJelenlegi elem: Kiadva");
        String jarmuID = kiadva.getAttribute("GepjarmuID");
        String cegID = kiadva.getAttribute("CegID");
        System.out.println("Gépjármű ID (ref): " + jarmuID);
        System.out.println("Cég ID (ref): " + cegID);

        String datum = getTextContent(kiadva, "Datum");
        System.out.println("Kiadás dátuma: " + datum);
    }

    // Segédfüggvény a szövegtartalom lekérésére
    private static String getTextContent(Element parent, String tagName) {
        Node node = parent.getElementsByTagName(tagName).item(0);
        if (node != null) {
            return node.getTextContent();
        }
        return "";
    }
}
