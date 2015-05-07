package com.renobidz.endpoints.util.converters;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import com.renobidz.endpoints.dto.ProductDTO;
import com.renobidz.store.entity.Product;
import com.renobidz.store.entity.Supplier;

public class ProductConverter {
    private static ProductConverter singleton = null;

    private ProductConverter() {

    }

    /**
     * @return
     */
    public static ProductConverter getInstance() {
        if (singleton == null) {
            singleton = new ProductConverter();
        }
        return singleton;
    }

    /**
     * @param dto
     * @return
     *
     * To objectify entity
     */
    public Product toEntity(ProductDTO dto){
        Product entity = new Product();
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setManufacturer(dto.getManufacturer());
        entity.setHeight(dto.getHeight());
        entity.setWeight(dto.getWeight());
        entity.setShipping(dto.getShipping());
        entity.setPrice(dto.getPrice());
        entity.setSupplier(Ref.create(Key.create(Supplier.class, dto.getSupplierId())));
        return entity;
    }

    public List<Product> toEntityList(List<ProductDTO> dtos){
        List<Product> listDTOs = new ArrayList<Product>(0);
        for(ProductDTO dto : dtos){
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
    public ProductDTO toDTO(Product entity){
        ProductDTO dto = new ProductDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setManufacturer(entity.getManufacturer());
        dto.setHeight(entity.getHeight());
        dto.setWeight(entity.getWeight());
        dto.setShipping(entity.getShipping());
        dto.setPrice(entity.getPrice());
        return dto;
    }

    public List<ProductDTO> toDTOsList(List<Product> list){
        List<ProductDTO> listDTOs = new ArrayList<ProductDTO>(0);
        for(Product entity : list){
            listDTOs.add(toDTO(entity));
        }
        return listDTOs;
    }
}
