package com.sheffield.model;
import java.math.BigDecimal;


public class Product {
    private String productCode;
    private String name;
    private String brandName;
    private int quantity;
    private BigDecimal price;
    private GaugeScale gaugeScale;


    // Constructor to initialize a Product object with its attributes
    public Product(String productCode, String name, String brandName, int quantity, BigDecimal price, String gaugeScale) {
        this.setProductCode(productCode);
        this.setName(name);
        this.setBrandName(brandName);
        this.setQuantity(quantity);
        this.setPrice(price);
        this.setGaugeScale(gaugeScale);
    }

    // Getter and setter methods for the productCode attribute with validation
    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        if (isValidProductCode(productCode)) {
            this.productCode = productCode;
        } else {
            throw new IllegalArgumentException("Product code is not valid.");
        }
    }

    // Getter and setter methods for the Name attribute with validation
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (isValidName(name)) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("Product name is not valid.");
        }
    }

    // Getter and setter methods for the BrandName attribute with validation
    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        if (isValidBrandName(brandName)) {
            this.brandName = brandName;
        } else {
            throw new IllegalArgumentException("Brand name is not valid.");
        }
    }

    // Getter and setter methods for the Quantity attribute with validation
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (isValidQuantity(quantity)) {
            this.quantity = quantity;
        } else {
            throw new IllegalArgumentException("Quantity is not valid.");
        }
    }

    // Getter and setter methods for the Price attribute with validation
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        if (isValidPrice(price)) {
            this.price = price;
        } else {
            throw new IllegalArgumentException("Price is not valid.");
        }
    }

    // Getter and setter methods for the gaugeScale attribute with validation
    public GaugeScale getGaugeScale() {
        return gaugeScale;
    }

    public void setGaugeScale(String gaugeScale) {
        try {
            this.gaugeScale = GaugeScale.valueOf(gaugeScale.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("This type of gauge scale is not valid");
        }
    }

    // Private validation methods for each attribute
    private boolean isValidProductCode(String productCode) {
        return productCode != null && productCode.length() <= 100;
    }

    private boolean isValidName(String name) {
        return name != null && name.length() <= 100;
    }

    private boolean isValidBrandName(String brandName) {
        return brandName != null && brandName.length() <= 100;
    }

    private boolean isValidQuantity(int quantity) {
        return quantity >= 0;
    }

    private boolean isValidPrice(BigDecimal price) {
        // price must be non-negative
        return price != null && price.compareTo(BigDecimal.ZERO) >= 0;
    }


    @Override
    public String toString() {
        return "{ " +
            " productCode='" + getProductCode() + "'" +
            ", name='" + getName() + "'" +
            ", brandName='" + getBrandName() + "'" +
            ", quantity='" + getQuantity() + "'" +
            ", price='" + getPrice() + "'" +
            ", gaugeScale='" + getGaugeScale() + "'" +
            " }";
    }
}