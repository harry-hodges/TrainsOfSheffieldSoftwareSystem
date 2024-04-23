package com.sheffield.model;

import java.math.BigDecimal;

public class OrderLine {
    
    private String orderLineNumber;
    private String productCode;
    private String orderID;
    private int quantity;
    private BigDecimal orderLineCost;

    public OrderLine(String orderLineNumber, String productCode, String orderID, int quantity, BigDecimal orderLineCost){
        this.orderLineNumber = orderLineNumber;
        this.productCode = productCode;
        this.orderID = orderID;
        this.quantity = quantity;
        this.orderLineCost = orderLineCost;
    }

    public String getOrderLineNumber(){
        return orderLineNumber;
    }

    public void setOrderLineNumber(String orderLineNumber){
        this.orderLineNumber = orderLineNumber;
    }

    public String getProductCode(){
        return productCode;
    }

    public void setProductCode(String productCode){
        this.productCode = productCode;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public int getQuantity(){
        return quantity;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public BigDecimal getOrderLineCost() {
        return orderLineCost;
    }

    public void setOrderLineCost(BigDecimal orderLineCost) {
        this.orderLineCost = orderLineCost;
    }

    @Override
    public String toString(){
        return "OrderLineNumber: "+ orderLineNumber + ", productCode: " + productCode + ", Quantity: "+
        quantity+", Price: "+ orderLineCost; //+cost
    }





}
