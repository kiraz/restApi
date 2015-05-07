package com.renobidz.store.entity;

import java.io.Serializable;
import java.util.Date;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Ignore;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;

/**
 * Created by lmgagne on 14-12-28.
 */
@Entity(name = "RB_QUESTION")
public class Question implements Serializable {
    private static final long serialVersionUID = 3052731002511247608L;
    @Id
    private Long id;
    @Index
    private String title;
    private String description;
    private Date creationDate;
    private Integer nbViewed;
    private Integer nbLikes;
    private Integer nbComments;
    private String userName;
    @Load
    private Ref<User> user;
    @Ignore
    private Long userId;	// Added for API purpose only hence marked as ignored
    //private Ref<Comment> comment;

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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getNbViewed() {
        return nbViewed;
    }

    public void setNbViewed(Integer nbViewed) {
        this.nbViewed = nbViewed;
    }

    public Integer getNbLikes() {
        return nbLikes;
    }

    public void setNbLikes(Integer nbLikes) {
        this.nbLikes = nbLikes;
    }

    public Integer getNbComments() {
        return nbComments;
    }

    public void setNbComments(Integer nbComments) {
        this.nbComments = nbComments;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Ref<User> getUser() {
        return user;
    }

    public void setUser(Ref<User> user) {
        this.user = user;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /*public Ref<Comment> getComment() {
        return comment;
    }

    public void setComment(Ref<Comment> comment) {
        this.comment = comment;
    }*/
}
