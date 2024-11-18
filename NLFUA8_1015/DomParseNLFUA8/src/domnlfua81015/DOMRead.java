package domnlfua81015;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class DOMRead {

    public static void main(String[] args) {
        try {
            File xmlFile = new File("src/resources/NLFUA8_orarend.xml");
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = builder.parse(xmlFile);

            document.getDocumentElement().normalize();
            System.out.println("Root element: " + document.getDocumentElement().getNodeName());

            NodeList oraNodes = document.getDocumentElement().getElementsByTagName("ora");

            for (int i = 0; i < oraNodes.getLength(); i++) {
                Node currentNode = oraNodes.item(i);
                System.out.println("\nCurrent element: " + currentNode.getNodeName());

                if (currentNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element oraElement = (Element) currentNode;

                    String id = oraElement.getAttribute("id");
                    String tipus = oraElement.getAttribute("tipus");

                    String targy = extractText(oraElement, "targy");
                    String idopont = extractText(oraElement, "idopont");
                    String helyszin = extractText(oraElement, "helyszin");
                    String oktato = extractText(oraElement, "oktato");
                    String szak = extractText(oraElement, "szak");

                    System.out.println("ID: " + id);
                    System.out.println("Típus: " + tipus);
                    System.out.println("Tárgy: " + targy);
                    System.out.println("Időpont: " + idopont);
                    System.out.println("Helyszín: " + helyszin);
                    System.out.println("Oktató: " + oktato);
                    System.out.println("Szak: " + szak);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String extractText(Element element, String tagName) {
        NodeList nodeList = element.getElementsByTagName(tagName);
        if (nodeList.getLength() > 0 && nodeList.item(0).getNodeType() == Node.ELEMENT_NODE) {
            return nodeList.item(0).getTextContent();
        }
        return "";
    }
}
