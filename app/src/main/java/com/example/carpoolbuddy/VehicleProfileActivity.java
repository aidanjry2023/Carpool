package com.example.carpoolbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class VehicleProfileActivity extends AppCompatActivity {
    TextView model;
    TextView owner;
    TextView capacity;
    Button button;

    Vehicle vehicle;
    User currUser;

    private FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_profile);

        //Getting current user and vehicle back with getIntent
        vehicle = (Vehicle) getIntent().getSerializableExtra("vehicle");
        currUser = (User) getIntent().getSerializableExtra("User");

        model = findViewById(R.id.vehicleModelTextView);
        owner = findViewById(R.id.vehicleOwnerTextView);
        capacity = findViewById(R.id.vehicleCapacityTextView);
        button = findViewById(R.id.button);

        //Setting the text for information about the vehicle
        model.setText(vehicle.getModel());
        owner.setText("Owner: " + vehicle.getOwner());
        capacity.setText("Available seats: " + vehicle.getCapacity());

        firestore = FirebaseFirestore.getInstance();
    }

    /**
     * This function is used by the user to book a seat on the current vehicle. When the user
     * presses the button, the capacity of the vehicle goes down by one on the app and on Firestore.
     * The text is also updated so that the user cannot book more than once.
     * @param v: essential for onClick functions.
     */
    public void bookVehicle(View v) {
        //Reducing the capacity of the vehicle by 1 when the user books
        int rideCapacity = Integer.parseInt(vehicle.getCapacity()) - 1;
        String updatedCapacity = String.valueOf(rideCapacity);

        //Setting the text on app to the new capacity
        vehicle.setCapacity(updatedCapacity);
        capacity.setText("Available seats: " + updatedCapacity);

//        I was getting a really weird error with this section so left commented out
//        ArrayList<String> ridersUIDS = vehicle.getRidersUIDs();
//        ridersUIDS.add(currUser.getUid());
//        vehicle.setRidersUIDs(ridersUIDS);

        //Adding updated vehicle back to firestore
        firestore.collection("vehicles").document(vehicle.getVehicleID()).set(vehicle);

        //Changing the text of the button to indicate that the task is completed
        button.setText("booking complete");

        v.setOnClickListener(null);
    }
}