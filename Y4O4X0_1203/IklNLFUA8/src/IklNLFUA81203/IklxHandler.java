package IklNLFUA81203;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class IklxHandler extends DefaultHandler {
    private int indent = 0;

    private String formatAttributes(Attributes attributes) {
        int attrLength = attributes.getLength();
        if (attrLength == 0) return "";

        StringBuilder bl = new StringBuilder(",{");
        for (int i = 0; i < attrLength; i++) {
            bl.append(attributes.getLocalName(i) + "=" +attributes.getValue(i));
            if ( i < attrLength - 1) bl.append(", ");
        }

        bl.append("}");
        return bl.toString();
    }

    private void indent() {
        for (int i = 0; i < indent; i++) {
            System.out.print("  ");
        }
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        indent++;
        indent();
        System.out.println(qName + formatAttributes(attributes) + " start");
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        indent();
        indent--;
        System.out.println(qName + " end");
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
       String chars = new String(ch, start, length).trim();
       if (!chars.isEmpty()) {
           indent++;
           indent();
           indent--;
           System.out.println(chars);
       }
    }
}
