package com.renobidz.endpoints.util.converters;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import com.renobidz.endpoints.dto.SupplierDTO;
import com.renobidz.store.entity.Supplier;
import com.renobidz.store.entity.User;

/**
 * Created by lmgagne on 15-01-19.
 */
public class SupplierConverter {
    private static SupplierConverter singleton = null;

    private SupplierConverter() {

    }

    /**
     * @return
     */
    public static SupplierConverter getInstance() {
        if (singleton == null) {
            singleton = new SupplierConverter();
        }
        return singleton;
    }

    /**
     * @param dto
     * @return
     *
     * To objectify entity
     */
    public Supplier toEntity(SupplierDTO dto){
        Supplier entity = new Supplier();
        entity.setName(dto.getName());
        entity.setUser(Ref.create(Key.create(User.class, dto.getUserId())));
        return entity;
    }

    public List<Supplier> toEntityList(List<SupplierDTO> dtos){
        List<Supplier> listDTOs = new ArrayList<Supplier>(0);
        for(SupplierDTO dto : dtos){
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
    public SupplierDTO toDTO(Supplier entity){
        SupplierDTO dto = new SupplierDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }

    public List<SupplierDTO> toDTOsList(List<Supplier> list){
        List<SupplierDTO> listDTOs = new ArrayList<SupplierDTO>(0);
        for(Supplier entity : list){
            listDTOs.add(toDTO(entity));
        }
        return listDTOs;
    }
}
