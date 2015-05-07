package com.renobidz.endpoints.dto;


/**
 * Created by lmgagne on 15-01-26.
 */
public class TransactionDTO {
    private Long id;
    private String receiptId;
    private String responseCode;
    private String authCode;
    private String transTime;
    private String transDate;
    private String transType;
    private Boolean complete;
    private String message;
    private String transAmount;
    private String txn_number;
    private Boolean timedOut;
    private Boolean isVisaDebit;
    //private Long orderId;
    private Long serviceId;
    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(String receiptId) {
        this.receiptId = receiptId;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getTransTime() {
        return transTime;
    }

    public void setTransTime(String transTime) {
        this.transTime = transTime;
    }

    public String getTransDate() {
        return transDate;
    }

    public void setTransDate(String transDate) {
        this.transDate = transDate;
    }

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    public Boolean getComplete() {
        return complete;
    }

    public void setComplete(Boolean complete) {
        this.complete = complete;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTransAmount() {
        return transAmount;
    }

    public void setTransAmount(String transAmount) {
        this.transAmount = transAmount;
    }

    public String getTxn_number() {
        return txn_number;
    }

    public void setTxn_number(String txn_number) {
        this.txn_number = txn_number;
    }

    public Boolean getTimedOut() {
        return timedOut;
    }

    public void setTimedOut(Boolean timedOut) {
        this.timedOut = timedOut;
    }

    public Boolean getIsVisaDebit() {
        return isVisaDebit;
    }

    public void setIsVisaDebit(Boolean isVisaDebit) {
        this.isVisaDebit = isVisaDebit;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
