package com.sheffield.model;

public class BankDetails {
    
    private String bankName;
    private String cardHolderName;
    private int cardNumber;
    private String expiryDate;
    private int securityCode;

    public BankDetails(String cardHolderName, String bankName, int cardNumber, String expiryDate, int securityCode){
        this.bankName = bankName;
        this.cardHolderName = cardHolderName;
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.securityCode = securityCode;
    }

    public String getBankName(){
        return bankName;
    }

    public void setBankName(String bankName){
        this.bankName = bankName;
    }

     public String getCardHolderName(){
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName){
        this.cardHolderName = cardHolderName;
    }

     public String getExpiryDate(){
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate){
        this.expiryDate = expiryDate;
    }

     public int getCardNumber(){
        return cardNumber;
    }

    public void setCardNumber(int cardNumber){
        this.cardNumber = cardNumber;
    }

    public int getSecurityCode(){
        return securityCode;
    }

    public void setSecurityCode(int securityCode){
        this.securityCode = securityCode;
    }

    
}
