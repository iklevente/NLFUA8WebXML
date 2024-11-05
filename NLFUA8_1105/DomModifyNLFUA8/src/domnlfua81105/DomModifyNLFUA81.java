package domnlfua81105;

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class DomModifyNLFUA81 {
    public static void main(String[] args) {
        try {
            File xmlFile = new File("orarendnlfua8.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            NodeList oraList = doc.getElementsByTagName("ora");
            for (int i = 0; i < oraList.getLength(); i++) {
                Element ora = (Element)oraList.item(i);
                NodeList oraadoList = ora.getElementsByTagName("oraado");
                
                if (oraadoList.getLength() == 0) {
                    Element oraado = doc.createElement("oraado");
                    oraado.setTextContent("Tanár");
                    ora.appendChild(oraado);
                }
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult fileResult = new StreamResult(new File("orarendModifynlfua8.xml"));
            transformer.transform(source, fileResult);

            for (int i = 0; i < oraList.getLength(); i++) {
                Element ora = (Element) oraList.item(i);
                NodeList tipusList = ora.getElementsByTagName("tipus");

                if (tipusList.getLength() > 0 && "gyakorlat".equals(tipusList.item(0).getTextContent())) {
                    tipusList.item(0).setTextContent("gyakorlat-előadás");
                }
            }

            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
