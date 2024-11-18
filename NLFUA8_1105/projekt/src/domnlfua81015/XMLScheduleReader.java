package domnlfua81022;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.File;

public class XMLScheduleReader {

    public static void main(String[] args) {
        File xmlFile = new File("resources/NLFUA8_orarend.xml");

        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = builder.parse(xmlFile);

            document.getDocumentElement().normalize();

            NodeList scheduleNodes = document.getElementsByTagName("ora");
            for (int i = 0; i < scheduleNodes.getLength(); i++) {
                Node scheduleNode = scheduleNodes.item(i);
                if (scheduleNode.getNodeType() == Node.ELEMENT_NODE) {
                    displayScheduleDetails((Element) scheduleNode);
                    System.out.println();
                }
            }
        } catch (Exception ex) {
            System.err.println("Error reading XML file: " + ex.getMessage());
        }
    }

    private static void displayScheduleDetails(Element scheduleElement) {
        System.out.println("Schedule ID: " + scheduleElement.getAttribute("id"));
        System.out.println("Type: " + scheduleElement.getAttribute("tipus"));

        displayChildTextContent(scheduleElement, "targy", "Subject: ");
        displayTimeDetails(scheduleElement);
        displayChildTextContent(scheduleElement, "helyszin", "Location: ");
        displayChildTextContent(scheduleElement, "oktato", "Instructor: ");
    }

    private static void displayChildTextContent(Element parentElement, String tagName, String label) {
        NodeList childNodes = parentElement.getElementsByTagName(tagName);
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node childNode = childNodes.item(i);
            if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                System.out.println(label + childNode.getTextContent().trim());
            }
        }
    }

    private static void displayTimeDetails(Element scheduleElement) {
        NodeList timeNodes = scheduleElement.getElementsByTagName("idopont");
        for (int i = 0; i < timeNodes.getLength(); i++) {
            Node timeNode = timeNodes.item(i);
            if (timeNode.getNodeType() == Node.ELEMENT_NODE) {
                System.out.print("Time: ");
                NodeList timeDetails = timeNode.getChildNodes();
                for (int j = 0; j < timeDetails.getLength(); j++) {
                    Node detail = timeDetails.item(j);
                    if (detail.getNodeType() == Node.ELEMENT_NODE) {
                        System.out.print(detail.getTextContent().trim() + " ");
                    }
                }
                System.out.println();
            }
        }
    }
}
