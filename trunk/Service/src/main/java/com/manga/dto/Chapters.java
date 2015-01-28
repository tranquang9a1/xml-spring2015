package com.manga.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
 * Created by QuangTV on 1/28/2015.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "chapters", propOrder = {
        "chapter"
})

public class Chapters {
    private List<ChapterDTO> chapter;

    public List<ChapterDTO> getChapterDTOs() {
        return chapter;
    }
    //@XmlElement(required = true,name = "chapter")
    public void setChapterDTOs(List<ChapterDTO> chapter) {
        this.chapter = chapter;
    }
}
