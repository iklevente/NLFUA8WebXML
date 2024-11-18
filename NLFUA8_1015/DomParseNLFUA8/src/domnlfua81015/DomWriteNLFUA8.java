package domnlfua81015;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class DomWriteNLFUA8 {

    public static void main(String[] args) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.newDocument();

        Element rootElement = document.createElementNS("DOMNLFUA8", "IKL_orarend");
        document.appendChild(rootElement);

        rootElement.appendChild(buildOraElement(document,
                "o1",
                "elmelet",
                "Elektrotechnika és elektronika",
                "Hétfő",
                "8:00",
                "10:00",
                "A2/III. Előadó",
                "Szabó Nórbert",
                "Mérnökinformatika BSc"));

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

        DOMSource source = new DOMSource(document);
        File outputFile = new File("src/resources/NLFUA8_orarend1.xml");

        transformer.transform(source, new StreamResult(System.out));
        transformer.transform(source, new StreamResult(outputFile));
    }

    private static Node buildOraElement(Document doc,
                                        String id,
                                        String tipus,
                                        String targy,
                                        String nap,
                                        String tol,
                                        String ig,
                                        String helyszin,
                                        String oktato,
                                        String szak) {
        Element oraElement = doc.createElement("ora");

        oraElement.setAttribute("id", id);
        oraElement.setAttribute("tipus", tipus);
        oraElement.appendChild(createChildElement(doc, "targy", targy));
        oraElement.appendChild(buildIdopontElement(doc, nap, tol, ig));
        oraElement.appendChild(createChildElement(doc, "helyszin", helyszin));
        oraElement.appendChild(createChildElement(doc, "oktato", oktato));
        oraElement.appendChild(createChildElement(doc, "szak", szak));

        return oraElement;
    }

    private static Node createChildElement(Document doc, String tag, String value) {
        Element element = doc.createElement(tag);
        element.appendChild(doc.createTextNode(value));
        return element;
    }

    private static Node buildIdopontElement(Document doc, String nap, String tol, String ig) {
        Element idopontElement = doc.createElement("idopont");

        idopontElement.appendChild(createChildElement(doc, "nap", nap));
        idopontElement.appendChild(createChildElement(doc, "tol", tol));
        idopontElement.appendChild(createChildElement(doc, "ig", ig));

        return idopontElement;
    }
}
