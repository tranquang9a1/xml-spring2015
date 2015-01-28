package com.manga.domain;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import java.util.List;

/**
 * Created by khangtnse60992 on 1/24/2015.
 */
@Document(collection = "manga")
@XmlRootElement(name = "manga")
public class Manga {

    @Id
    private String id;
    private String name;
    private String author;
    private String status;
    private String source;
    private Long update_date;
    private String newest_chap;
    private List<String> type;
    private List<Chapter> chapters;


    public String getId() {
        return id;
    }

    @XmlElement(required = true)
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @XmlElement(required = true)
    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    @XmlElement(required = true)
    public void setAuthor(String author) {
        this.author = author;
    }

    public String getStatus() {
        return status;
    }

    @XmlElement(required = true)
    public void setStatus(String status) {
        this.status = status;
    }

    public String getSource() {
        return source;
    }

    @XmlElement(required = true)
    public void setSource(String source) {
        this.source = source;
    }


    public List<String> getType() {
        return type;
    }
    @XmlElement(required = true)
    public void setType(List<String> type) {
        this.type = type;
    }

    public List<Chapter> getChapters() {
        return chapters;
    }


    @XmlElement(required = true)
    public void setChapters(List<Chapter> chapters) {
        this.chapters = chapters;
    }

    public Long getUpdate_date() {
        return update_date;
    }

    @XmlElement(required = true)
    public void setUpdate_date(Long update_date) {
        this.update_date = update_date;
    }

    public String getNewest_chap() {
        return newest_chap;
    }

    @XmlElement(required = true)
    public void setNewest_chap(String newest_chap) {
        this.newest_chap = newest_chap;
    }
}
