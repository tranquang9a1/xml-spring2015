package com.manga.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by khangtnse60992 on 1/24/2015.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "chapter", propOrder = {
        "name",
        "data"
})

public class ChapterDTO {

    private String name;

    private String data;

    public String getName() {
        return name;
    }

    //@XmlElement(required = true)
    public void setName(String name) {
        this.name = name;
    }

    public String getData() {
        return data;
    }

    //@XmlElement(required = true)
    public void setData(String data) {
        this.data = data;
    }
}
