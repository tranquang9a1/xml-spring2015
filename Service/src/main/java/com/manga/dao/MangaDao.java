package com.manga.dao;

import com.manga.domain.Story;

import java.util.List;

/**
 * Created by khangtnse60992 on 1/24/2015.
 */
public interface MangaDao {
    public List<Story> getNewUpdate() ;
    public List<Story> getByName(String name);
    public List<Story> getByType(String type);
}
