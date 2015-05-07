package com.renobidz.endpoints.util.converters;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import com.renobidz.endpoints.dto.IdeaBookDTO;
import com.renobidz.store.entity.IdeaBook;
import com.renobidz.store.entity.User;

/**
 * Created by lmgagne on 15-01-26.
 */
public class IdeaBookConverter {
    private static IdeaBookConverter singleton = null;

    private IdeaBookConverter() {

    }

    /**
     * @return
     */
    public static IdeaBookConverter getInstance() {
        if (singleton == null) {
            singleton = new IdeaBookConverter();
        }
        return singleton;
    }

    /**
     * @param dto
     * @return
     *
     * To objectify entity
     */
    public IdeaBook toEntity(IdeaBookDTO dto){
        IdeaBook entity = new IdeaBook();
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setUser(Ref.create(Key.create(User.class, dto.getUserId())));
        return entity;
    }

    public List<IdeaBook> toEntityList(List<IdeaBookDTO> dtos){
        List<IdeaBook> listDTOs = new ArrayList<IdeaBook>(0);
        for(IdeaBookDTO dto : dtos){
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
    public IdeaBookDTO toDTO(IdeaBook entity){
        IdeaBookDTO dto = new IdeaBookDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        return dto;
    }

    public List<IdeaBookDTO> toDTOsList(List<IdeaBook> list){
        List<IdeaBookDTO> listDTOs = new ArrayList<IdeaBookDTO>(0);
        for(IdeaBook entity : list){
            listDTOs.add(toDTO(entity));
        }
        return listDTOs;
    }
}
