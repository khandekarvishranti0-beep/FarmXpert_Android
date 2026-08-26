package com.example.framxpert;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;


public class ProfileActivity extends AppCompatActivity {


    ImageView imgProfile;

    TextView txtName, txtMobile, txtVillage, txtLand, txtCrop;

    Button btnEditProfile, btnLogout;


    FirebaseAuth auth;

    DatabaseReference reference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_profile);



        imgProfile = findViewById(R.id.imgProfile);

        txtName = findViewById(R.id.txtName);
        txtMobile = findViewById(R.id.txtMobile);
        txtVillage = findViewById(R.id.txtVillage);
        txtLand = findViewById(R.id.txtLand);
        txtCrop = findViewById(R.id.txtCrop);


        btnEditProfile = findViewById(R.id.btnEditProfile);
        btnLogout = findViewById(R.id.btnLogout);
        btnEditProfile.setOnClickListener(v -> {

            Intent intent = new Intent(ProfileActivity.this,
                    EditProfile.class);

            startActivity(intent);

        });



        auth = FirebaseAuth.getInstance();


        String uid = auth.getCurrentUser().getUid();



        reference = FirebaseDatabase.getInstance()
                .getReference("Users")
                .child(uid);



        // Get Farmer Data From Firebase

        reference.addListenerForSingleValueEvent(
                new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot snapshot) {


                        String name = snapshot.child("name")
                                .getValue(String.class);


                        String email = snapshot.child("email")
                                .getValue(String.class);



                        txtName.setText(name);


                        String mobile = snapshot.child("mobile")
                                .getValue(String.class);

                        String village = snapshot.child("village")
                                .getValue(String.class);

                        String land = snapshot.child("land")
                                .getValue(String.class);

                        String crop = snapshot.child("crop")
                                .getValue(String.class);



                        txtMobile.setText("📱 Mobile : " + mobile);

                        txtVillage.setText("📍 Village : " + village);

                        txtLand.setText("🌾 Land : " + land + " Acre");

                        txtCrop.setText("🌱 Main Crop : " + crop);


                    }


                    @Override
                    public void onCancelled(DatabaseError error) {

                        Toast.makeText(ProfileActivity.this,
                                "Failed to load profile",
                                Toast.LENGTH_SHORT).show();

                    }

                });



        btnLogout.setOnClickListener(v -> {


            auth.signOut();

            finish();


        });



    }
}