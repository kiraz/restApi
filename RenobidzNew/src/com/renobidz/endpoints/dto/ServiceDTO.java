package com.renobidz.endpoints.dto;

import java.util.Date;

import com.renobidz.store.entity.util.BILLINGPLAN;

/**
 * Created by lmgagne on 15-01-19.
 */
public class ServiceDTO {
    private Long id;
    private String name;
    private String description;
    private Float price;
    private BILLINGPLAN billingPlan;
    private Boolean isActive;
    private Date activationDate;
    private Date deactivationDate;
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

    public void setBillingPlan(BILLINGPLAN billingPlan) {
        this.billingPlan = billingPlan;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
