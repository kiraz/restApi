package com.renobidz.store.entity;

import java.io.Serializable;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;

/**
 * Created by lmgagne on 15-01-01.
 */

@Entity(name = "RB_IDEABOOK")
public class IdeaBook implements Serializable{
    private static final long serialVersionUID = 1033634319382482163L;

    @Id
    private Long id;
    private String title;
    private String description;
    //private blobstore photo;
    @Load
    @Index
    private Ref<User> user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Ref<User> getUser() {
        return user;
    }

    public void setUser(Ref<User> user) {
        this.user = user;
    }
}
