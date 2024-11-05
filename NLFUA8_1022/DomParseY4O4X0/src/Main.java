import domnlfua81015.DOMRead;
import domnlfua81015.DomWritenlfua8;
import org.w3c.dom.Document;


public class Main {
    public static void main(String[] args) throws Exception {
        String path = "src/resources/NLFUA8_orarend.xml";

        DOMRead dm = new DOMRead(path);
        Document dom = dm.buildUpDom();
        DomWritenlfua8 write = new DomWritenlfua8(dom.getDocumentElement());

        try {
            write.printXmlStructure(dom.getDocumentElement(),"");
            write.writeXmlFile(dom, "src/resources/nlfua8_orarend1.xml");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}