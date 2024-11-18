package domnlfua81022;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class DomReaderNLFUA8 {

    public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
        File sourceFile = new File("src/resources/hallgatoNLFUA8.xml");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(sourceFile);

        document.getDocumentElement().normalize();

        System.out.println("Root Node: " + document.getDocumentElement().getNodeName());

        NodeList students = document.getElementsByTagName("hallgato");

        for (int index = 0; index < students.getLength(); index++) {
            Node studentNode = students.item(index);

            System.out.println("\nNode: " + studentNode.getNodeName());

            if (studentNode.getNodeType() == Node.ELEMENT_NODE) {
                Element studentElement = (Element) studentNode;

                String identifier = studentElement.getAttribute("id");
                String lastName = extractTextContent(studentElement, "vezeteknev");
                String firstName = extractTextContent(studentElement, "keresztnev");
                String job = extractTextContent(studentElement, "foglalkozas");

                System.out.println("ID: " + identifier);
                System.out.println("Last Name: " + lastName);
                System.out.println("First Name: " + firstName);
                System.out.println("Job: " + job);
            }
        }
    }

    private static String extractTextContent(Element element, String tagName) {
        Node node = element.getElementsByTagName(tagName).item(0);
        return node != null ? node.getTextContent()
