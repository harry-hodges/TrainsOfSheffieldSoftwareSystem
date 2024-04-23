package com.sheffield.model;
import java.math.BigDecimal;

public class TrackPack extends Product {
    
    public TrackPack(String productCode, String name, String brandName, int quantity, BigDecimal price, String gaugeScale) {
        super(productCode, name, brandName, quantity, price, gaugeScale);
    }
}