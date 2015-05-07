package com.renobidz.endpoints.dto;

import java.util.Date;

/**
 * Created by lmgagne on 15-01-05.
 */
public class QuestionDTO {
    private Long id;
    private String title;
    private String description;
    private Date creationDate;
    private Integer nbViewed;
    private Integer nbLikes;
    private Integer nbComments;
    private String userName;
    private Long userId;

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

    public Integer getNbComments() {
        return nbComments;
    }

    public void setNbComments(Integer nbComments) {
        this.nbComments = nbComments;
    }

    public void setNbLikes(Integer nbLikes) {
        this.nbLikes = nbLikes;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
