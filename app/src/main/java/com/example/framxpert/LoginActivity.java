package com.example.framxpert;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;



import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText etEmail, etPassword;

    Button btnLogin;

    TextView tvSignup, tvForgotPassword;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth=FirebaseAuth.getInstance();

        etEmail=findViewById(R.id.etEmail);
        etPassword=findViewById(R.id.etPassword);

        btnLogin=findViewById(R.id.btnLogin);

        tvSignup=findViewById(R.id.tvSignup);
        tvForgotPassword=findViewById(R.id.tvForgotPassword);

        tvForgotPassword.setOnClickListener(v-> {
            Intent i = new Intent(LoginActivity.this, ForgotPassword.class);
            startActivity(i);
        });


        btnLogin.setOnClickListener(v ->
        {

            String email = etEmail.getText().toString();
            String password = etPassword.getText().toString();

            if (email.isEmpty())
            {
                etEmail.setError("Enter Email");
                return;
            }

            if (password.isEmpty()) {
                etPassword.setError("Enter Password");
                return;
            }

            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task ->
            {
                if (task.isSuccessful())
                {
                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(LoginActivity.this, NextActivity.class));

                    finish();

                } else {
                    String error = task.getException() != null ? task.getException().getMessage() : "Login Failed";
                    Toast.makeText(LoginActivity.this, error, Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(LoginActivity.this, SignupActivity.class));
                }
            });

        });


        tvSignup.setOnClickListener(v ->
        {
            startActivity(new Intent(LoginActivity.this,
                    SignupActivity.class));
        });

    }
}