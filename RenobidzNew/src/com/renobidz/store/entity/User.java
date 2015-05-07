package com.renobidz.store.entity;

import java.io.Serializable;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.renobidz.store.entity.util.LANGUAGE;
import com.renobidz.store.entity.util.SOCIALWEBSITE;

/**
 * Created by lmgagne on 2014-08-19.
 * 
 * Modified by Ankur Jain on 2014-12-16
 * 
 */

@Entity(name = "RB_USER")
public class User implements Serializable{
	private static final long serialVersionUID = -4641329068685733119L;
	@Id
	private Long id;
	@Index
	private String email;
	private String password;
	private String passwordResetKey;
	private Integer incorrectPwd; // incorrectPwd are set to 0 by default while, Integer are considered as an object.

	private Boolean isLocked;
	private Boolean isSocialUser;
	private SOCIALWEBSITE socialwebsite;

    private Boolean isCompany;
    private String firstName;
	private String lastName;

	private String phoneNumber;
	private LANGUAGE prefLanguage;
	
	private String profilePicture;
	private String profilePictureBlobKey;

	private Long creationDate;
	private Long lastLogonDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

    public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordResetKey() {
		return passwordResetKey;
	}

	public void setPasswordResetKey(String passwordResetKey) {
		this.passwordResetKey = passwordResetKey;
	}

	public Integer getIncorrectPwd() {
		return incorrectPwd;
	}

	public void setIncorrectPwd(Integer incorrectPwd) {
		this.incorrectPwd = incorrectPwd;
	}

	public Boolean getIsLocked() {
		return isLocked;
	}

	public void setIsLocked(Boolean isLocked) {
		this.isLocked = isLocked;
	}

	public Boolean getIsSocialUser() {
		return isSocialUser;
	}

	public void setIsSocialUser(Boolean isSocialUser) {
		this.isSocialUser = isSocialUser;
	}

	public SOCIALWEBSITE getSocialwebsite() {
		return socialwebsite;
	}

	public void setSocialwebsite(SOCIALWEBSITE socialwebsite) {
		this.socialwebsite = socialwebsite;
	}

    public Boolean getIsCompany() {
        return isCompany;
    }

    public void setIsCompany(Boolean isCompany) {
        this.isCompany = isCompany;
    }

    public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public LANGUAGE getPrefLanguage() {
		return prefLanguage;
	}

	public void setPrefLanguage(LANGUAGE prefLanguage) {
		this.prefLanguage = prefLanguage;
	}

	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	public String getProfilePictureBlobKey() {
		return profilePictureBlobKey;
	}

	public void setProfilePictureBlobKey(String profilePictureBlobKey) {
		this.profilePictureBlobKey = profilePictureBlobKey;
	}

	public Long getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Long creationDate) {
		this.creationDate = creationDate;
	}

	public Long getLastLogonDate() {
		return lastLogonDate;
	}

	public void setLastLogonDate(Long lastLogonDate) {
		this.lastLogonDate = lastLogonDate;
	}
}
