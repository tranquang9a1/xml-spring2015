package com.manga.service;

import com.manga.dao.impl.MangaDaoImpl;
import com.manga.domain.Story;
import com.manga.dto.Stories;
import com.manga.util.DomainToDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by khangtnse60992 on 1/24/2015.
 */
@Controller
@RequestMapping("/")
public class MangaService {

    @Autowired
    MangaDaoImpl mangaDao;

    @RequestMapping(value = "/all",method = RequestMethod.GET )
    public @ResponseBody
    Stories getAllManga() {
       List<Story> storyList = mangaDao.findAll();
        Stories stories = new Stories();
        for (int i = 0; i < storyList.size(); i++) {
            stories.getStoryDTO().add(DomainToDTO.convertToStoryDTO(storyList.get(i)));
        }
       return  stories;
    }
}
