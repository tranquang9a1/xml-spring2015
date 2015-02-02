package com.crawler.main;

import com.crawler.controller.ParserFromIZManga;
import com.crawler.controller.ParserFromKissManga;
import com.crawler.controller.ParserFromMangaHead;
import com.crawler.dao.StoryDAO;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author QuangTV
 */
public class main {

    public static final StoryDAO dao = new StoryDAO();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
          //ParserFromMangaHead.getStoriesMangaHead();
        //ParserFromKissManga.getStoriesKissManga();
        ParserFromIZManga.getStoriesIZManga();

    }

}
