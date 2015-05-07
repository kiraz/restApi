package com.renobidz.endpoints.util.converters;

import java.util.ArrayList;
import java.util.List;

import com.renobidz.endpoints.dto.CategoryDTO;
import com.renobidz.store.entity.Category;

public class CategoryConverter {
    private static CategoryConverter singleton = null;

    private CategoryConverter() {

    }

    /**
     * @return
     */
    public static CategoryConverter getInstance() {
        if (singleton == null) {
            singleton = new CategoryConverter();
        }
        return singleton;
    }

    /**
     * @param dto
     * @return
     *
     * To objectify entity
     */
    public Category toEntity(CategoryDTO dto){
        Category entity = new Category();
        entity.setCategory(dto.getCategory());
        entity.setLanguage(dto.getLanguage());
        return entity;
    }

    public List<Category> toEntityList(List<CategoryDTO> dtos){
        List<Category> listDTOs = new ArrayList<Category>(0);
        for(CategoryDTO dto : dtos){
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
    public CategoryDTO toDTO(Category entity){
        CategoryDTO dto = new CategoryDTO();
        dto.setId(entity.getId());
        dto.setCategory(entity.getCategory());
        dto.setLanguage(entity.getLanguage());
        return dto;
    }

    public List<CategoryDTO> toDTOsList(List<Category> list){
        List<CategoryDTO> listDTOs = new ArrayList<CategoryDTO>(0);
        for(Category entity : list){
            listDTOs.add(toDTO(entity));
        }
        return listDTOs;
    }
}
