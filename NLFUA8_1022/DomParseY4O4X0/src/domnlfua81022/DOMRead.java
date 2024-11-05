package domnlfua81022;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.print.Doc;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class DOMRead {

    public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
        File xmlFile = new File("src/resources/hallgatonlfua8.xml");;

        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = builder.parse(xmlFile);

        doc.getDocumentElement().normalize();

        System.out.println("Root element: " + doc.getDocumentElement().getNodeName());

        NodeList nodeList = doc.getElementsByTagName("hallgato");

        for(int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);

            System.out.println("\nCurrent Element: " + node.getNodeName());

            if(node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;

                String uid = element.getAttribute("id");

                Node node1 = element.getElementsByTagName("vezeteknev").item(0);
                String fname = node1.getTextContent();

                Node node2 = element.getElementsByTagName("keresztnev").item(0);
                String lname = node2.getTextContent();

                Node node3 = element.getElementsByTagName("foglalkozas").item(0);
                String profession = node3.getTextContent();

                System.out.println("UID: " + uid);
                System.out.println("First name: " + fname);
                System.out.println("Last name " + lname);
                System.out.println("Profession " + profession);
            }
        }

    }
}
