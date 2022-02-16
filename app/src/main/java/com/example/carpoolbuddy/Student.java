package com.example.carpoolbuddy;

import java.util.ArrayList;

public class Student extends User {

    String graduatingYear;
    ArrayList<String> parentUIDs;

    public Student(String uid, String name, String email, String userType, double priceMultiplier,
                   ArrayList<String> ownedVehicles, String graduatingYear,
                   ArrayList<String> parentUIDs) {
        super(uid, name, email, userType, priceMultiplier, ownedVehicles);
        this.graduatingYear = graduatingYear;
        this.parentUIDs = parentUIDs;
    }
}
