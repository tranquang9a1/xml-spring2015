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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 *
 * @author QuangTV
 */
public class ParserFromIZManga {

    public static void getStoriesIZManga() {
        try {
            for (int page = 1;; page++) {
                String homelink = "http://izmanga.com/danh_sach_truyen?type=new&category=all&alpha=all&page=" + page + "&state=all&group=all";
                Document linkget = Jsoup.connect(homelink).timeout(60 * 1000).get();

                Elements stories = linkget.getElementsByClass("list-truyen-item-wrap");
                if (stories != null) {
                    int i;
                    List<String> story = new ArrayList<String>();
                    String href;
                    for (i = 0; i < stories.size(); i++) {
                        href = stories.get(i).getElementsByTag("a").get(3).attr("href");
                        story.add(href.substring(href.indexOf("-") + 1, href.length()));
                    }
                    getChapterByStoriesIZManga(story);
                } else {
                    break;
                }

            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void getChapterByStoriesIZManga(List<String> story) {
        try {
            String title = "";
            String author = "";
            String status = "";
            String source = "";
            String type = "";
            String image = "";
            String description = "";
            List<String> chapterList = new ArrayList<>();
            List<String> data = new ArrayList<>();

            int i = 0;
            for (i = 0; i < story.size(); i++) {
                String homelink = "http://izmanga.com/chapter_id-" + story.get(i);
                Document linkget = Jsoup.connect(homelink).timeout(60 * 1000).get();
                Elements chapter_list = linkget.getElementsByClass("row");
                Elements info_topic = linkget.getElementsByClass("manga-info-text");
                Elements info_pic = linkget.getElementsByClass("manga-info-pic");
                Elements info_content = linkget.getElementsByClass("manga-info-content");
                Elements info = info_topic.get(0).getElementsByTag("li");
                source = "http://www.izmanga.com";
                title = info.get(0).getElementsByTag("h1").text();
                title = title + " - " + source.substring(11);
                author = info.get(1).text();
                author = author.substring(author.indexOf(": ") + 2).trim();
                status = info.get(2).text();
                status = status.substring(status.indexOf(": ") + 2).trim();
                description = info_content.get(0).text().trim();
                description = description.substring(description.indexOf(": ") + 2).trim();
                image = info_pic.get(0).getElementsByTag("img").get(0).attr("src");
                if (!image.startsWith("http")) {
                    image = source + image;
                }
                type = info.get(7).getElementsByTag("a").text().replace(" ", ",");

                DBCursor cursor = dao.checkNewestChap(title.trim());
                String newest_chap = "";
                Map<String, String> result = new LinkedHashMap<>();
                if (cursor != null) {
                    newest_chap = (String) cursor.next().get("newest_chap");
                }
                result = getDataIZManga(chapter_list, story, i, newest_chap);
                System.out.println(title + " - " + image + " - " + author + " - " + status + " - " + source + " - " + type);
                if (newest_chap == null || newest_chap.isEmpty()) {
                    dao.insertStory(title, author, status, source, type, image, description, result);
                } else {
                    dao.updateStory(title, author, status, source, type, image, description, result);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Map<String, String> getDataIZManga(Elements chapter_list, List<String> story, int i, String newest_chap) {
        String data = "";
        String source = "http://izmanga.com";

        Map<String, String> result = new LinkedHashMap<>();
        for (int j = 1; j < chapter_list.size(); j++) {
            String chapter = chapter_list.get(j).getElementsByTag("a").text();
            if (chapter.equalsIgnoreCase(newest_chap)) {
                break;
            }
            String href = chapter_list.get(j).getElementsByTag("a").attr("href");
            int id = Integer.parseInt(href.substring(href.indexOf(story.get(i)) + story.get(i).length() + 1));
            data = getImageFromChapterIZManga(id);
            if (!data.startsWith("http")) {
                data = source + data;
            }
            result.put(chapter, data);
        }
        
        return result;
    }

    public static String getImageFromChapterIZManga(int id) {
        try {

            String homelink = "http://izmanga.com/chapter/story_name/1159/" + id;
            Document linkget = Jsoup.connect(homelink).timeout(60 * 1000).get();

            Elements image_list = linkget.getElementsByTag("script");
            int start = image_list.get(17).data().trim().indexOf("'");
            int end = image_list.get(17).data().trim().indexOf("';");
            String data = image_list.get(17).data().trim().substring(start + 1, end);
            return data;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
