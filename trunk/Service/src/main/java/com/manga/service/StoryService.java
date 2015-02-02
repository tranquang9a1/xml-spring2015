package com.manga.service;

import com.manga.dao.impl.StoryDaoImpl;
import com.manga.domain.Story;
import com.manga.dto.Stories;
import com.manga.util.DomainToDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by khangtnse60992 on 1/24/2015.
 */
@Controller
@RequestMapping("/")
public class StoryService {

    @Autowired
    StoryDaoImpl storyDAO;

    @RequestMapping(value = "/all",method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE )
    public @ResponseBody
    Stories getAllStory(@RequestParam("limit") int limit, @RequestParam("offset") int offset) {
       List<Story> storyList = storyDAO.getAllStory(limit, offset);
        Stories stories = new Stories();
        for (int i = 0; i < storyList.size(); i++) {
            stories.getStoryDTO().add(DomainToDTO.convertToStoryDTO(storyList.get(i)));
        }
       return  stories;
    }
    @RequestMapping(produces = MediaType.APPLICATION_XML_VALUE, value="/update", method = RequestMethod.GET)
    public @ResponseBody Stories getNewUpdate() {
        List<Story> storyList = storyDAO.getNewUpdate(3,0);
        Stories stories = new Stories();
        for (int i = 0; i < storyList.size(); i++) {
            stories.getStoryDTO().add(DomainToDTO.convertToStoryDTO(storyList.get(i)));
        }
        return  stories;
    }

    @RequestMapping(produces = MediaType.APPLICATION_XML_VALUE, value="/getByName", method = RequestMethod.GET)
    public @ResponseBody Stories getByName(@RequestParam("name") String name) {
        name = name.toLowerCase();
        List<Story> storyList = storyDAO.getByName(name, 10, 0);
        Stories stories = new Stories();
        for (int i = 0; i < storyList.size(); i++) {
            stories.getStoryDTO().add(DomainToDTO.convertToStoryDTO(storyList.get(i)));
        }
        return  stories;
    }

    @RequestMapping(produces = MediaType.APPLICATION_XML_VALUE, value="/getByType", method = RequestMethod.GET)
    public @ResponseBody Stories getByType(@RequestParam("name") String type) {
        type = type.toLowerCase();
        List<Story> storyList = storyDAO.getByType(type, 3, 0);
        Stories stories = new Stories();
        for (int i = 0; i < storyList.size(); i++) {
            stories.getStoryDTO().add(DomainToDTO.convertToStoryDTO(storyList.get(i)));
        }
        return  stories;
    }
}
