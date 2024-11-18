import domnlfua81015.DOMRead;
import domnlfua81015.DomWriteNLFUA8;
import org.w3c.dom.Document;

public class MainApp {
    public static void main(String[] args) throws Exception {
        String inputPath = "src/resources/NLFUA8_orarend.xml";

        DOMRead domReader = new DOMRead(inputPath);
        Document document = domReader.buildUpDom();
        DomWriteNLFUA8 domWriter = new DomWriteNLFUA8(document.getDocumentElement());

        try {
            domWriter.displayXmlStructure(document.getDocumentElement(), "");
            domWriter.saveXmlToFile(document, "src/resources/NLFUA8_orarend1.xml");
        } catch (Exception ex) {
            System.out.println("An error occurred: " + ex.getMessage());
        }
    }
}
