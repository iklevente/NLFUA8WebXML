package domnlfua81015;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class XmlHandlerNLFUA8 {
    private final Element rootElement;

    public XmlHandlerNLFUA8(Element rootElement) {
        this.rootElement = rootElement;
    }

    public void displayXmlTree(Element currentElement, String spacing) {
        System.out.println("<" + currentElement.getNodeName() + ">");

        NodeList childNodes = currentElement.getChildNodes();
        for (int idx = 0; idx < childNodes.getLength(); idx++) {
            Node currentNode = childNodes.item(idx);
            if (currentNode instanceof Element) {
                displayXmlTree((Element) currentNode, spacing + "  ");
            } else if (currentNode.getNodeType() == Node.TEXT_NODE) {
                String textContent = currentNode.getTextContent().trim();
                if (!textContent.isEmpty()) {
                    System.out.println(spacing + "  " + textContent);
                }
            }
        }
        System.out.println("</" + currentElement.getNodeName() + ">");
    }

    public void saveXmlToFile(Document document, String destination) throws Exception {
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        DOMSource xmlSource = new DOMSource(document);
        StreamResult fileOutput = new StreamResult(new File(destination));
        transformer.transform(xmlSource, fileOutput);
        System.out.println("File successfully saved to " + destination);
    }
}
