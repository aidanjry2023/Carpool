package com.example.carpoolbuddy;

import java.util.ArrayList;

public class Segway extends Vehicle {

    int range;
    int weightCapacity;

    public Segway(String owner, String model, String capacity, String vehicleID, String vehicleType,
                  String basePrice, int range, int weightCapacity) {
        super(owner, model, capacity, vehicleID, vehicleType, basePrice);
        this.range = range;
        this.weightCapacity = weightCapacity;
    }
}
