package com.renobidz.endpoints.dto;

import com.renobidz.store.entity.util.LANGUAGE;

/**
 * Created by lmgagne on 15-01-26.
 */
public class StdProjectDTO {
    private Long id;
    private String project;
    private LANGUAGE language;
    private Long categoryId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public LANGUAGE getLanguage() {
        return language;
    }

    public void setLanguage(LANGUAGE language) {
        this.language = language;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
