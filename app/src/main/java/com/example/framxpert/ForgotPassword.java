package com.example.framxpert;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {

    EditText etEmail;
    Button btnResetPassword;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        etEmail = findViewById(R.id.etEmailFP);
        btnResetPassword = findViewById(R.id.btnResetPasswordFP);

        auth = FirebaseAuth.getInstance();

        btnResetPassword.setOnClickListener(v-> {
            String email=etEmail.getText().toString();
            if(TextUtils.isEmpty(email))
            {
                etEmail.setError("Enter Email");
                return;
            }

            auth.sendPasswordResetEmail(email).addOnSuccessListener(unused -> {
                Toast.makeText(this, "Password reset link sent to your email.", Toast.LENGTH_SHORT).show();
                finish();
            }).addOnFailureListener(e->
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show());
        });

    }
}