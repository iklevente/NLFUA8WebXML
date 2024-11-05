package domnlfua81015;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;

public class DOMRead {

    private String path;

    public DOMRead(String path) {
        this.path = path;
    }

    public Document buildUpDom() throws Exception {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document dom = builder.parse(new File(path));
        dom.getDocumentElement().normalize();

        return dom;
    }

    public void query(Document dom) {
        NodeList classList = dom.getElementsByTagName("ora");
        for(int i = 0; i < classList.getLength(); i++) {
            Node node = classList.item(i);
            NodeList childList = node.getChildNodes();

            for(int j = 0; j < childList.getLength(); j++){
                Node child = childList.item(j);

                if (child.getNodeType() == Node.ELEMENT_NODE){
                    System.out.println(child.getNodeName() + " : " + child.getTextContent());
                }
            }
            System.out.println();
        }
    }
}
