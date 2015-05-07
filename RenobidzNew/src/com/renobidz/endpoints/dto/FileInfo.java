package com.renobidz.endpoints.dto;

public class FileInfo {
	private String fileName;
	private String imageUrl;
	private String blobkey;
	private String contentType;
	private String size;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getBlobkey() {
		return blobkey;
	}

	public void setBlobkey(String blobkey) {
		this.blobkey = blobkey;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}
}
