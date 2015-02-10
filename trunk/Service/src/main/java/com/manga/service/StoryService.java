package com.manga.service;

import com.manga.dao.impl.StoryDaoImpl;
import com.manga.domain.Story;
import com.manga.dto.Stories;
import com.manga.util.CreatePDF;
import com.manga.util.DomainToDTO;
import com.manga.util.RemoveUTF8;
import com.sun.java.browser.plugin2.DOM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Response;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by khangtnse60992 on 1/24/2015.
 */
@Controller
@RequestMapping("/")
public class StoryService {

    public static final String APPLICATION_XML_VALUE = MediaType.APPLICATION_XML_VALUE;
    @Autowired
    StoryDaoImpl storyDAO;

    @Autowired
    ServletContext context;


    @RequestMapping(value="/", method = RequestMethod.GET)
    public String index() {
        return "hello";
    }

    @RequestMapping(value="/exportPDF", method = RequestMethod.GET, produces = "application/pdf")
    public void download(@RequestParam("story") String story, @RequestParam("chapter") String chapter,
                                         HttpServletResponse response, HttpServletRequest request) {
        try {
            String path = context.getRealPath("/");
            System.out.print(story);
            byte[] content = CreatePDF.create(story, chapter, path);
            response.setContentLength(content.length);
            response.getOutputStream().write(content);
            response.setCharacterEncoding("UTF-8");
            response.getOutputStream().flush();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @RequestMapping(value="/download", method=RequestMethod.GET)
    public String download(@RequestParam("story") String story, @RequestParam("chapter") String chapter,
                           HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            session.setAttribute("story", story);
            session.setAttribute("chapter", chapter);
            String xmlPath = "http://lazyeng.com:8080/xmlservice/getStory?name=" + URLEncoder.encode(story, "UTF-8");
            session.setAttribute("xmlPath", xmlPath);


            return "download";
        }catch (Exception ex) {
            return null;
        }
    }

    @RequestMapping(value = "/all",method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE )
    public @ResponseBody
    Stories getAllStory(@RequestParam("limit") int limit, @RequestParam("offset") int offset, HttpServletResponse response) {
       List<Story> storyList = storyDAO.getAllStory(limit, offset);
        response.addHeader("Access-Control-Allow-Origin", "*");
        return DomainToDTO.convertFromList(storyList);
    }
    @RequestMapping(produces = MediaType.APPLICATION_XML_VALUE, value="/update", method = RequestMethod.GET)
    public @ResponseBody Stories getNewUpdate(@RequestParam("limit") int limit,
                                              @RequestParam("offset") int offset, HttpServletResponse response) {
        List<Story> storyList = storyDAO.getNewUpdate(limit, offset);
        response.addHeader("Access-Control-Allow-Origin", "*");
        return DomainToDTO.convertFromList(storyList);

    }

    @RequestMapping(produces = APPLICATION_XML_VALUE, value="/getByName", method = RequestMethod.GET)
    public @ResponseBody Stories getByName(@RequestParam("name") String name, @RequestParam("limit") int limit,
                                           @RequestParam("offset") int offset, HttpServletResponse response) {

        String search = RemoveUTF8.removeAccent(name.toLowerCase());
        List<Story> storyList = storyDAO.getByName(search, limit, offset);
        response.addHeader("Access-Control-Allow-Origin", "*");
        return DomainToDTO.convertFromList(storyList);
    }

    @RequestMapping(produces = APPLICATION_XML_VALUE, value="/getByType", method = RequestMethod.GET)
    public @ResponseBody Stories getByType(@RequestParam("type") String type, @RequestParam("limit") int limit,
                                           @RequestParam("offset") int offset, HttpServletResponse response) {

        List<Story> storyList = storyDAO.getByType(type, limit, offset);
        response.addHeader("Access-Control-Allow-Origin", "*");
        return DomainToDTO.convertFromList(storyList);
    }

    @RequestMapping(produces = MediaType.APPLICATION_XML_VALUE, value="/getIzManga", method = RequestMethod.GET)
    public @ResponseBody Stories getIzManga(@RequestParam("limit") int limit,
                                            @RequestParam("offset") int offset, HttpServletResponse response) {
        List<Story> storyList = storyDAO.getIzManga(limit, offset);
        response.addHeader("Access-Control-Allow-Origin", "*");
        return DomainToDTO.convertFromList(storyList);
    }

    @RequestMapping(produces = MediaType.APPLICATION_XML_VALUE, value="/getKissManga", method = RequestMethod.GET)
    public @ResponseBody Stories getKissManga(@RequestParam("limit") int limit,
                                              @RequestParam("offset") int offset, HttpServletResponse response) {
        List<Story> storyList = storyDAO.getKissManga(limit, offset);
        response.addHeader("Access-Control-Allow-Origin", "*");
        return DomainToDTO.convertFromList(storyList);
    }

    @RequestMapping(produces = MediaType.APPLICATION_XML_VALUE, value="/getMangaHead", method = RequestMethod.GET)
    public @ResponseBody Stories getMangaHead(@RequestParam("limit") int limit,
                                              @RequestParam("offset") int offset, HttpServletResponse response) {
        List<Story> storyList = storyDAO.getMangaHead(limit, offset);
        response.addHeader("Access-Control-Allow-Origin", "*");
        return DomainToDTO.convertFromList(storyList);
    }

    @RequestMapping(value = "/getStory", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
    public @ResponseBody Stories getStory(@RequestParam("name") String name, HttpServletResponse response) {
        name = RemoveUTF8.toUrlFriendly(name.toLowerCase());
        Story story = storyDAO.getStory(name);
        if(story == null) {
            return new Stories();
        }
        response.addHeader("Access-Control-Allow-Origin", "*");
        return DomainToDTO.convertFromObject(story);
    }
}
