package com.sheffield.model;
import java.math.BigDecimal;

public class RollingStock extends Product{

    private String era;
    private RollingStockType rollingStockType;

    public RollingStock(String productCode, String name, String brandName, int quantity, BigDecimal price, String gaugeScale, String era, String rollingStockType){
        super(productCode, name, brandName, quantity, price, gaugeScale);
        this.setEra(era);
        this.setRollingStockType(rollingStockType);
    }

    public String getEra(){
        return era;
    }

    public void setEra(String era) {
        if (era != null) {
            this.era = era;
        } else {
            throw new IllegalArgumentException("era is not valid.");
        }
    }

    public boolean isValidEra (String era) {
        return era != null && era.length() <= 50;
    }


    public RollingStockType getRollingStockType(){
        return rollingStockType;
    }

    public void setRollingStockType(String rollingStockType) {
        try {
            this.rollingStockType = RollingStockType.valueOf(rollingStockType.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("This type of rolling stock is not valid");
        }
    }
}