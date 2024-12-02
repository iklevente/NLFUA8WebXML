package XMLConverter;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class orarendNLFUA8 {
	public static void main(String[] args) {
        try {
            
            File inputFile = new File("orarendNLFUA8.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(inputFile);

            
            Element root = document.getDocumentElement();
            Element newElement = document.createElement("NewData");
            newElement.setTextContent("ExampleContent");
            root.appendChild(newElement);

            
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File("orarendNLFUA8.out.xml"));
            transformer.transform(source, result);

            System.out.println("Az XML konvertálás befejeződött.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
