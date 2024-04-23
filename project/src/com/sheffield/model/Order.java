package com.sheffield.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

public class Order {

    private String orderID;
    private String userID;
    private BigDecimal totalCost;
    private Status status;
    private Date date;
    private List<OrderLine> orderLines;

    public Order(String orderID, String userID, BigDecimal totalCost, Status status, Date date) {
        this.orderID = orderID;
        this.userID = userID;
        this.totalCost = totalCost;
        this.status = status;
        this.date = date;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "orderID: " + orderID + 
               ", userID: " + userID + 
               ", totalCost: " + totalCost +
               ", placed: " + status +
               ", date: " + date;  
    }
}
