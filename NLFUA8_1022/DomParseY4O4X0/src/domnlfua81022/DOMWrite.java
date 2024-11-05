package domnlfua81022;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class DOMWrite {

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, Exception {

        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = builder.newDocument();

        Element root = doc.createElementNS("DOMnlfua8", "hallgatok");
        doc.appendChild(root);

        root.appendChild(createHallgato(doc, "1", "Peter", "Nagy", "Web developer"));

        Transformer transformer = TransformerFactory.newInstance().newTransformer();

        transformer.setOutputProperty(OutputKeys.ENCODING,"UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT,"yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount","2");

        DOMSource source = new DOMSource(doc);
        File myFile = new File("hallgato1nlfua8.xml");

        StreamResult console = new StreamResult(System.out);
        StreamResult file = new StreamResult(myFile);

        transformer.transform(source,console);
        transformer.transform(source,file);
    }

    private static Node createHallgato(Document doc, String id, String firstName, String lastName, String profession) {
        Element hallgato = doc.createElement("hallgato");

        hallgato.setAttribute("id",id);
        hallgato.appendChild(createHallgatoElement(doc, "keresztnev", firstName));
        hallgato.appendChild(createHallgatoElement(doc, "vezeteknev", lastName));
        hallgato.appendChild(createHallgatoElement(doc,"foglalkozas", profession));

        return hallgato;
    }

    private static Node createHallgatoElement(Document doc, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));

        return node;
    }
}
