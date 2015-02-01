/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crawler.xml;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import org.xml.sax.SAXException;
import stories.Stories;

/**
 *
 * @author khangtnse60992
 */
public class JAXBValidate {

    public static boolean validateSchema() {
        try {
            JAXBContext jc = JAXBContext.newInstance("stories");
            Unmarshaller u = jc.createUnmarshaller();
            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = sf.newSchema(new File("src/com/crawler/xml/mangaSchema.xsd"));
            u.setSchema(schema);
            File f = new File("story.xml");
            Stories unmarshal = (Stories) u.unmarshal(f);
        } catch (JAXBException ex) {
            Logger.getLogger(JAXBValidate.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (SAXException ex) {
            Logger.getLogger(JAXBValidate.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;

    }
}
