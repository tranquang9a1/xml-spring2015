package com.manga.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by khangtnse60992 on 1/27/2015.
 */
@XmlRootElement(name = "mangas")
public class Mangas {
    private List<Manga> manga;

    public List<Manga> getManga() {
        return manga;
    }

    @XmlElement(required = true)
    public void setManga(List<Manga> manga) {
        this.manga = manga;
    }
}
