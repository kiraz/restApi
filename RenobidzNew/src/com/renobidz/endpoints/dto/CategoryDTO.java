package com.renobidz.endpoints.dto;

import com.renobidz.store.entity.util.LANGUAGE;

/**
 * Created by lmgagne on 15-01-20.
 */
public class CategoryDTO {
    private Long id;
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
