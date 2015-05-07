package com.renobidz.store.entity;

import java.io.Serializable;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Ignore;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;

/**
 * @author Ankur
 * 
 */
@Entity(name = "RB_COMPANY")
public class Company implements Serializable {
	private static final long serialVersionUID = -6789653325846645877L;
	@Id
	private Long id;
	private String name;
	private String servicedCategory;
	private String website; // optional
	private String licenseNumber;
	private String description;
	private String servicesProvided;
	private String areasServed;
	private String certificationsAndAwards;
	private String affiliations;
	private String typicalJobCostCurrency;
	private String typicalJobCostFrom;
	private String typicalJobCostTo;

	@Load
	@Index
	private Ref<User> user;
	@Ignore
	private Long userId; // Added for API purpose only hence marked as ignored

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

	public String getServicedCategory() {
		return servicedCategory;
	}

	public void setServicedCategory(String servicedCategory) {
		this.servicedCategory = servicedCategory;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getLicenseNumber() {
		return licenseNumber;
	}

	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getServicesProvided() {
		return servicesProvided;
	}

	public void setServicesProvided(String servicesProvided) {
		this.servicesProvided = servicesProvided;
	}

	public String getAreasServed() {
		return areasServed;
	}

	public void setAreasServed(String areasServed) {
		this.areasServed = areasServed;
	}

	public String getCertificationsAndAwards() {
		return certificationsAndAwards;
	}

	public void setCertificationsAndAwards(String certificationsAndAwards) {
		this.certificationsAndAwards = certificationsAndAwards;
	}

	public String getAffiliations() {
		return affiliations;
	}

	public void setAffiliations(String affiliations) {
		this.affiliations = affiliations;
	}

	public String getTypicalJobCostCurrency() {
		return typicalJobCostCurrency;
	}

	public void setTypicalJobCostCurrency(String typicalJobCostCurrency) {
		this.typicalJobCostCurrency = typicalJobCostCurrency;
	}

	public String getTypicalJobCostFrom() {
		return typicalJobCostFrom;
	}

	public void setTypicalJobCostFrom(String typicalJobCostFrom) {
		this.typicalJobCostFrom = typicalJobCostFrom;
	}

	public String getTypicalJobCostTo() {
		return typicalJobCostTo;
	}

	public void setTypicalJobCostTo(String typicalJobCostTo) {
		this.typicalJobCostTo = typicalJobCostTo;
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
