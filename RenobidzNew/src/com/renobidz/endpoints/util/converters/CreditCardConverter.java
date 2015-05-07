package com.renobidz.endpoints.util.converters;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import com.renobidz.endpoints.dto.CreditCardDTO;
import com.renobidz.store.entity.CreditCard;
import com.renobidz.store.entity.User;

/**
 * Created by lmgagne on 15-01-19.
 */
public class CreditCardConverter {
    private static CreditCardConverter singleton = null;

    private CreditCardConverter() {

    }

    /**
    * @return
    */
    public static CreditCardConverter getInstance() {
        if (singleton == null) {
            singleton = new CreditCardConverter();
        }
        return singleton;
    }

    /**
    * @param dto
    * @return
    *
    * To objectify entity
    */
    public CreditCard toEntity(CreditCardDTO dto){
        CreditCard entity = new CreditCard();
        entity.setCardType(dto.getCardType());
        entity.setCardHolderName(dto.getCardHolderName());
        entity.setCardNumber(dto.getCardNumber());
        entity.setExpMonth(dto.getExpMonth());
        entity.setExpYear(dto.getExpYear());
        entity.setIsValidated(dto.getIsValidated());
        entity.setAvsNumber(dto.getAvsNumber());
        entity.setAvsStreet(dto.getAvsStreet());
        entity.setAvsZipCode(dto.getAvsZipCode());
        entity.setCvdCode(dto.getCvdCode());
        entity.setCvdIndicator(dto.getCvdIndicator());
        entity.setIsDefault(dto.getIsDefault());
        entity.setUser(Ref.create(Key.create(User.class, dto.getUserId())));
        return entity;
    }

    public List<CreditCard> toEntityList(List<CreditCardDTO> dtos){
        List<CreditCard> listDTOs = new ArrayList<CreditCard>(0);
        for(CreditCardDTO dto : dtos){
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
    public CreditCardDTO toDTO(CreditCard entity){
        CreditCardDTO dto = new CreditCardDTO();
        dto.setCardType(entity.getCardType());
        dto.setId(entity.getId());
        dto.setCardHolderName(entity.getCardHolderName());
        dto.setCardNumber(entity.getCardNumber());
        dto.setExpMonth(entity.getExpMonth());
        dto.setExpYear(entity.getExpYear());
        dto.setIsValidated(entity.getIsValidated());
        dto.setAvsNumber(entity.getAvsNumber());
        dto.setAvsStreet(entity.getAvsStreet());
        dto.setAvsZipCode(entity.getAvsZipCode());
        dto.setCvdCode(entity.getCvdCode());
        dto.setCvdIndicator(entity.getCvdIndicator());
        dto.setIsDefault(entity.getIsDefault());
        return dto;
    }

    public List<CreditCardDTO> toDTOsList(List<CreditCard> list){
        List<CreditCardDTO> listDTOs = new ArrayList<CreditCardDTO>(0);
        for(CreditCard entity : list){
            listDTOs.add(toDTO(entity));
        }
        return listDTOs;
    }
}
