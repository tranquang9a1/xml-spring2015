/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crawler.xml;

import com.sun.org.apache.xerces.internal.dom.DOMImplementationImpl;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import stories.Chapter;
import stories.ChapterType;
import stories.StoryType;

/**
 *
 * @author khangtnse60992
 */
public class DomConvert {

    public static StoryType ObjectToStoryType(String title, String author, String status,
            String source, String type, String image,
            String description, Map<String, String> result) {
        StoryType storyType = new StoryType();
        storyType.setName(title);
        storyType.setAuthor(author);
        storyType.setStatus(status);
        storyType.setSource(source);
        List<String> types = new ArrayList<>();
        for (String genre : type.split(",")) {
            types.add(genre);
        }
        storyType.setType(types);
        storyType.setImage(image);
        storyType.setDescription(description);
        //List<ChapterType> chapters = new ArrayList<>();
        ChapterType chapterType = new ChapterType();
        String newest_chap ="";
        Chapter chapter = new Chapter();
        for (Map.Entry<String, String> entry : result.entrySet()) {
//                BasicDBObject chapter = new BasicDBObject();
                System.out.println("Log Name Chapter :" + entry.getKey() );
                chapterType.setName(entry.getKey());
                chapterType.setData(entry.getValue());
                chapter.getChapter().add(chapterType);
                newest_chap = entry.getKey();
            }
        storyType.setChapters(chapter);
        storyType.setNewestChap(newest_chap);
        storyType.setUpdateDate(System.currentTimeMillis()/1000);
        return storyType;

    }

    public void convertXML(StoryType manga) {
        try {
            DocumentBuilderFactory fac = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = fac.newDocumentBuilder();
            DOMImplementationImpl dom = (DOMImplementationImpl) builder.getDOMImplementation();
            Document doc = builder.newDocument();
            Element root = doc.createElement("stories");
            doc.appendChild(root);
            Element name = doc.createElement("name");
            Element author = doc.createElement("author");
            Element status = doc.createElement("status");
            Element source = doc.createElement("source");
            Element updateDate = doc.createElement("updateDate");
            Element newestChap = doc.createElement("newestChap");
            name.setTextContent(manga.getName());
            author.setTextContent(manga.getAuthor());
            status.setTextContent(manga.getStatus());
            source.setTextContent(manga.getSource());
            updateDate.setTextContent(String.valueOf(manga.getUpdateDate()));
            newestChap.setTextContent(manga.getNewestChap());
            Element chapters = doc.createElement("chapters");
            root.appendChild(name);
            root.appendChild(author);
            root.appendChild(status);
            root.appendChild(source);
            root.appendChild(updateDate);
            root.appendChild(newestChap);
            for (int i = 0; i < manga.getType().size(); i++) {
                Element type = doc.createElement("type");
                type.setTextContent(manga.getType().get(i));
                root.appendChild(type);
            }
            for (int i = 0; i < manga.getChapters().getChapter().size(); i++) {
                Element chapter = doc.createElement("chapter");
                name = doc.createElement("name");
                name.setTextContent(manga.getChapters().getChapter().get(i).getName());
                Element image = doc.createElement("image");
                image.setTextContent(manga.getChapters().getChapter().get(i).getData());
                chapter.appendChild(name);
                chapter.appendChild(image);
                chapters.appendChild(chapter);
            }
            root.appendChild(chapters);
            TransformerFactory transform = TransformerFactory.newInstance();
            Transformer trans = transform.newTransformer();
            trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            trans.setOutputProperty(OutputKeys.INDENT, "yes");
            StreamResult rs = new StreamResult(new File("story.xml"));
            DOMSource doms = new DOMSource(doc);
            trans.transform(doms, rs);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(DomConvert.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(DomConvert.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(DomConvert.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
