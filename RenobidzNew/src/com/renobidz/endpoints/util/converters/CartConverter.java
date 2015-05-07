package com.renobidz.endpoints.util.converters;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import com.renobidz.endpoints.dto.CartDTO;
import com.renobidz.store.entity.Cart;
import com.renobidz.store.entity.User;

public class CartConverter {
    private static CartConverter singleton = null;

    private CartConverter() {

    }

    /**
     * @return
     */
    public static CartConverter getInstance() {
        if (singleton == null) {
            singleton = new CartConverter();
        }
        return singleton;
    }

    /**
     * @param dto
     * @return
     *
     * To objectify entity
     */
    public Cart toEntity(CartDTO dto){
        Cart entity = new Cart();
        entity.setUser(Ref.create(Key.create(User.class, dto.getUserId())));
        //entity.setProduct(Ref.create(Key.create(Product.class, dto.getProductId())));
        return entity;
    }

    public List<Cart> toEntityList(List<CartDTO> dtos){
        List<Cart> listDTOs = new ArrayList<Cart>(0);
        for(CartDTO dto : dtos){
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
    public CartDTO toDTO(Cart entity){
        CartDTO dto = new CartDTO();
        dto.setId(entity.getId());
        return dto;
    }

    public List<CartDTO> toDTOsList(List<Cart> list){
        List<CartDTO> listDTOs = new ArrayList<CartDTO>(0);
        for(Cart entity : list){
            listDTOs.add(toDTO(entity));
        }
        return listDTOs;
    }
}
