package com.renobidz.store.entity;

import java.io.Serializable;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;
import com.renobidz.store.entity.util.CREDITCARD;


/**
 * Created by lmgagne on 15-01-19.
 */
@Entity(name = "RB_CREDITCARD")
public class CreditCard implements Serializable{
    private static final long serialVersionUID = 3650619283550326372L;

    @Id
    private Long id;
    private CREDITCARD cardType;
    private String cardHolderName;
    private String cardNumber;          //pan - credit card number, 20 characters
    private String expMonth;            //expDate - expiry month - format MM
    private String expYear;             //expDate - expiry year - format YY
    private Boolean isValidated;
    private String avsNumber;           //street number, 19 digits
    private String avsStreet;           //street name, 19 characters
    private String avsZipCode;          //zipcode, 10 characters
    private String cvdCode;             //cvdCode, 3-4 characters
    private String cvdIndicator = "1";  //cvdIndicator, usually 1
    private Boolean isDefault = false;
    @Load
    @Index
    private Ref<User> user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CREDITCARD getCardType() {
        return cardType;
    }

    public void setCardType(CREDITCARD cardType) {
        this.cardType = cardType;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpMonth() {
        return expMonth;
    }

    public void setExpMonth(String expMonth) {
        this.expMonth = expMonth;
    }

    public String getExpYear() {
        return expYear;
    }

    public void setExpYear(String expYear) {
        this.expYear = expYear;
    }

    public Boolean getIsValidated() {
        return isValidated;
    }

    public void setIsValidated(Boolean isValidated) {
        this.isValidated = isValidated;
    }

    public String getAvsNumber() {
        return avsNumber;
    }

    public void setAvsNumber(String avsNumber) {
        this.avsNumber = avsNumber;
    }

    public String getAvsStreet() {
        return avsStreet;
    }

    public void setAvsStreet(String avsStreet) {
        this.avsStreet = avsStreet;
    }

    public String getAvsZipCode() {
        return avsZipCode;
    }

    public void setAvsZipCode(String avsZipCode) {
        this.avsZipCode = avsZipCode;
    }

    public String getCvdCode() {
        return cvdCode;
    }

    public void setCvdCode(String cvdCode) {
        this.cvdCode = cvdCode;
    }

    public String getCvdIndicator() {
        return cvdIndicator;
    }

    public void setCvdIndicator(String cvdIndicator) {
        this.cvdIndicator = cvdIndicator;
    }

    public Ref<User> getUser() {
        return user;
    }

    public void setUser(Ref<User> user) {
        this.user = user;
    }

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }
}
