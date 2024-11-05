package domnlfua81015;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;


public class DOMRead {

    public static void main(String[] args) throws Exception {
       File xmlFile = new File("src/resources/NLFUA8_orarend.xml");

       DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
       Document doc = builder.parse(xmlFile);

       doc.getDocumentElement().normalize();

       System.out.println("Root element: " + doc.getDocumentElement().getNodeName());

       NodeList nodeList = doc.getDocumentElement().getElementsByTagName("ora");

       for (int i = 0; i < nodeList.getLength(); i++){
           Node node = nodeList.item(i);
           System.out.println("\nCurrent element: " + node.getNodeName());

           if(node.getNodeType() == Node.ELEMENT_NODE) {
               Element element = (Element) node;

               String id = element.getAttribute("id");
               String tipus = element.getAttribute("tipus");

               Node targyNode = element.getElementsByTagName("targy").item(0);
               String targy = targyNode.getTextContent();
               Node idopontNode = element.getElementsByTagName("idopont").item(0);
               String idopont = idopontNode.getTextContent();
               Node helyszinNode = element.getElementsByTagName("helyszin").item(0);
               String helyszin = helyszinNode.getTextContent();
               Node oktatoNode = element.getElementsByTagName("oktato").item(0);
               String oktato = oktatoNode.getTextContent();
               Node szakNode = element.getElementsByTagName("szak").item(0);
               String szak = szakNode.getTextContent();

               System.out.println("ID: " + id);
               System.out.println("Tipus: " + tipus);
               System.out.println("Tárgy: " + targy);
               System.out.println("Idopont: " + idopont);
               System.out.println("Helyszin: " + helyszin);
               System.out.println("Oktato: " + oktato);
               System.out.println("Szak: " + szak);
           }
       }
    }
}
