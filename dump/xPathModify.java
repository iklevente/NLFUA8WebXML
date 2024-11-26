public import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class xPathModify {
    public static void main(String[] args) {
        try {
            // XML betöltése
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse("orarendNeptunkod.xml");

            // Módosítások
            Node szakNode = doc.getElementsByTagName("szak").item(0);
            szakNode.setTextContent("PTI-MI");

            Node targyNode = doc.getElementsByTagName("nev").item(0);
            targyNode.setTextContent(targyNode.getTextContent() + " (BL)");

            Node helyszinNode = doc.getElementsByTagName("helyszin").item(2);
            helyszinNode.setTextContent("XXXVII");

            // Módosított XML mentése
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult("orarendNeptunkod1.xml");
            transformer.transform(source, result);

            System.out.println("Módosítások mentve az orarendNeptunkod1.xml fájlba.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
 {
    
}
