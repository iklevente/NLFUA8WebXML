package domnlfua81015;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class DomReaderNLFUA8 {

    private final String filePath;

    public DomReaderNLFUA8(String filePath) {
        this.filePath = filePath;
    }

    public Document loadDocument() throws Exception {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = builder.parse(new File(filePath));
        document.getDocumentElement().normalize();

        return document;
    }

    public void printElements(Document document) {
        NodeList lessonNodes = document.getElementsByTagName("ora");
        for (int index = 0; index < lessonNodes.getLength(); index++) {
            Node lessonNode = lessonNodes.item(index);
            NodeList details = lessonNode.getChildNodes();

            for (int detailIndex = 0; detailIndex < details.getLength(); detailIndex++) {
                Node detailNode = details.item(detailIndex);

                if (detailNode.getNodeType() == Node.ELEMENT_NODE) {
                    System.out.println(detailNode.getNodeName() + " : " + detailNode.getTextContent());
                }
            }
            System.out.println();
        }
    }
}
