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
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 *
 * @author QuangTV
 */
public class ParserFromKissManga {
    public static void getStoriesKissManga() {
        try {
            for (int i = 1;; i++) {
                String homelink = "http://kissmanga.com/MangaList/LatestUpdate?page=" + i;
                Document linkget = Jsoup.connect(homelink).timeout(60 * 1000).get();

                Elements stories = linkget.getElementsByClass("listing");
                if (stories != null) {
                    int j = 0;
                    List<String> story = new ArrayList<String>();
                    Elements stories_list = stories.first().getElementsByTag("a");
                    String href = "";
                    for (j = 0; j < stories_list.size(); j++) {
                        if (j % 2 == 0) {
                            href = stories_list.get(j).attr("href");
                            getChapterByStoriesKissManga(href);
                        }
                    }

                    //System.out.println(href);
                } else {
                    break;
                }

            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void getChapterByStoriesKissManga(String href) {

        try {
            String title = "";
            String author = "";
            String status = "";
            String source = "http://www.kissmanga.com";
            String type = "";
            String image = "";
            String chapter_name = "";
            String description = "";

            String homelink = "http://kissmanga.com" + href;
            Document linkget = Jsoup.connect(homelink).timeout(60 * 1000).get();

            Elements info = linkget.getElementsByClass("barContent").first().getElementsByTag("p");
            if (info != null) {
                title = linkget.getElementsByClass("bigChar").text();
                image = linkget.getElementsByClass("barContent").get(3).getElementsByTag("img").attr("src");
                if (info.get(0).getElementsByTag("span").text().equalsIgnoreCase("Other name:")) {
                    type = info.get(1).getElementsByTag("a").text().replace(" ", ",");
                    author = info.get(2).getElementsByTag("a").text();
                    status = info.get(3).text();
                    description = info.get(5).text();
                } else {
                    type = info.get(0).getElementsByTag("a").text().replace(" ", ",");
                    author = info.get(1).getElementsByTag("a").text();
                    status = info.get(2).text();
                    description = info.get(4).text();
                }
                title = title + " - " + source.substring(11);
                status = status.substring(0, status.indexOf("Views")).trim();
                int start = status.indexOf("Status:");
                status = status.substring(start + 8).trim();

                System.out.println(title + " - " + image + " - " + type + " - " + author + " - " + status + " - " + description);
                Elements chapter_list = linkget.getElementsByClass("listing");
                Elements chapter = chapter_list.first().getElementsByTag("a");

                DBCursor cursor = dao.checkNewestChap(title.trim());
                String newest_chap = "";
                Map<String, String> result = new LinkedHashMap<>();
                if (cursor != null) {
                    newest_chap = (String) cursor.next().get("newest_chap");
                }
                result = getDataKissManga(chapter, newest_chap);
                //System.out.println(title + " - " + image + " - " + author + " - " + status + " - " + source + " - " + type);
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

    public static Map<String, String> getDataKissManga(Elements chapter, String newest_chap) {
        Map<String, String> result = new LinkedHashMap<>();
        String chapter_name = "";
        String link = "";
        String data = "";
        String source = "http://kissmanga.com";
        for (int i = 0; i < chapter.size(); i++) {
            chapter_name = chapter.get(i).text();
            if (chapter_name.equalsIgnoreCase(newest_chap)) {
                break;
            }
            link = chapter.get(i).attr("href");
            data = getImageFromChapterKissManga(link);
            if (data != null && !data.startsWith("http")) {
                data = source + data;
            }
            result.put(chapter_name, data);

        }

        return result;
    }

    public static String getImageFromChapterKissManga(String href) {
        try {

            String homelink = "http://kissmanga.com" + href;
            Document doc = Jsoup.connect(homelink).timeout(60 * 1000).get();

            Elements images = doc.getElementsByTag("script");
            int start = images.get(6).data().trim().indexOf("(\"");
            int end = images.get(6).data().trim().indexOf("var lstImagesLoaded");
            String list = images.get(6).data().substring(start, end).replace("lstImages.push(\"", "\u0020").replace("\");", "|");
            String[] image = list.split("\\|");
            String data = "";
            for (String img : image) {

                data += img.trim() + "|";

            }
            return data;

        } catch (Exception e) {
            return null;
        }
    }
}
