package domnlfua81022;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.print.Doc;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.concurrent.ExecutionException;

public class DOMWrite1 {

    public static void main(String[] args) throws Exception{
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = builder.newDocument();

        Element root = doc.createElementNS("DOMnlfua8", "SZK_orarend");
        doc.appendChild(root);

        root.appendChild(createOraElement(doc, "o1", "elmelet", "Elektrotechnika és elektronika", "Hétfő", "8:00", "10:00", "A2/III. Előadó", "Szabó Nórbert", "Mérnökinformatika BSc"));
        root.appendChild(createOraElement(doc, "O2", "elmelet", "Webtechnológia I", "Hétfő", "10:00", "12:00", "A2/I. Előadó", "Agárdi Anita", "Mérnökinformatika BSc"));
        root.appendChild(createOraElement(doc, "O3", "gyakorlat", "Angol szaknyelv", "Hétfő", "12:00", "14:00", "A2/223", "Dorkó Dóra", "Mérnökinformatika BSc"));
        root.appendChild(createOraElement(doc, "O4", "gyakorlat", "Webtechnológia I", "Hétfő", "14:00", "16:00", "Inf/101", "Agárdi Anita", "Mérnökinformatika BSc"));
        root.appendChild(createOraElement(doc, "O5", "elmelet", "Mobilprogramozás alapjai", "Hétfő", "16:00", "18:00", "Inf/101", "Agárdi Anita", "Mérnökinformatika BSc"));
        root.appendChild(createOraElement(doc, "O6", "gyakorlat", "Mobilprogramozás alapjai", "Hétfő", "18:00", "20:00", "Inf/101", "Agárdi Anita", "Mérnökinformatika BSc"));
        root.appendChild(createOraElement(doc, "O7", "elmelet", "Windows rendszergazda", "Kedd", "8:00", "10:00", "Inf/103", "Wagner György", "Mérnökinformatika BSc"));
        root.appendChild(createOraElement(doc, "O8", "gyakorlat", "Webes adatkezelő környezetek", "Kedd", "12:00", "14:00", "Inf/101", "Dr Bednarik László", "Mérnökinformatika BSc"));
        root.appendChild(createOraElement(doc, "O9", "gyakorlat", "Elektrotechnika és elektronika", "Szerda", "8:00", "10:00", "A1/310", "Szabó Norbert", "Mérnökinformatika BSc"));
        root.appendChild(createOraElement(doc, "O10", "gyakorlat", "Mesterséges inteligencia", "Szerda", "10:00", "12:00", "A1/218", "Fazekas Levente", "Mérnökinformatika BSc"));
        root.appendChild(createOraElement(doc, "O11", "gyakorlat", "Windows rendszergazda", "Szerda", "12:00", "14:00", "Inf/101", "Wagner György", "Mérnökinformatika BSc"));
        root.appendChild(createOraElement(doc, "O12", "elmelet", "Webes adatkezelő környezetek", "Szerda", "14:00", "16:00", "Inf/102", "Dr Kovács László", "Mérnökinformatika BSc"));
        root.appendChild(createOraElement(doc, "O13", "elmelet", "Mesterséges inteligencia", "Szerda", "16:00", "18:00", "A1/XXXII előadó", "Kunné Dr. Tamás Judit", "Mérnökinformatika BSc"));


        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT,"yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

        DOMSource source = new DOMSource(doc);
        File myFile = new File("src/resource/nlfua8_orarend.xml");

        StreamResult consol = new StreamResult(System.out);
        StreamResult file = new StreamResult(myFile);

        transformer.transform(source,consol);
        transformer.transform(source,file);
    }

    private static Node createOraElement(Document doc,
                                         String id,
                                         String tipus,
                                         String targy,
                                         String nap,
                                         String tol,
                                         String ig,
                                         String helyszin,
                                         String oktato,
                                         String szak){
        Element ora = doc.createElement("ora");

        ora.setAttribute("id", id);
        ora.setAttribute("tipus", tipus);
        ora.appendChild(createElement(doc, "targy", targy));
        ora.appendChild(createIdopontElement(doc, nap, tol, ig));
        ora.appendChild(createElement(doc, "helyszin", helyszin));
        ora.appendChild(createElement(doc, "oktato", oktato));
        ora.appendChild(createElement(doc, "szak", szak));

        return ora;
    }

    private static Node createElement(Document doc, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));

       return node;
    }

    private static Node createIdopontElement(Document doc, String nap, String tol, String ig){
        Element idopont = doc.createElement("idopont");
        idopont.appendChild(createElement(doc, "nap", nap));
        idopont.appendChild(createElement(doc,"tol",tol));
        idopont.appendChild(createElement(doc,"ig",ig));

        return idopont;
    }
}
