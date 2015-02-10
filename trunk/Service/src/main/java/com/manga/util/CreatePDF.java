package com.manga.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;

/**
 * Created by QuangTV on 2/6/2015.
 */
public class CreatePDF {

    public static byte[] create(String story, String chapter, String path) {



            try {
                System.out.println(story);
                String foFileName = "Story-" + RemoveUTF8.removeAccent(story)+ "-Chapter-" + RemoveUTF8.removeAccent(chapter)+".fo";
                String xslPath = path + "WEB-INF/xml/chapterFO.xsl";
                String xmlPath = "http://lazyeng.com:8080/xmlservice/getStory?name=" + URLEncoder.encode(story, "UTF-8");
                String foPath = path + "WEB-INF/xml/" + foFileName;
                String config = path + "WEB-INF/xml/FOPUserConfig.xml";
                String font = path + "WEB-INF/xml";
                File file = new File(foPath);
                if (!file.exists()) {
                    methodTrAX(xslPath, xmlPath, foPath, chapter, story);
                }
                FileInputStream input = new FileInputStream(file);

                ByteArrayOutputStream out = new ByteArrayOutputStream();

                FopFactory ff = FopFactory.newInstance();
                ff.setUserConfig(config);
                ff.getFontManager().setFontBaseURL(font);
                FOUserAgent userAgent = ff.newFOUserAgent();

                Fop fop = ff.newFop(MimeConstants.MIME_PDF, userAgent, out);
                TransformerFactory tf = TransformerFactory.newInstance();
                Transformer transfomer = tf.newTransformer();
                File fo = new File(foPath);
                Source source = new StreamSource(fo);
                Result result = new SAXResult(fop.getDefaultHandler());
                transfomer.transform(source, result);

                byte[] content = out.toByteArray();


                return content;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

    }

    private static void methodTrAX(String xslPath, String xmlPath, String output, String chapter, String story) {
        try {
            TransformerFactory tf = TransformerFactory.newInstance();
            StreamSource xsltFile = new StreamSource(xslPath);
            Transformer transform = tf.newTransformer(xsltFile);
            transform.setParameter("chapterName", chapter);
            System.out.print(story);
            transform.setParameter("story", story);
            StreamSource xmlFile = new StreamSource(xmlPath);
            StreamResult htmlFile = new StreamResult(new FileOutputStream(output));
            transform.transform(xmlFile, htmlFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
