package domnlfua81022;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
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

public class DomWriterNLFUA8 {

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, TransformerException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.newDocument();

        Element rootElement = document.createElementNS("DOMNLFUA8", "hallgatok");
        document.appendChild(rootElement);

        rootElement.appendChild(createStudentElement(document, "1", "Peter", "Nagy", "Web developer"));

        Transformer transformer = TransformerFactory.newInstance().newTransformer();

        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

        DOMSource source = new DOMSource(document);
        File outputFile = new File("hallgato1NLFUA8.xml");

        StreamResult consoleOutput = new StreamResult(System.out);
        StreamResult fileOutput = new StreamResult(outputFile);

        transformer.transform(source, consoleOutput);
        transformer.transform(source, fileOutput);
    }

    private static Node createStudentElement(Document document, String id, String firstName, String lastName, String job) {
        Element student = document.createElement("hallgato");

        student.setAttribute("id", id);
        student.appendChild(createElementWithValue(document, "keresztnev", firstName));
        student.appendChild(createElementWithValue(document, "vezeteknev", lastName));
        student.appendChild(createElementWithValue(document, "foglalkozas", job));

        return student;
    }

    private static Node createElementWithValue(Document document, String tagName, String value) {
        Element element = document.createElement(tagName);
        element.appendChild(document.createTextNode(value));

        return element;
    }
}
