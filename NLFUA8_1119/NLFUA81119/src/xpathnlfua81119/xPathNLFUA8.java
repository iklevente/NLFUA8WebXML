package xpathnlfua81119;

import java.io.File;
import java.io.IOException;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;




import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import org.xml.sax.SAXException;

public class xPathNLFUA8 {
	
	public static void main(String[] args) {
		
		try {
			
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			
			Document document = documentBuilder.parse("studentNLFUA8.xml");
			
			document.getDocumentElement().normalize();
			
			XPath xPath = XPathFactory.newInstance().newXPath();
			
			NodeList neptunKod = (NodeList) xPath.compile(b1).evaluate(document, XPathConstants.NODESET);
			
			for (int i = 0; i < neptunKod.getLength(); i++) {
				
				Node node = neptunKod.item(i);
				
				System.out.println("\nAktuális elem: " + node.getNodeName());
				
				if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("hallgato")) {
					
					Element element = (Element) node;
					
					System.out.println("Hallgató ID: "
							+ element.getAttribute("id"));
					
					System.out.println("Keresztnév: "
							+ element.getElementsByTagName("keresztnev").item(0).getTextContent());
					
					System.out.println("Vezetéknév: "
							+ element.getElementsByTagName("vezeteknev").item(0).getTextContent());
					
					System.out.println("Becenév: "
							+ element.getElementsByTagName("becenev").item(0).getTextContent());
					
					System.out.println("Kor: "
							+ element.getElementsByTagName("kor").item(0).getTextContent());
				}
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			
		} catch (SAXException e) {
			e.printStackTrace();
			
		} catch (IOException e) {
			e.printStackTrace();
			
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
	}

}
