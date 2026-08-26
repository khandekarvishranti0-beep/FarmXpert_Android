package com.example.framxpert;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FeedbackActivity extends AppCompatActivity {

    EditText etName, etEmail, etFeedback;
    RatingBar ratingBar;
    Button btnSubmit, btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etFeedback = findViewById(R.id.etFeedback);

        ratingBar = findViewById(R.id.ratingBar);

        btnSubmit = findViewById(R.id.btnSubmit);
        btnLogout = findViewById(R.id.btnLogout);

        // Submit Feedback
        btnSubmit.setOnClickListener(v -> {

            String name = etName.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String feedback = etFeedback.getText().toString().trim();

            if (TextUtils.isEmpty(name)) {
                etName.setError("Enter Name");
                return;
            }

            if (TextUtils.isEmpty(email)) {
                etEmail.setError("Enter Email");
                return;
            }

            if (TextUtils.isEmpty(feedback)) {
                etFeedback.setError("Enter Feedback");
                return;
            }

            Toast.makeText(
                    FeedbackActivity.this,
                    "Thank you for your feedback!",
                    Toast.LENGTH_LONG
            ).show();

            etName.setText("");
            etEmail.setText("");
            etFeedback.setText("");
            ratingBar.setRating(0);
        });

        // Logout
        btnLogout.setOnClickListener(v -> {

            Intent intent = new Intent(
                    FeedbackActivity.this,
                    LoginActivity.class   // किंवा LoginActivity.class
            );

            startActivity(intent);
            finish();

        });
    }
}