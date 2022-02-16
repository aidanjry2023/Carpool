package com.example.carpoolbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.UUID;

public class AddVehicleActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseFirestore firestore;

    Button addVehicleButton;
    Spinner vehicleTypeMenu;
    EditText model;
    EditText capacity;
    EditText price;

    private static final String[] vehicleTypes = {"Bicycle", "Car", "Helicopter", "Segway"};

    User currUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vehicle);

        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        addVehicleButton = findViewById(R.id.addVehicle);
        model = findViewById(R.id.editTextVehicleModel);
        capacity = findViewById(R.id.editTextVehicleCapacity);
        price = findViewById(R.id.editTextVehiclePrice);

        //Setting the spinner adapter for user types
        vehicleTypeMenu = findViewById(R.id.vehicleSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddVehicleActivity.this,
                android.R.layout.simple_spinner_item, vehicleTypes);

        //Actually creating the spinner here
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        vehicleTypeMenu.setAdapter(adapter);
    }

    public boolean formValid()
    {
        return true;
    }

    /**
     * This function is used to add a new vehicle to the current user. It takes in Strings from the
     * user by EditTexts and uses them as parameters for newly created vehicles. Depending on the
     * selected item from the spinner, the vehicle type will be different.
     * @param v: this parameter is important for the function's use as an onClick function.
     */

    public void addNewVehicle(View v)
    {
        currUser = (User) getIntent().getSerializableExtra("currUser");

        //Getting all the user inputs
        String vehicleModel = model.getText().toString();
        String vehicleCapacity = capacity.getText().toString();
        String vehiclePrice = price.getText().toString();
        String vehicleID = UUID.randomUUID().toString();
        String dropDownItem = vehicleTypeMenu.getSelectedItem().toString();

        //A different vehicle is created in accordance to the selected spinner item
        if (dropDownItem.equals("Bicycle"))
        {
            //Creating the vehicle and adding it to Firestore
            Vehicle bicycle = new Vehicle(currUser.getName(), vehicleModel, vehicleCapacity,
                    vehicleID, dropDownItem, vehiclePrice);
            firestore.collection("vehicles").document(bicycle.getVehicleID()).set(bicycle);

            //Adding the vehicles to the user's owned vehicles
            ArrayList userVehicles = currUser.getOwnedVehicles();
            userVehicles.add(bicycle.getVehicleID());
            currUser.setOwnedVehicles(userVehicles);
            firestore.collection("users").document(currUser.getUid()).set(currUser);
        }
        else if (dropDownItem.equals("Car"))
        {
            Vehicle car = new Vehicle(currUser.getName(), vehicleModel, vehicleCapacity,
                    vehicleID, dropDownItem, vehiclePrice);
            firestore.collection("vehicles").document(car.getVehicleID()).set(car);

            ArrayList userVehicles = currUser.getOwnedVehicles();
            userVehicles.add(car.getVehicleID());
            currUser.setOwnedVehicles(userVehicles);
            firestore.collection("users").document(currUser.getUid()).set(currUser);
        }
        else if (dropDownItem.equals("Helicopter"))
        {
            Vehicle helicopter = new Vehicle(currUser.getName(), vehicleModel, vehicleCapacity,
                    vehicleID, dropDownItem, vehiclePrice);
            firestore.collection("vehicles").document(helicopter.getVehicleID()).
                    set(helicopter);

            ArrayList userVehicles = currUser.getOwnedVehicles();
            userVehicles.add(helicopter.getVehicleID());
            currUser.setOwnedVehicles(userVehicles);
            firestore.collection("users").document(currUser.getUid()).set(currUser);
        }
        else if (dropDownItem.equals("Segway"))
        {
            Vehicle segway = new Vehicle(currUser.getName(), vehicleModel, vehicleCapacity,
                    vehicleID, dropDownItem, vehiclePrice);
            firestore.collection("vehicles").document(segway.getVehicleID()).
                    set(segway);

            ArrayList userVehicles = currUser.getOwnedVehicles();
            userVehicles.add(segway.getVehicleID());
            currUser.setOwnedVehicles(userVehicles);
            firestore.collection("users").document(currUser.getUid()).set(currUser);
        }

        Intent intent = new Intent(this, VehiclesInfoActivity.class);
        intent.putExtra("currUser", currUser);
        startActivity(intent);
    }
}