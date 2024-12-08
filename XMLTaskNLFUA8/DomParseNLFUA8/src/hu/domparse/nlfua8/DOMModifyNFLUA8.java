package hu.domparse.nlfua8;

import java.io.File;
import java.time.LocalDate;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;

public class DOMModifyNFLUA8 {

    public static void main(String[] args) {
        try {
            // Fájl beolvasása
            File xmlFile = new File("src/resources/XMLNLFUA8.xml");
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            // Módosítások nyomon követése
            StringBuilder changesLog = new StringBuilder();

            // Metódus hívások
            modifyCarKilometers(doc, changesLog);
            addNewMaintenanceRecord(doc, changesLog);
            updateCustomerPhoneNumber(doc, changesLog);
            increaseRentalPrice(doc, changesLog);

            // Módosított elemek kiírása
            printModifiedXml(changesLog.toString());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // 1. Gépjármű kilométeróra állásának növelése (CAR1)
    private static void modifyCarKilometers(Document doc, StringBuilder changesLog) {
        NodeList cars = doc.getElementsByTagName("Gepjarmu");
        for (int i = 0; i < cars.getLength(); i++) {
            Element car = (Element) cars.item(i);
            if (car.getAttribute("JarmuID").equals("CAR1")) {
                Node kmNode = car.getElementsByTagName("MegtettKilometer").item(0);
                int currentKm = Integer.parseInt(kmNode.getTextContent());
                kmNode.setTextContent(String.valueOf(currentKm + 1000));
                changesLog.append("Módosított CAR1 kilométeróra: ")
                          .append(kmNode.getTextContent()).append("\n");
            }
        }
    }

    // 2. Új karbantartás bejegyzés hozzáadása CAR2 gépjárműhöz, SZ1 szerelőhöz
    private static void addNewMaintenanceRecord(Document doc, StringBuilder changesLog) {
        Element newMaintenance = doc.createElement("Karbantartas");
        newMaintenance.setAttribute("GepjarmuID", "CAR2");
        newMaintenance.setAttribute("SzereloID", "SZ1");

        Element datum = doc.createElement("Datum");
        datum.setTextContent(LocalDate.now().toString());
        newMaintenance.appendChild(datum);

        Element km = doc.createElement("MegtettKilometer");
        km.setTextContent("27000");
        newMaintenance.appendChild(km);

        Node root = doc.getDocumentElement();
        root.appendChild(newMaintenance);

        changesLog.append("Hozzáadott karbantartás CAR2-hez: ")
                  .append(newMaintenance.getTextContent()).append("\n");
    }

    // 3. Vásárló telefonszámának frissítése: V1 VasarloID esetén
    private static void updateCustomerPhoneNumber(Document doc, StringBuilder changesLog) {
        NodeList vasarlok = doc.getElementsByTagName("Vasarlo");
        for (int i = 0; i < vasarlok.getLength(); i++) {
            Element vasarlo = (Element) vasarlok.item(i);
            if (vasarlo.getAttribute("VasarloID").equals("V1")) {
                Node telefonNode = vasarlo.getElementsByTagName("Telefonszam").item(0);
                telefonNode.setTextContent("9999999");
                changesLog.append("Frissített telefonszám V1-hez: ")
                          .append(telefonNode.getTextContent()).append("\n");
            }
        }
    }

    // 4. Kölcsönzés árának növelése: CAR3 és V3 esetén +50.00
    private static void increaseRentalPrice(Document doc, StringBuilder changesLog) {
        NodeList kolcsonzesek = doc.getElementsByTagName("Kolcsonzes");
        for (int i = 0; i < kolcsonzesek.getLength(); i++) {
            Element kolcsonzes = (Element) kolcsonzesek.item(i);
            if (kolcsonzes.getAttribute("GepjarmuID").equals("CAR3") &&
                kolcsonzes.getAttribute("VasarloID").equals("V3")) {

                Node arNode = kolcsonzes.getElementsByTagName("KolcsonzesiAr").item(0);
                double currentPrice = Double.parseDouble(arNode.getTextContent());
                arNode.setTextContent(String.valueOf(currentPrice + 50.00));
                changesLog.append("Módosított kölcsönzési ár CAR3 és V3 esetén: ")
                          .append(arNode.getTextContent()).append("\n");
            }
        }
    }

    // Csak a módosítások kiírása konzolra
    private static void printModifiedXml(String changes) {
        System.out.println("-------Módosítások--------");
        System.out.println(changes);
    }
}
