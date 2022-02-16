package com.example.carpoolbuddy;

import java.util.ArrayList;

public class Parent extends User {

    ArrayList<String> childrenUIDs;

    public Parent(String uid, String name, String email, String userType, double priceMultiplier,
                  ArrayList<String> ownedVehicles, ArrayList<String> childrenUIDs) {
        super(uid, name, email, userType, priceMultiplier, ownedVehicles);
        this.childrenUIDs = childrenUIDs;
    }


}
