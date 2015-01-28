/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crawler.dao;

import com.crawler.entity.Story;
import com.crawler.xml.DomConvert;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

/**
 *
 * @author QuangTV
 */
public class StoryDAO {

    public DBCollection getCollection(String database, String collection) {
        try {
            MongoClient mongo = new MongoClient("localhost", 27017);
            DB db = mongo.getDB(database);
            DBCollection table = db.getCollection(collection);
            return table;
        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
    }

    public boolean insertStory(String title, String author, String status,
            String source, String type, String image,
            String description, Map<String, String> result) {
        DBCollection collection = null;
        try {
            collection = getCollection("xml", "story");
            
            // Create object story to insert DB
            
            BasicDBObject story = new BasicDBObject();
            story.put("name", title);
            story.put("author", author);
            story.put("status", status);
            story.put("source", source);
            BasicDBList typeList = new BasicDBList();
            for (String genre : type.split(",")) {
                typeList.add(genre);
            }
            story.put("type", typeList);
            story.put("image", image);
            story.put("description", description);
            BasicDBList listchapter = new BasicDBList();
            String newest_chap = "";
            for (Map.Entry<String, String> entry : result.entrySet()) {
                BasicDBObject chapter = new BasicDBObject();
                chapter.put("name", entry.getKey());
                chapter.put("data", entry.getValue());
                listchapter.add(chapter);
                newest_chap = entry.getKey();
                
            }
            story.put("chapters", listchapter);
            story.put("newest_chap", newest_chap);
            story.put("update_date", System.currentTimeMillis()/1000);
            // End create object Story
            // Start insert DB
            // Create XML file 
            DomConvert domConvert = new DomConvert();
            domConvert.convertXML(DomConvert.ObjectToStoryType(title, author, status, source, type, image, description, result));
            collection.insert(story);
            
            // End insert DB
            
            System.out.println("Insert successful " + title + " with " + result.size() + " chapters!");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean updateStory(String title, String author, String status,
            String source, String type, String image,
            String description, Map<String, String> result) {
        DBCollection collection = null;
        try {
            collection = getCollection("xml", "story");
            BasicDBObject story = new BasicDBObject();
            story.put("name", title);
            
            BasicDBObject updateStory = new BasicDBObject();
            
            updateStory.put("author", author);
            updateStory.put("status", status);
            updateStory.put("image", image);
            BasicDBList listchapter = new BasicDBList();
            String newest_chap ="";
            for (Map.Entry<String, String> entry : result.entrySet()) {
                BasicDBObject chapter = new BasicDBObject();
                chapter.put("name", entry.getKey());
                chapter.put("data", entry.getValue());
                DBObject chapterUpdate = new BasicDBObject("chapters", chapter);
                DBObject updateChapter = new BasicDBObject("$push", chapterUpdate);
                collection.update(story, updateChapter);
                newest_chap = entry.getKey();
            }
            
            updateStory.put("newest_chap", newest_chap);
            updateStory.put("update_date", System.currentTimeMillis());
            
            
            DBObject updateInfo = new BasicDBObject("$set", updateStory);
            collection.update(story, updateInfo);
            
            System.out.println("Update successful " + title + " with " + result.size() + " chapters!");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public DBCursor findStoryByName(String name) {
        try {

            DBCollection collection = getCollection("xml", "story");
            BasicDBObject query = new BasicDBObject();
            query.put("name", name);
            DBCursor cursor = collection.find(query);
            if (cursor.hasNext()) {
                return cursor;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public DBCursor checkNewestChap(String name) {
        try {

            DBCollection collection = getCollection("xml", "story");
            BasicDBObject query = new BasicDBObject();
            query.put("name", name);
            DBCursor cursor = collection.find(query);
            if (cursor.hasNext()) {
                return cursor;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
