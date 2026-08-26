package com.example.framxpert;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.framxpert.NextActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {

    private EditText etName, etEmail, etPassword;
    private Button btnSignup;
    private TextView tvLogin;
    private FirebaseAuth auth;
    private EditText etMobile, etVillage, etLand, etCrop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);


        auth = FirebaseAuth.getInstance();


        etName = findViewById(R.id.etNameS);
        etEmail = findViewById(R.id.etEmailS);
        etPassword = findViewById(R.id.etPasswordS);
        btnSignup = findViewById(R.id.btnSignup);
        tvLogin = findViewById(R.id.tvLogin);
        etMobile = findViewById(R.id.etMobileS);
        etVillage = findViewById(R.id.etVillageS);
        etLand = findViewById(R.id.etLandS);
        etCrop = findViewById(R.id.etCropS);

        // 3. Apply Window Insets Safely
        View mainView = findViewById(R.id.main);
        if (mainView != null) {
            ViewCompat.setOnApplyWindowInsetsListener(mainView, (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        }

        // 4. Sign-Up Form Validation and Action
        if (btnSignup != null) {

            btnSignup.setOnClickListener(v -> {
                String name = etName.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                String mobile = etMobile.getText().toString().trim();

                String village = etVillage.getText().toString().trim();

                String land = etLand.getText().toString().trim();

                String crop = etCrop.getText().toString().trim();

                // Validate form inputs before executing Firebase actions
                if (TextUtils.isEmpty(name)) {
                    etName.setError("Full name is required");
                    etName.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(email)) {
                    etEmail.setError("Email address is required");
                    etEmail.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    etPassword.setError("Password is required");
                    etPassword.requestFocus();
                    return;
                }
                if (password.length() < 6) {
                    etPassword.setError("Password must be at least 6 characters long");
                    etPassword.requestFocus();
                    return;
                }

                // Register user with Firebase Auth
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful() && auth.getCurrentUser() != null) {
                                String uid = auth.getCurrentUser().getUid();

                                // Save additional user info to Firebase Realtime Database
                                DatabaseReference reference = FirebaseDatabase.getInstance()
                                        .getReference("Users")
                                        .child(uid);

                                reference.child("name").setValue(name);
                                reference.child("email").setValue(email);
                                reference.child("mobile").setValue(mobile);

                                reference.child("village").setValue(village);

                                reference.child("land").setValue(land);

                                reference.child("crop").setValue(crop);


                                Toast.makeText(this, "Account Created Successfully!", Toast.LENGTH_SHORT).show();


                                // Navigate to Home Dashboard
                                startActivity(new Intent(SignupActivity.this, NextActivity.class));
                                finish();
                            } else {
                                // Show the specific Firebase exception error to the user
                                String errorMsg = task.getException() != null ?
                                        task.getException().getMessage() : "Registration failed. Try again.";
                                Toast.makeText(this, errorMsg, Toast.LENGTH_LONG).show();
                            }
                        });
            });
        }


        if (tvLogin != null) {
            tvLogin.setOnClickListener(v -> finish());
        }
    }
}