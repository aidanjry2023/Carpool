package com.example.carpoolbuddy;

import java.util.ArrayList;

public class HeliCopter extends Vehicle {

    int maxAltitude;
    int maxAirSpeed;

    public HeliCopter(String owner, String model, String capacity, String vehicleID,
                      String vehicleType, String basePrice, int maxAltitude, int maxAirSpeed) {
        super(owner, model, capacity, vehicleID, vehicleType, basePrice);
        this.maxAltitude = maxAltitude;
        this.maxAirSpeed = maxAirSpeed;
    }
}
