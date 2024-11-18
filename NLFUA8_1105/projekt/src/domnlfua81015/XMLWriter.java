package domnlfua81022;

import java.io.File;
import javax.xml.transform.*;
import org.w3c.dom.*;

public class XMLWriter {

    public static void main(String[] args) {
    }

    private static void saveDocumentToFile(Document document, String filePath) throws Exception {
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(new File(filePath));
        transformer.transform(source, result);
    }
}
