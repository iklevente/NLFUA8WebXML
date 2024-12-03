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
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            // 1. Módosítás: Egy jármű rendszámának frissítése
            NodeList nodes = doc.getElementsByTagName("Gepjarmu");
            Element car = (Element) nodes.item(0);
            car.getElementsByTagName("Rendszam").item(0).setTextContent("ZZZ-999");

            // 2. Módosítás: Második jármű megtett kilométerének növelése
            Element secondCar = (Element) nodes.item(1);
            secondCar.getElementsByTagName("MegtettKilometer").item(0).setTextContent("95000");

            // 3. Módosítás: Egy új gépjármű hozzáadása
            Element newCar = doc.createElement("Gepjarmu");
            newCar.setAttribute("JarmuID", "4");

            Element newCarRendszam = doc.createElement("Rendszam");
            newCarRendszam.setTextContent("JKL-456");
            newCar.appendChild(newCarRendszam);

            Element newCarTipus = doc.createElement("JarmuTipus");
            newCarTipus.setTextContent("SUV");
            newCar.appendChild(newCarTipus);

            Element newCarMarka = doc.createElement("Marka");
            newCarMarka.setTextContent("Toyota");
            newCar.appendChild(newCarMarka);

            Element newCarKilometer = doc.createElement("MegtettKilometer");
            newCarKilometer.setTextContent("20000");
            newCar.appendChild(newCarKilometer);

            doc.getDocumentElement().appendChild(newCar);

            // 4. Módosítás: Az első jármű gyártási helyének megváltoztatása
            car.getElementsByTagName("GyartasiHely").item(0).setTextContent("Győr");

            // XML mentése
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("XMLNLFUA8_Modified.xml"));
            transformer.transform(source, result);

            System.out.println("Módosítások mentve!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
       
}
