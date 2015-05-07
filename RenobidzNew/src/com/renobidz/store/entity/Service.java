package com.renobidz.store.entity;

import java.io.Serializable;
import java.util.Date;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Ignore;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;
import com.renobidz.store.entity.util.BILLINGPLAN;

/**
 * Created by lmgagne on 15-01-19.
 */
@Entity(name = "RB_SERVICE")
public class Service implements Serializable{
    private static final long serialVersionUID = -7029466756305315997L;

    @Id
    private Long id;
    private String name;
    private String description;
    private Float price;
    private BILLINGPLAN billingPlan;
    private Boolean isActive;
    private Date activationDate; //Note your billing cycle is a period from the date your Membership begins and not the first of the month (e.g. Jan 1 – Jan 31).
    private Date deactivationDate;
    @Load
    @Index
    private Ref<User> user;
    @Ignore
    private Long userId;

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

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public BILLINGPLAN getBillingPlan() {
        return billingPlan;
    }

    public void setBillingPlan(BILLINGPLAN billingPlan) {
        this.billingPlan = billingPlan;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Date getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(Date activationDate) {
        this.activationDate = activationDate;
    }

    public Date getDeactivationDate() {
        return deactivationDate;
    }

    public void setDeactivationDate(Date deactivationDate) {
        this.deactivationDate = deactivationDate;
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
}
