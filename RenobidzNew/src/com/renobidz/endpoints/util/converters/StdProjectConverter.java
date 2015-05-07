package com.renobidz.endpoints.util.converters;

import java.util.ArrayList;
import java.util.List;

import com.renobidz.endpoints.dto.StdProjectDTO;
import com.renobidz.store.entity.StdProject;

/**
 * Created by lmgagne on 15-01-26.
 */
public class StdProjectConverter {
    private static StdProjectConverter singleton = null;

    private StdProjectConverter() {

    }

    /**
     * @return
     */
    public static StdProjectConverter getInstance() {
        if (singleton == null) {
            singleton = new StdProjectConverter();
        }
        return singleton;
    }

    /**
     * @param dto
     * @return
     *
     * To objectify entity
     */
    public StdProject toEntity(StdProjectDTO dto){
        StdProject entity = new StdProject();
        entity.setProject(dto.getProject());
        entity.setLanguage(dto.getLanguage());
        //entity.setCategory(Ref.create(Key.create(Category.class, dto.getCategoryId())));
        return entity;
    }

    public List<StdProject> toEntityList(List<StdProjectDTO> dtos){
        List<StdProject> listDTOs = new ArrayList<StdProject>(0);
        for(StdProjectDTO dto : dtos){
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
    public StdProjectDTO toDTO(StdProject entity){
        StdProjectDTO dto = new StdProjectDTO();
        dto.setId(entity.getId());
        dto.setProject(entity.getProject());
        dto.setLanguage(entity.getLanguage());
        return dto;
    }

    public List<StdProjectDTO> toDTOsList(List<StdProject> list){
        List<StdProjectDTO> listDTOs = new ArrayList<StdProjectDTO>(0);
        for(StdProject entity : list){
            listDTOs.add(toDTO(entity));
        }
        return listDTOs;
    }

}
