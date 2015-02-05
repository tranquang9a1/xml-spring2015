package com.manga.service;

import com.manga.dao.impl.StoryDaoImpl;
import com.manga.domain.Story;
import com.manga.dto.Stories;
import com.manga.util.DomainToDTO;
import com.manga.util.RemoveUTF8;
import com.sun.java.browser.plugin2.DOM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping(value = "/all",method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE )
    public @ResponseBody
    Stories getAllStory(@RequestParam("limit") int limit, @RequestParam("offset") int offset) {
       List<Story> storyList = storyDAO.getAllStory(limit, offset);
        return DomainToDTO.convertFromList(storyList);
    }
    @RequestMapping(produces = MediaType.APPLICATION_XML_VALUE, value="/update", method = RequestMethod.GET)
    public @ResponseBody Stories getNewUpdate(@RequestParam("limit") int limit,
                                              @RequestParam("offset") int offset) {
        List<Story> storyList = storyDAO.getNewUpdate(limit, offset);
        return DomainToDTO.convertFromList(storyList);

    }

    @RequestMapping(produces = APPLICATION_XML_VALUE, value="/getByName", method = RequestMethod.GET)
    public @ResponseBody Stories getByName(@RequestParam("name") String name, @RequestParam("limit") int limit,
                                           @RequestParam("offset") int offset) {

        String search = RemoveUTF8.removeAccent(name.toLowerCase());
        List<Story> storyList = storyDAO.getByName(search, limit, offset);
        return DomainToDTO.convertFromList(storyList);
    }

    @RequestMapping(produces = APPLICATION_XML_VALUE, value="/getByType", method = RequestMethod.GET)
    public @ResponseBody Stories getByType(@RequestParam("type") String type, @RequestParam("limit") int limit,
                                           @RequestParam("offset") int offset) {

        List<Story> storyList = storyDAO.getByType(type, limit, offset);

        return DomainToDTO.convertFromList(storyList);
    }

    @RequestMapping(produces = MediaType.APPLICATION_XML_VALUE, value="/getIzManga", method = RequestMethod.GET)
    public @ResponseBody Stories getIzManga(@RequestParam("limit") int limit,
                                            @RequestParam("offset") int offset) {
        List<Story> storyList = storyDAO.getIzManga(limit, offset);
        return DomainToDTO.convertFromList(storyList);
    }

    @RequestMapping(produces = MediaType.APPLICATION_XML_VALUE, value="/getKissManga", method = RequestMethod.GET)
    public @ResponseBody Stories getKissManga(@RequestParam("limit") int limit,
                                              @RequestParam("offset") int offset) {
        List<Story> storyList = storyDAO.getKissManga(limit, offset);
        return DomainToDTO.convertFromList(storyList);
    }

    @RequestMapping(produces = MediaType.APPLICATION_XML_VALUE, value="/getMangaHead", method = RequestMethod.GET)
    public @ResponseBody Stories getMangaHead(@RequestParam("limit") int limit,
                                              @RequestParam("offset") int offset) {
        List<Story> storyList = storyDAO.getMangaHead(limit, offset);
        return DomainToDTO.convertFromList(storyList);
    }

    @RequestMapping(value = "/getStory", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
    public @ResponseBody Stories getStory(@RequestParam("name") String name) {
        name = RemoveUTF8.removeAccent(name.toLowerCase());
        Story story = storyDAO.getStory(name);
        return DomainToDTO.convertFromObject(story);
    }
}
