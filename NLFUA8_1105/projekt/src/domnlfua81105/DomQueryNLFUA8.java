package domnlfua8;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DomQueryNlfua8 {

	public static void main(String[] args) {
		
		try {
			File xmlFile = new File("resources/hallgato.xml");
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = builder.parse(xmlFile);
			
			doc.getDocumentElement().normalize();	
			System.out.println("Gyökér elem: " + doc.getDocumentElement().getNodeName());
			
			NodeList hallgatoList = doc.getElementsByTagName("hallgato");
			System.out.println("------------------");
			
			for (int i = 0; i < hallgatoList.getLength(); i++) {
				Node hallgatoNode = hallgatoList.item(i);
				System.out.println("Aktuális elem: " + hallgatoNode.getNodeName());
				
				if(hallgatoNode.getNodeType() == Node.ELEMENT_NODE) {
					Element hallgato = (Element) hallgatoNode;
					
					System.out.println("Hallgato ID: " + hallgato.getAttribute("id"));
					
					NodeList hallgatoNevList = hallgato.getElementsByTagName("vezeteknev");
					
					for(int j = 0; j < hallgatoNevList.getLength(); j++) {
						Node hallgatoNevNode = hallgatoNevList.item(j);
						
						if(hallgatoNevNode.getNodeType() == Node.ELEMENT_NODE) {
							Element hallgatoNev = (Element) hallgatoNevNode;
							System.out.println("Vezeteknev: " + hallgatoNev.getTextContent());
						}
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
