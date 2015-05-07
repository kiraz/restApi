package com.renobidz.store.entity;

import java.io.Serializable;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Ignore;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;

/**
 * Created by lmgagne on 15-01-19.
 */

@Entity(name = "RB_PRODUCT")
public class Product implements Serializable{
    private static final long serialVersionUID = 2490545482212744045L;

    @Id
    private Long id;
    private String name;
    private String description;
    private String manufacturer;
    private Float height;
    private Float weight;
    private Float price;
    private Float shipping;
    //link to supplier or POS?
    @Load
    @Index
    private Ref<Supplier> supplier;
    @Ignore
    private Long supplierId;	// Added for API purpose only hence marked as ignored

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Float getHeight() {
        return height;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getShipping() {
        return shipping;
    }

    public void setShipping(Float shipping) {
        this.shipping = shipping;
    }

    public Ref<Supplier> getSupplier() {
        return supplier;
    }

    public void setSupplier(Ref<Supplier> supplier) {
        this.supplier = supplier;
    }
}
