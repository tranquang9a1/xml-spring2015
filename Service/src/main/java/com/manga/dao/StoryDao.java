package com.manga.dao;

import com.manga.domain.Story;

import java.util.List;

/**
 * Created by khangtnse60992 on 1/24/2015.
 */
public interface StoryDao {
    public List<Story> getAllStory(int limit, int offset);
    public List<Story> getNewUpdate(int limit, int offset) ;
    public List<Story> getByName(String name, int limit, int offset);
    public List<Story> getByType(String type, int limit, int offset);
}
