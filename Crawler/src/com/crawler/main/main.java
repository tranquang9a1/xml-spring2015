package com.crawler.main;

import com.crawler.controller.ParserFromIZManga;
import com.crawler.controller.ParserFromKissManga;
import com.crawler.controller.ParserFromMangaHead;
import com.crawler.dao.StoryDAO;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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
        ParserFromMangaHead.getStoriesMangaHead();
        ParserFromKissManga.getStoriesKissManga();
        ParserFromIZManga.getStoriesIZManga();

    }

}
