package domNLFUA81015;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = builder.newDocument();

        Element root = doc.createElementNS("DOMNLFUA8", "IKL_orarend");
        doc.appendChild(root);

        root.appendChild(createOra(doc,
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

        DOMSource source = new DOMSource(doc);
        File myFile = new File("src/resources/NLFUA8_orarend1.xml");

        StreamResult console = new StreamResult(System.out);
        StreamResult file = new StreamResult(myFile);

        transformer.transform(source,console);
        transformer.transform(source,file);
    }

    private static Node createOra(Document doc,
                                  String id,
                                  String tipus,
                                  String targy,
                                  String nap,
                                  String tol,
                                  String ig,
                                  String helyszin,
                                  String oktato,
                                  String szak) {

        Element ora = doc.createElement("ora");

        ora.setAttribute("id", id);
        ora.setAttribute("tipus", tipus);
        ora.appendChild(createElement(doc, "targy", targy));
        ora.appendChild(createIdopontElement(doc,nap,tol,ig));
        ora.appendChild(createElement(doc, "helyszin", helyszin));
        ora.appendChild(createElement(doc, "oktato", oktato));
        ora.appendChild(createElement(doc, "szak", szak));

        return ora;
    }

    private static Node createElement(Document doc, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));

        return node;
    }

    private static Node createIdopontElement(Document doc, String nap, String tol, String ig){
        Element idopont = doc.createElement("idopont");

        idopont.appendChild(createElement(doc, "nap", nap));
        idopont.appendChild(createElement(doc, "tol", tol));
        idopont.appendChild(createElement(doc, "ig", ig));

        return idopont;
    }
}
