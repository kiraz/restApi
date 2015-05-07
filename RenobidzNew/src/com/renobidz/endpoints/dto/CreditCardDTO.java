package com.renobidz.endpoints.dto;

import com.renobidz.store.entity.util.CREDITCARD;

/**
 * Created by lmgagne on 15-01-19.
 */
public class CreditCardDTO {
    private Long id;
    private CREDITCARD cardType;
    private String cardHolderName;
    private String cardNumber;
    private String expMonth;
    private String expYear;
    private Boolean isValidated;
    private String avsNumber;
    private String avsStreet;
    private String avsZipCode;
    private String cvdCode;
    private String cvdIndicator;
    private Boolean isDefault;
    private Long userId;

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

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
