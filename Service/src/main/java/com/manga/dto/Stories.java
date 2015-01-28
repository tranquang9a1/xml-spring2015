package com.manga.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by khangtnse60992 on 1/27/2015.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "story"
})


@XmlRootElement(name = "stories")
public class Stories {
    private List<StoryDTO> story;

    public List<StoryDTO> getStoryDTO() {
        if(story==null) {
            story = new ArrayList<StoryDTO>();
        }
        return story;
    }

    //@XmlElement(required = true)
    public void setStoryDTO(List<StoryDTO> storyDTO) {
        this.story = story;
    }
}
