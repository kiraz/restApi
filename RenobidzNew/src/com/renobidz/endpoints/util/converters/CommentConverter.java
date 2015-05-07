package com.renobidz.endpoints.util.converters;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import com.renobidz.endpoints.dto.CommentDTO;
import com.renobidz.store.entity.Comment;
import com.renobidz.store.entity.Question;

public class CommentConverter {
    private static CommentConverter singleton = null;

    private CommentConverter() {

    }

    /**
     * @return
     */
    public static CommentConverter getInstance() {
        if (singleton == null) {
            singleton = new CommentConverter();
        }
        return singleton;
    }

    /**
     * @param dto
     * @return
     *
     * To objectify entity
     */
    public Comment toEntity(CommentDTO dto){
        Comment entity = new Comment();
        entity.setComment(dto.getComment());
        entity.setCreationDate(dto.getCreationDate());
        entity.setNbLikes(dto.getNbLikes());
        entity.setQuestion(Ref.create(Key.create(Question.class, dto.getQuestionId())));
        return entity;
    }

    public List<Comment> toEntityList(List<CommentDTO> dtos){
        List<Comment> listDTOs = new ArrayList<Comment>(0);
        for(CommentDTO dto : dtos){
            listDTOs.add(toEntity(dto));
        }
        return listDTOs;
    }

    /**
     * @param entity
     * @return
     *
     * To dto from entity
     */
    public CommentDTO toDTO(Comment entity){
        CommentDTO dto = new CommentDTO();
        dto.setId(entity.getId());
        dto.setComment(entity.getComment());
        dto.setCreationDate(entity.getCreationDate());
        dto.setNbLikes(entity.getNbLikes());
        return dto;
    }

    public List<CommentDTO> toDTOsList(List<Comment> list){
        List<CommentDTO> listDTOs = new ArrayList<CommentDTO>(0);
        for(Comment entity : list){
            listDTOs.add(toDTO(entity));
        }
        return listDTOs;
    }
}
