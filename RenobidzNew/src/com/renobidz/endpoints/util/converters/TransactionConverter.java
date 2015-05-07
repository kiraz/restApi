package com.renobidz.endpoints.util.converters;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import com.renobidz.endpoints.dto.TransactionDTO;
import com.renobidz.store.entity.Service;
import com.renobidz.store.entity.Transaction;
import com.renobidz.store.entity.User;

/**
 * Created by lmgagne on 15-01-26.
 */
public class TransactionConverter {
    private static TransactionConverter singleton = null;

    private TransactionConverter() {

    }

    /**
     * @return
     */
    public static TransactionConverter getInstance() {
        if (singleton == null) {
            singleton = new TransactionConverter();
        }
        return singleton;
    }

    /**
     * @param dto
     * @return
     *
     * To objectify entity
     */
    public Transaction toEntity(TransactionDTO dto){
        Transaction entity = new Transaction();
        entity.setReceiptId(dto.getReceiptId());
        entity.setResponseCode(dto.getResponseCode());
        entity.setAuthCode(dto.getAuthCode());
        entity.setTransTime(dto.getTransTime());
        entity.setTransDate(dto.getTransDate());
        entity.setTransType(dto.getTransType());
        entity.setComplete(dto.getComplete());
        entity.setMessage(dto.getMessage());
        entity.setTransAmount(dto.getTransAmount());
        entity.setTxn_number(dto.getTxn_number());
        entity.setTimedOut(dto.getTimedOut());
        entity.setIsVisaDebit(dto.getIsVisaDebit());
        entity.setService(Ref.create(Key.create(Service.class, dto.getServiceId())));
        entity.setUser(Ref.create(Key.create(User.class, dto.getUserId())));
        return entity;
    }

    public List<Transaction> toEntityList(List<TransactionDTO> dtos){
        List<Transaction> listDTOs = new ArrayList<Transaction>(0);
        for(TransactionDTO dto : dtos){
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
    public TransactionDTO toDTO(Transaction entity){
        TransactionDTO dto = new TransactionDTO();
        dto.setId(entity.getId());
        dto.setReceiptId(entity.getReceiptId());
        dto.setResponseCode(entity.getResponseCode());
        dto.setAuthCode(entity.getAuthCode());
        dto.setTransTime(entity.getTransTime());
        dto.setTransDate(entity.getTransDate());
        dto.setTransType(entity.getTransType());
        dto.setComplete(entity.getComplete());
        dto.setMessage(entity.getMessage());
        dto.setTransAmount(entity.getTransAmount());
        dto.setTxn_number(entity.getTxn_number());
        dto.setTimedOut(entity.getTimedOut());
        dto.setIsVisaDebit(entity.getIsVisaDebit());
        return dto;
    }

    public List<TransactionDTO> toDTOsList(List<Transaction> list){
        List<TransactionDTO> listDTOs = new ArrayList<TransactionDTO>(0);
        for(Transaction entity : list){
            listDTOs.add(toDTO(entity));
        }
        return listDTOs;
    }
}
