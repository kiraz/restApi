package com.renobidz.store.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;

/**
 * @author Ankur
 * 
 */
@Entity(name = "RB_PORTFOLIO")
public class Portfolio implements Serializable {
	private static final long serialVersionUID = 6254194866821474850L;
	@Id
	private Long id;
	private String title;
	private String description;
	private List<PortfolioImage> portfolioImages = new ArrayList<PortfolioImage>(0);
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

	public List<PortfolioImage> getPortfolioImages() {
		return portfolioImages;
	}

	public void setPortfolioImages(List<PortfolioImage> portfolioImages) {
		this.portfolioImages = portfolioImages;
	}

	public Ref<User> getUser() {
		return user;
	}

	public void setUser(Ref<User> user) {
		this.user = user;
	}
}
