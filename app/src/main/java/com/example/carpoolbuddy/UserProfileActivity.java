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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.UUID;

public class UserProfileActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseFirestore firestore;

    Button goToInfoButton;
    Spinner TypeMenu;
    EditText username;
    EditText email;

    private static final String[] userTypes = {"Alumni", "Parent", "Student", "Teacher"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        goToInfoButton = findViewById(R.id.goToInfoButton);
        username = findViewById(R.id.editTextUserName);
        email = findViewById(R.id.editTextUserEmail);

        //Creating a spinner for each type of user that can be created
        TypeMenu = findViewById(R.id.TypeMenu);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(UserProfileActivity.this,
                android.R.layout.simple_spinner_item, userTypes);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        TypeMenu.setAdapter(adapter);
    }

    /**
     * This function is used to add a new user to Firestore. It takes in Strings from the
     * user by EditTexts and uses them as parameters for the user that is being created. Depending
     * on the selected item from the spinner, the user type will be different.
     * @param v: essential for use as an onClick.
     */
    public void goToInfo(View v)
    {
        //Setting all the userInputs to variables that can be used later on
        String usernameText = username.getText().toString();
        String emailText = email.getText().toString();
        String UID = UUID.randomUUID().toString();
        String dropDownItem = TypeMenu.getSelectedItem().toString();
        double priceMultiplier = 1;
        ArrayList<String> ownedVehicles = new ArrayList<>();

        //Creating a different user for each spinner item (certain parameters are added for each type)
        if (dropDownItem.equals("Alumni"))
        {
            String graduateYear = "";

            //Creating the user and adding to Firestore
            Alumni alumni = new Alumni(UID, usernameText, emailText, "Alumni",
                    priceMultiplier, ownedVehicles, graduateYear);
            firestore.collection("users").document(alumni.getUid()).set(alumni);

            //Getting the user back and sending the data to AddVehicleActivity through Intent
            firestore.collection("users").document(alumni.getUid()).get()
                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if(task.isSuccessful())
                    {
                        DocumentSnapshot ds = task.getResult();
                        User currUser = ds.toObject(User.class);

                        Intent i = new Intent(UserProfileActivity.this, AddVehicleActivity.class);
                        i.putExtra("currUser", currUser);
                        startActivity(i);
                    }
                }
            });
        }
        else if (dropDownItem.equals("Parent"))
        {
            ArrayList<String> childrenUIDs = new ArrayList<>();

            Parent parent = new Parent(UID, usernameText, emailText, "Parent",
                    priceMultiplier, ownedVehicles, childrenUIDs);
            firestore.collection("users").document(parent.getUid()).set(parent);
            firestore.collection("users").document(parent.getUid()).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful())
                        {
                            DocumentSnapshot ds = task.getResult();
                            User currUser = ds.toObject(User.class);

                            Intent i = new Intent(UserProfileActivity.this, AddVehicleActivity.class);
                            i.putExtra("currUser", currUser);
                            startActivity(i);
                        }
                    }
                });

        }
        else if (dropDownItem.equals("Student"))
        {
            String graduatingYear = "";
            ArrayList<String> parentUIDs = new ArrayList<>();

            Student student = new Student(UID, usernameText, emailText, "Student",
                    priceMultiplier, ownedVehicles, graduatingYear, parentUIDs);
            firestore.collection("users").document(student.getUid()).set(student);
            firestore.collection("users").document(student.getUid()).get()
                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if(task.isSuccessful())
                            {
                                DocumentSnapshot ds = task.getResult();
                                User currUser = ds.toObject(User.class);

                                Intent i = new Intent(UserProfileActivity.this, AddVehicleActivity.class);
                                i.putExtra("currUser", currUser);
                                startActivity(i);
                            }
                        }
                    });
        }
        else if (dropDownItem.equals("Teacher"))
        {
            String inSchoolTitle = "";

            Teacher teacher = new Teacher(inSchoolTitle, UID, usernameText, emailText,
                    "Teacher", priceMultiplier, ownedVehicles);
            firestore.collection("users").document(teacher.getUid()).set(teacher);
            firestore.collection("users").document(teacher.getUid()).get()
                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if(task.isSuccessful())
                            {
                                DocumentSnapshot ds = task.getResult();
                                User currUser = ds.toObject(User.class);

                                Intent i = new Intent(UserProfileActivity.this, AddVehicleActivity.class);
                                i.putExtra("currUser", currUser);
                                startActivity(i);
                            }
                        }
                    });
        }
    }

    public void signIn(View v) {
        Intent intent = new Intent(this, AddVehicleActivity.class);
        startActivity(intent);
    }
}