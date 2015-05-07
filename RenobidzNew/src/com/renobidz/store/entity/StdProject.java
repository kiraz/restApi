package com.renobidz.store.entity;

import java.io.Serializable;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;
import com.renobidz.store.entity.util.LANGUAGE;


/**
 * Created by lmgagne on 15-01-19.
 */
@Entity(name = "RB_STDPROJECT")
public class StdProject implements Serializable{
    private static final long serialVersionUID = 3650619283550326372L;

    @Id
    private Long id;
    private String project;
    private LANGUAGE language;
    @Load
    @Index
    private Ref<Category> category;

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

    public Ref<Category> getCategory() {
        return category;
    }

    public void setCategory(Ref<Category> category) {
        this.category = category;
    }
}
