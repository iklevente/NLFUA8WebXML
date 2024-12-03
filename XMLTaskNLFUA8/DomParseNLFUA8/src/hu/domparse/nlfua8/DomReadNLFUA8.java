package hu.domparse.nlfua8;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DomReadNLFUA8 {
   public static void main(String[] args) {
        try {
            File inputFile = new File("XMLNLFUA8.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(inputFile);
            document.getDocumentElement().normalize();

            System.out.println("Root element: " + document.getDocumentElement().getNodeName());
            NodeList nodeList = document.getChildNodes();

            printTree(nodeList, "");

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File("ReadOutput_XMLNLFUA8.xml"));
            transformer.transform(source, result);

            System.out.println("\nAz XML dokumentum tartalma mentve a 'ReadOutput_XMLNLFUA8.xml' fájlba.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printTree(NodeList nodeList, String indent) {
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                System.out.println(indent + "Element: " + node.getNodeName());
                if (node.hasAttributes()) {
                    NamedNodeMap attributes = node.getAttributes();
                    for (int j = 0; j < attributes.getLength(); j++) {
                        Node attribute = attributes.item(j);
                        System.out.println(indent + "  Attribute: " + attribute.getNodeName() + " = " + attribute.getNodeValue());
                    }
                }
                if (node.hasChildNodes()) {
                    printTree(node.getChildNodes(), indent + "  ");
                }
            } else if (node.getNodeType() == Node.TEXT_NODE && !node.getTextContent().trim().isEmpty()) {
                System.out.println(indent + "  Value: " + node.getTextContent().trim());
            }
        }
    }
}
