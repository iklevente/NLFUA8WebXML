package domnlfua81105;

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class DomModifyNLFUA8 {
    public static void main(String[] args) {
        try {
            File xmlFile = new File("nlfua8hallgato.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            NodeList hallgatoList = doc.getElementsByTagName("hallgato");
            for (int i = 0; i < hallgatoList.getLength(); i++) {
                Element hallgato = (Element) hallgatoList.item(i);
                if ("01".equals(hallgato.getAttribute("id"))) {
                    hallgato.getElementsByTagName("keresztnev").item(0).setTextContent("NewFirstName");
                    hallgato.getElementsByTagName("vezeteknev").item(0).setTextContent("NewLastName");
                }
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
