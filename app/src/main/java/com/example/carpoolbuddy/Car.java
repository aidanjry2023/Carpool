package com.example.carpoolbuddy;

import java.util.ArrayList;

public class Car extends Vehicle {

    int range;

    public Car(String owner, String model, String capacity, String vehicleID, String vehicleType,
               String basePrice, int range) {
        super(owner, model, capacity, vehicleID, vehicleType, basePrice);
        this.range = range;
    }
}
