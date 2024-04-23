package com.sheffield.model;
import java.math.BigDecimal;

public class Locomotive extends Product {

<<<<<<< HEAD
    // private Type type;
    // private String era;

    // enum Type {
    // ANALOGUE, DCC_READY, DCC_FITTED, DCC_SOUND;
    // }

    // public Locomotive(String productCode, String name, String brandName, int
    // quantity, BigDecimal price, Type type,
    // String era) {
    // super(productCode, name, brandName, quantity, price);
    // this.type = type;
    // this.era = era;
    // }

    // public Type getType() {
    // return type;
    // }

    // public String getEra() {
    // return era;
    // }

    // public void setType(Type type) {
    // if (isValidType(type)) {
    // this.type = type;
    // } else {
    // throw new IllegalArgumentException("This type of locomotive is not valid");
    // }
    // }

    // public boolean isValidType(Type type) {
    // return type != null;
    // }
    private String era;
    private String locomotiveType;

    public Locomotive(String productCode, String name, String brandName, int quantity, BigDecimal price,
            String gaugeScale, String era, String locomotiveType) {
        super(productCode, name, brandName, quantity, price, gaugeScale);
        this.era = era;
        this.locomotiveType = locomotiveType;
    }

    public void setEra(String era) {
        if (isValidEra(era)) {
            this.era = era;
        } else {
            throw new IllegalArgumentException("Era is not valid");
        }
=======
    private String era;
    private LocomotiveType locomotiveType;


    public Locomotive(String productCode, String name, String brandName, int quantity, BigDecimal price,
            String gaugeScale, String era, String locomotiveType) {
        super(productCode, name, brandName, quantity, price, gaugeScale);
        this.setEra(era);
        this.setLocomotiveType(locomotiveType);
>>>>>>> 31ad3d7a5a159d732d88ec83d28639d4e0d2ea55
    }

    public String getEra() {
        return era;
    }

<<<<<<< HEAD
    public void setLocomotiveType(String locomotiveType) {
        if (isValidLocomotiveType(locomotiveType)) {
            this.locomotiveType = locomotiveType;
        } else {
            throw new IllegalArgumentException("This type of locomotive is not valid");
        }
    }

    public String getLocomotiveType() {
        return locomotiveType;
    }

    public boolean isValidEra(String era) {
        return era != null && era.length() <= 50;
    }

    public boolean isValidLocomotiveType(String locomotiveType) {
        return locomotiveType != null && locomotiveType.length() <= 100;
=======
    public void setEra(String era) {
        if (isValidEra(era)) {
            this.era = era;
        } else {
            throw new IllegalArgumentException("Era is not valid");
        }
    }

    public boolean isValidEra(String era) {
        return era != null && era.length() <= 50;
>>>>>>> 31ad3d7a5a159d732d88ec83d28639d4e0d2ea55
    }

    public LocomotiveType getLocomotiveType() {
        return locomotiveType;
    }

    public void setLocomotiveType(String locomotiveType) {
        try {
            this.locomotiveType = LocomotiveType.valueOf(locomotiveType.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("This type of locomotive is not valid");
        }
    }
}

