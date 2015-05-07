package com.renobidz.endpoints.util.converters;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import com.renobidz.endpoints.dto.QuestionDTO;
import com.renobidz.store.entity.Question;
import com.renobidz.store.entity.User;

/**
 * Created by lmgagne on 15-01-15.
 */
public class QuestionConverter {
    private static QuestionConverter singleton = null;

    private QuestionConverter() {

    }

    /**
     * @return
     */
    public static QuestionConverter getInstance() {
        if (singleton == null) {
            singleton = new QuestionConverter();
        }
        return singleton;
    }

    /**
     * @param dto
     * @return
     *
     * To objectify entity
     */
    public Question toEntity(QuestionDTO dto){
        Question entity = new Question();

        entity.setUserName(dto.getUserName());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setCreationDate(dto.getCreationDate());
        entity.setNbViewed(dto.getNbViewed());
        entity.setNbLikes(dto.getNbLikes());
        entity.setNbComments(dto.getNbComments());
        entity.setUser(Ref.create(Key.create(User.class, dto.getUserId())));
        return entity;
    }

    public List<Question> toEntityList(List<QuestionDTO> dtos){
        List<Question> listDTOs = new ArrayList<Question>(0);
        for(QuestionDTO dto : dtos){
            listDTOs.add(toEntity(dto));
        }
        return listDTOs;
    }

    /**
     * @param dto
     * @return
     *
     * To dto from entity
     */
    public QuestionDTO toDTO(Question entity){
        QuestionDTO dto = new QuestionDTO();
        dto.setId(entity.getId());
        dto.setUserName(entity.getUserName());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setCreationDate(entity.getCreationDate());
        dto.setNbViewed(entity.getNbViewed());
        dto.setNbLikes(entity.getNbLikes());
        dto.setNbComments(entity.getNbComments());
        return dto;
    }

    public List<QuestionDTO> toDTOsList(List<Question> list){
        List<QuestionDTO> listDTOs = new ArrayList<QuestionDTO>(0);
        for(Question entity : list){
            listDTOs.add(toDTO(entity));
        }
        return listDTOs;
    }
}
