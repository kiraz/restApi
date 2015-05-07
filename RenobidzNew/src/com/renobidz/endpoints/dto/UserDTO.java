package com.renobidz.endpoints.dto;

import com.renobidz.store.entity.util.LANGUAGE;
import com.renobidz.store.entity.util.SOCIALWEBSITE;

public class UserDTO {
	private Long id;
	private String email;
	private String userName;
	private String firstName;
	private String lastName;
    private String phoneNumber;
    private LANGUAGE prefLanguage;
	private String profilePicture;
	
	private Boolean isLocked;
	private Boolean isSocialUser;
	private SOCIALWEBSITE socialwebsite;

    private Boolean isCompany;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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
}
