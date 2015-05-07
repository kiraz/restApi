package com.renobidz.store.entity;

import java.io.Serializable;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.renobidz.store.entity.util.LANGUAGE;

/**
 * Created by lmgagne on 15-01-01.
 */

@Entity(name = "RB_CATEGORY")
public class Category implements Serializable  {
    private static final long serialVersionUID = -6014218937725998793L;

    @Id
    private Long id;
    @Index
    private String category;
    private LANGUAGE language;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LANGUAGE getLanguage() {
        return language;
    }

    public void setLanguage(LANGUAGE language) {
        this.language = language;
    }
}
