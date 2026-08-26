package com.example.framxpert;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class EditProfile extends AppCompatActivity {


    EditText etName, etMobile, etVillage, etLand, etCrop;

    Button btnSave;


    DatabaseReference reference;

    FirebaseAuth auth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_edit_profile);



        etName = findViewById(R.id.etName);
        etMobile = findViewById(R.id.etMobile);
        etVillage = findViewById(R.id.etVillage);
        etLand = findViewById(R.id.etLand);
        etCrop = findViewById(R.id.etCrop);

        btnSave = findViewById(R.id.btnSave);



        auth = FirebaseAuth.getInstance();


        String uid = auth.getCurrentUser().getUid();


        reference = FirebaseDatabase.getInstance()
                .getReference("Users")
                .child(uid);



        btnSave.setOnClickListener(v -> {


            reference.child("name")
                    .setValue(etName.getText().toString());


            reference.child("mobile")
                    .setValue(etMobile.getText().toString());


            reference.child("village")
                    .setValue(etVillage.getText().toString());


            reference.child("land")
                    .setValue(etLand.getText().toString());


            reference.child("crop")
                    .setValue(etCrop.getText().toString());



            Toast.makeText(this,
                    "Profile Updated Successfully",
                    Toast.LENGTH_SHORT).show();


            finish();


        });


    }
}