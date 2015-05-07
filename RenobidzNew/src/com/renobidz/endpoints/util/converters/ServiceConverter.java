package com.renobidz.endpoints.util.converters;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import com.renobidz.endpoints.dto.ServiceDTO;
import com.renobidz.store.entity.Service;
import com.renobidz.store.entity.User;

/**
 * Created by lmgagne on 15-01-19.
 */
public class ServiceConverter {
    private static ServiceConverter singleton = null;

    private ServiceConverter() {

    }

    /**
     * @return
     */
    public static ServiceConverter getInstance() {
        if (singleton == null) {
            singleton = new ServiceConverter();
        }
        return singleton;
    }

    /**
     * @param dto
     * @return
     *
     * To objectify entity
     */
    public Service toEntity(ServiceDTO dto){
        Service entity = new Service();
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setBillingPlan(dto.getBillingPlan());
        entity.setIsActive(dto.getIsActive());
        entity.setActivationDate(dto.getActivationDate());
        entity.setDeactivationDate(dto.getDeactivationDate());
        entity.setUser(Ref.create(Key.create(User.class, dto.getUserId())));
        return entity;
    }

    public List<Service> toEntityList(List<ServiceDTO> dtos){
        List<Service> listDTOs = new ArrayList<Service>(0);
        for(ServiceDTO dto : dtos){
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
    public ServiceDTO toDTO(Service entity){
        ServiceDTO dto = new ServiceDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setPrice(entity.getPrice());
        dto.setBillingPlan(entity.getBillingPlan());
        dto.setIsActive(entity.getIsActive());
        dto.setActivationDate(entity.getActivationDate());
        dto.setDeactivationDate(entity.getDeactivationDate());
        return dto;
    }

    public List<ServiceDTO> toDTOsList(List<Service> list){
        List<ServiceDTO> listDTOs = new ArrayList<ServiceDTO>(0);
        for(Service entity : list){
            listDTOs.add(toDTO(entity));
        }
        return listDTOs;
    }
}
