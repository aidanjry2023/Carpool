package com.example.carpoolbuddy;

import java.util.ArrayList;

public class Bicycle extends Vehicle {

    String bicycleType;
    int weight;
    int weightCapacity;

    public Bicycle(String owner, String model, String capacity, String vehicleID,
                   String vehicleType, String basePrice, String bicycleType,
                   int weight, int weightCapacity) {
        super(owner, model, capacity, vehicleID, vehicleType, basePrice);
        this.bicycleType = bicycleType;
        this.weight = weight;
        this.weightCapacity = weightCapacity;
    }
}
