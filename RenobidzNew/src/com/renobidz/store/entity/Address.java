package com.renobidz.store.entity;

import java.io.Serializable;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Ignore;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;
import com.renobidz.store.entity.util.COUNTRY;
import com.renobidz.store.entity.util.STATE;

/**
 * @author Ankur
 * 
 */
@Entity(name = "RB_ADDRESS")
public class Address implements Serializable {
	private static final long serialVersionUID = 6254194866821474850L;
	@Id
	private Long id;
	private String addressLine1; // https://www.usps.com/business/web-tools-apis/address-information.htm
	private String addressLine2; // might want to consider exploding address as a seperate Entity (someone who owns several properties)
    private String city;
	private STATE state;
	private String zipCode;
	private COUNTRY country; // Only one value USA. http://www.codeproject.com/Articles/28363/How-to-convert-IP-address-to-country-name
    private Boolean isDefault;
    private Float longitude;
    private Float latitude;
	@Load
	@Index
	private Ref<User> user;
	@Ignore
	private Long userId;	// Added for API purpose only hence marked as ignored

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

    public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public STATE getState() {
		return state;
	}

	public void setState(STATE state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public COUNTRY getCountry() {
		return country;
	}

	public void setCountry(COUNTRY country) {
		this.country = country;
	}


    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
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
