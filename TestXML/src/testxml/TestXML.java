package testxml;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

public class TestXML {

    public static void main(String[] args) {

        SAXParserFactory spf = SAXParserFactory.newInstance();
        
        System.out.println("Parse will " + (spf.isNamespaceAware()? "" : "not ") + "be namespace aware");
        System.out.println("Parse will " + (spf.isValidating() ? "" : "not ") + "validate XML");
        
        SAXParser perser = null;
        
        try {
            perser = spf.newSAXParser();
        } catch (ParserConfigurationException | SAXException ex) {
            Logger.getLogger(TestXML.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Parser object is: " + perser);
        
        
        
    }

}
