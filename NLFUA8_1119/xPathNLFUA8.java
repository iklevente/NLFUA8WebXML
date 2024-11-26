import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class xPathNLFUA8 {
    public static void main(String[] args) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse("studentNeptunkod.xml");

            XPath xpath = XPathFactory.newInstance().newXPath();

            String expression1 = "//student[keresztnev='Péter']";
            NodeList students = (NodeList) xpath.evaluate(expression1, doc, XPathConstants.NODESET);
            
            System.out.println("Keresztnev = Péter lekérdezés eredménye:");
            for (int i = 0; i < students.getLength(); i++) {
                System.out.println(students.item(i).getTextContent());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
