package com.renobidz.store.entity;

import java.io.Serializable;
import java.util.Date;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.renobidz.store.entity.util.STATUS;

/**
 * Created by lmgagne on 15-01-01.
 */

@Entity(name = "RB_PROJECT")
public class Project implements Serializable {
    private static final long serialVersionUID = -5965584110240252105L;

    @Id
    private Long id;
    @Index
    private Ref<User> user;
    private Ref<IdeaBook> ideaBook;
    private Ref<Category> category;

    private String title;
    private String description;
    private STATUS status;

    //private blob pictures;

    private float price;
    private float quality;
    private float responsiveness;
    private float punctuality;
    private float professionalism;
    private float overall;

    private Date createdDate;
    private Date startDate;
    private Date endDate;

    //payment schedule?
}