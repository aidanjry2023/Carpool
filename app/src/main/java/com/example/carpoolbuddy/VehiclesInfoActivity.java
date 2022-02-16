package com.example.carpoolbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class VehiclesInfoActivity extends AppCompatActivity {

    private FirebaseFirestore firestore;

    private ArrayList<Vehicle> vehiclesList;
    private RecyclerView recyclerView;
    private VehicleRecyclerViewAdapter.RecyclerViewClickListener listener;

    User currUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicles_info);

        firestore = FirebaseFirestore.getInstance();

        recyclerView = findViewById(R.id.recyclerView);
        vehiclesList = new ArrayList<>();

        setVehicleInfo();

        currUser = (User) getIntent().getSerializableExtra("currUser");
    }

    private void setAdapter() {
        setOnClickListener();
        VehicleRecyclerViewAdapter adapter = new VehicleRecyclerViewAdapter(vehiclesList, listener);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void setOnClickListener() {
        listener = new VehicleRecyclerViewAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(getApplicationContext(), VehicleProfileActivity.class);
                intent.putExtra("vehicle", vehiclesList.get(position));
                intent.putExtra("User", currUser);
                startActivity(intent);
            }
        };
    }

    private void setVehicleInfo() {
        firestore.collection("vehicles").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()) {
                    for(QueryDocumentSnapshot document : task.getResult()) {
                        Vehicle vehicle = document.toObject(Vehicle.class);
                        vehiclesList.add(vehicle);
                    }
                    setAdapter();
                }
            }
        });
    }

    public void onClick(View v) {
        Intent intent = new Intent(this, AddVehicleActivity.class);
        intent.putExtra("currUser", currUser);
        startActivity(intent);
    }
}