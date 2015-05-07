package com.renobidz.endpoints.dto;

import java.util.ArrayList;
import java.util.List;

import com.renobidz.store.entity.PortfolioImage;

public class PortfolioDTO {
	private Long id;
	private String title;
	private String description;
	private List<PortfolioImage> portfolioImages = new ArrayList<PortfolioImage>(0);
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

	public List<PortfolioImage> getPortfolioImages() {
		return portfolioImages;
	}

	public void setPortfolioImages(List<PortfolioImage> portfolioImages) {
		this.portfolioImages = portfolioImages;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
