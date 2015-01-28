package com.manga.dao.impl;

import com.manga.dao.BaseDao;
import com.manga.dao.MangaDao;
import com.manga.domain.Story;
import org.springframework.stereotype.Repository;

/**
 * Created by khangtnse60992 on 1/24/2015.
 */
@Repository
public class MangaDaoImpl extends BaseDao<Story,String> implements MangaDao {

}
