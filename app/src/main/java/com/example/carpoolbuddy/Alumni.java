package com.example.carpoolbuddy;

import java.util.ArrayList;

public class Alumni extends User {

    String graduateYear;

    public Alumni(String uid, String name, String email, String userType, double priceMultiplier,
                  ArrayList<String> ownedVehicles, String graduateYear) {
        super(uid, name, email, userType, priceMultiplier, ownedVehicles);
        this.graduateYear = graduateYear;
    }
}
