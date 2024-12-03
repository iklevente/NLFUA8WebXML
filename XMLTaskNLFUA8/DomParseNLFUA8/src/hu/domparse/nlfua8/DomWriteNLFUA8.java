package hu.domparse.nlfua8;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DomWriteNLFUA8 {
    public static void main(String[] args) {
        try {
            // Új XML dokumentum létrehozása
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            // Root elem hozzáadása
            Element rootElement = doc.createElement("AutoRendszer");
            doc.appendChild(rootElement);

            // Gépjármű hozzáadása
            Element gepjarmu = doc.createElement("Gepjarmu");
            rootElement.appendChild(gepjarmu);

            Attr attr = doc.createAttribute("JarmuID");
            attr.setValue("4");
            gepjarmu.setAttributeNode(attr);

            Element rendszam = doc.createElement("Rendszam");
            rendszam.appendChild(doc.createTextNode("GHI-321"));
            gepjarmu.appendChild(rendszam);

            Element tipus = doc.createElement("JarmuTipus");
            tipus.appendChild(doc.createTextNode("SUV"));
            gepjarmu.appendChild(tipus);

            // XML mentése fájlba
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("XMLNLFUA8_Updated.xml"));
            transformer.transform(source, result);

            System.out.println("XML fájl sikeresen létrehozva!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
