package com.example.carpoolbuddy;

import java.util.ArrayList;

public class Teacher extends User{

    String inSchoolTitle;

    public Teacher(String inSchoolTitle, String uid, String name, String email, String userType,
                   double priceMultiplier, ArrayList<String> ownedVehicles) {
        super(uid, name, email, userType, priceMultiplier, ownedVehicles);
        this.inSchoolTitle = inSchoolTitle;
    }
}
