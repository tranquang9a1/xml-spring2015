package com.manga.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by khangtnse60992 on 1/24/2015.
 */
@XmlRootElement(name = "chapter")
public class Chapter {

    private String name;

    private String images;

    public String getName() {
        return name;
    }

    @XmlElement(required = true)
    public void setName(String name) {
        this.name = name;
    }

    public String getImages() {
        return images;
    }

    @XmlElement(required = true)
    public void setImages(String images) {
        this.images = images;
    }
}
