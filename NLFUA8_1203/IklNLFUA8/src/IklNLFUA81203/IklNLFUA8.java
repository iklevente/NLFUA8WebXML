package IklNLFUA81203;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

public class IklNLFUA8 {

    public static void main(String[] args) {
        try {
            //Dokumentum olvasó létrehozása, IklXParseFactor osztály newInstance metódus
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();

            //Saját eseménykezelő létrehozása
            IklxHandler handler = new IklxHandler();

            //Dokumentum értelmezési folyamatának elindítása
            parser.parse(new File("src/resources/NLFUA8_kurzusfelvetel.xml"), handler);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
