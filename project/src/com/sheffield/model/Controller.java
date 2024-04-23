
package com.sheffield.model;

import java.math.BigDecimal;

public class Controller extends Product {
<<<<<<< HEAD

    private String controllerType;

    public Controller(String name, String code, String brandName, int quantity, BigDecimal price, String gaugeScale,
            String controllerType) {
=======
    private ControllerType controllerType;
    

    public Controller (String name, String code, String brandName, int quantity, BigDecimal price, String gaugeScale, String controllerType) {
>>>>>>> 31ad3d7a5a159d732d88ec83d28639d4e0d2ea55
        super(name, code, brandName, quantity, price, gaugeScale);
        this.setControllerType(controllerType);
    }

<<<<<<< HEAD
    public String getControllerType() {
=======
    public ControllerType getControllerType(){
>>>>>>> 31ad3d7a5a159d732d88ec83d28639d4e0d2ea55
        return controllerType;
    }

    public void setControllerType(String controllerType) {
<<<<<<< HEAD
        if (isValidControllerType(controllerType)) {
            this.controllerType = controllerType;
        } else {
            throw new IllegalArgumentException("This type of controller is not valid");
        }
    }

    public boolean isValidControllerType(String controllerType) {
        return controllerType != null && controllerType.length() <= 100;
    }

}
=======
        try {
            this.controllerType = ControllerType.valueOf(controllerType.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("This type of controller type is not valid.");
        }
    }
}
>>>>>>> 31ad3d7a5a159d732d88ec83d28639d4e0d2ea55
