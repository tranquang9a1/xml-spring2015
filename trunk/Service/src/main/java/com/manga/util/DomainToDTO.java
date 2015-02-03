package com.manga.util;


import com.manga.domain.Chapter;
import com.manga.domain.Story;
import com.manga.dto.ChapterDTO;
import com.manga.dto.Chapters;
import com.manga.dto.Stories;
import com.manga.dto.StoryDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by QuangTV on 1/28/2015.
 */
public class DomainToDTO {

    public static StoryDTO convertToStoryDTO(Story story) {
        StoryDTO dto = new StoryDTO();

        Chapters chapters = new Chapters();
        List<ChapterDTO> listChapter = new ArrayList<ChapterDTO>();
        for(int i = 0; i < story.getChapters().size(); i++) {
            listChapter.add(convertToChaptersDTO(story.getChapters().get(i)));
        }
        chapters.setChapterDTOs(listChapter);

        dto.setAuthor(story.getAuthor());
        dto.setId(story.getId());
        dto.setListChapter(chapters);
        dto.setDescription(story.getDesciption());
        dto.setImage(story.getImage());
        dto.setNewest_chap(story.getNewest_chap());
        dto.setSource(story.getSource());
        dto.setStatus(story.getStatus());
        dto.setType(story.getType());
        dto.setUpdate_date(story.getUpdate_date());
        dto.setName(story.getName());

        return dto;
    }

    public static ChapterDTO convertToChaptersDTO(Chapter chapter) {
        ChapterDTO chapters = new ChapterDTO();
        chapters.setName(chapter.getName());
        chapters.setData(chapter.getData());
        return chapters;
    }


    public static Stories convertFromList(List<Story> storyList) {
        Stories stories = new Stories();
        for (int i = 0; i < storyList.size(); i++) {
            stories.getStoryDTO().add(convertToStoryDTO(storyList.get(i)));
        }
        return stories;
    }



}
