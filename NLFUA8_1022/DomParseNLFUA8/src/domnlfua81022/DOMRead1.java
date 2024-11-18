package domnlfua81022;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class DOMRead1 {

    public static void main(String[] args) throws Exception{
        File xmlFile = new File("src/resources/Y4O4X0_orarend.xml");

        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = builder.parse(xmlFile);

        doc.getDocumentElement().normalize();
        System.out.println("Root element: " + doc.getDocumentElement().getNodeName());

        NodeList nodeList = doc.getElementsByTagName("ora");

        for (int i = 0; i < nodeList.getLength(); i++){
            Node node = nodeList.item(i);
            System.out.println("\nCurrent element: " + node.getNodeName());

            if(node.getNodeType() == Node.ELEMENT_NODE){
                Element element = (Element) node;

                String id = element.getAttribute("id");
                String tipusContent = element.getAttribute("tipus");

                Node targyNode = element.getElementsByTagName("targy").item(0);
                String targyContent = targyNode.getTextContent();

                Node idopontNode = element.getElementsByTagName("idopont").item(0);
                String idopontContent = idopontNode.getTextContent();

                Node helyszinNode = element.getElementsByTagName("helyszin").item(0);
                String helyszinContent = helyszinNode.getTextContent();

                Node oktatoNode = element.getElementsByTagName("oktato").item(0);
                String oktatoContent = oktatoNode.getTextContent();

                Node szakNode = element.getElementsByTagName("szak").item(0);
                String szakContent = szakNode.getTextContent();

                System.out.println("ID: " + id);
                System.out.println("Tipus: " + tipusContent);
                System.out.println("Tárgy: " + targyContent);
                System.out.println("Idopont: " + idopontContent);
                System.out.println("Helyszin: " + helyszinContent);
                System.out.println("Oktato: " + oktatoContent);
                System.out.println("Szak: " + szakContent);
            }
        }
    }

}
