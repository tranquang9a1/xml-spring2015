package com.manga.service;

import com.manga.dao.impl.MangaDaoImpl;
import com.manga.domain.Chapter;
import com.manga.domain.Manga;
import com.manga.domain.Mangas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
    public @ResponseBody Mangas getAllManga() {
       List<Manga> mangaList = mangaDao.findAll();
        Mangas mangas = new Mangas();
        mangas.setManga(mangaList);
       return  mangas;
    }
}
