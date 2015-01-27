/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.crawler.controller;

import static com.crawler.main.main.dao;
import com.mongodb.DBCursor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author QuangTV
 */
public class ParserFromMangaHead {
    public static void getStoriesMangaHead() {
        try {
            String homelink = "http://mangahead.com/Manga-Raw-Scan";
            Document linkget = Jsoup.connect(homelink).timeout(60 * 1000).get();

            Elements stories = linkget.getElementsByClass("right");
            int i;
            int j = 0;
            List<String> story = new ArrayList<String>();
            String href;
            String time;
            Elements element = stories.get(0).getElementsByTag("a");
            Elements times = stories.get(0).getElementsByClass("time");
            for (i = 0; i < element.size(); i++) {
                href = element.get(i).attr("href");
                time = times.get(i).text();
                //System.out.println("Link: " + href + " Time: " + time);
                if (time.equalsIgnoreCase("Yesterday")) {
                    story.add(href);
                }
                //System.out.println(href);

            }
            getChapterByStoriesMangaHead(story);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void getChapterByStoriesMangaHead(List<String> href) {
        try {
            String title = "";
            String author = "";
            String status = "";
            String source = "";
            String type = "";
            String thumb = "";
            String description = "";
            String updating = "Đang cập nhật..";
            String chapter = "";
            for (int i = href.size() - 1; i >= 0; i--) {
                author = status = type = thumb = description = updating;
                source = "mangahead.com";

                String homelink = "http://mangahead.com" + href.get(i);
                Document linkget = Jsoup.connect(homelink).timeout(60 * 1000).get();
                Elements images = linkget.getElementsByClass("mangahead_thumbnail_cell");
                Elements info = linkget.getElementsByClass("mangaviewer_toppest_navig").get(0).getElementsByTag("a");
                title = info.get(2).text().trim();
                System.out.println(title);
                chapter = linkget.getElementsByClass("mangaviewer_toppest_navig").get(0).text();
                chapter = chapter.substring(chapter.lastIndexOf("/") + 2).trim();
                String data = "";
                for (Element image : images) {
                    data += image.getElementsByTag("a").attr("href").replace("?action=big&size=original&fromthumbnail=true", "\u0020").replace("/index.php/", "http://s9.mangahead.com/mangas/") + "|";
                }

                DBCursor cursor = dao.checkNewestChap(title.trim());
                // 32000 khang
                String newest_chap = "";
                Map<String, String> result = new HashMap<>();
                if (cursor != null) {
                    newest_chap = (String) cursor.next().get("newest_chap");
                }
                result.put(chapter, data);
                System.out.println(title + " - " + thumb + " - " + author + " - " + status + " - " + source + " - " + type);
                if (newest_chap == null || newest_chap.isEmpty()) {
                    dao.insertStory(title, author, status, source, type, thumb, description, result);
                } else {
                    dao.updateStory(title, author, status, source, type, thumb, description, result);
                }

            }
        } catch (Exception e) {
        }
    }

}
