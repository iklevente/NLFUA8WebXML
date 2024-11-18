package domnlfua81105;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

class DomModifyNlfua8 {

    public static void main(String[] args) {
        try {
            File xmlFile = new File("resources/hallgato.xml");
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(xmlFile);

            doc.getDocumentElement().normalize();

            NodeList students = doc.getElementsByTagName("hallgato");

            for (int i = 0; i < students.getLength(); i++) {
                Node student = students.item(i);

                if (student.getNodeType() == Node.ELEMENT_NODE) {
                    Element studentElement = (Element) student;

                    String id = studentElement.getAttribute("id");

                    if ("01".equals(id)) {
                        Element firstNameElement = (Element) studentElement.getElementsByTagName("keresztnev").item(0);
                        if (firstNameElement != null) {
                            firstNameElement.setTextContent("Olivia");
                        }

                        Element lastNameElement = (Element) studentElement.getElementsByTagName("vezeteknev").item(0);
                        if (lastNameElement != null) {
                            lastNameElement.setTextContent("Vigh");
                        }
                    }
                }
            }

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            DOMSource source = new DOMSource(doc);

            System.out.println("---Modified File---");
            StreamResult console = new StreamResult(System.out);
            transformer.transform(source, console);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
