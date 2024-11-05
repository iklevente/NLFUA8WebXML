package domnlfua81015;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class DomWritenlfua8 {
    private Element element;

    public DomWritenlfua8(Element element) {
        this.element = element;
    }

    public void printXmlStructure(Element element, String indent){
        System.out.println("<" + element.getNodeName() + ">");

        NodeList nodeList = element.getChildNodes();
        for(int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if(node instanceof Element){
                printXmlStructure((Element) node, indent + "  ");
            } else if (node.getNodeType() == Node.TEXT_NODE){
                String content = node.getTextContent().trim();
                if(!content.isEmpty()) {
                    System.out.println(indent + "  " + content);
                }
            }
        }
        System.out.println("</" + element.getNodeName() + ">");
    }

    public void writeXmlFile(Document doc, String filePath) throws Exception{
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(filePath));
        transformer.transform(source, result);
        System.out.println("Sucessfully written to " + filePath);
    }
}
