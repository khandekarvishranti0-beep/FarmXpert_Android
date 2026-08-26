package com.example.framxpert;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class DiseaseActivity extends AppCompatActivity {

    ImageView imgDisease, imgNext;
    Button btnUpload, btnDetect;
    TextView txtResult, txtSuggestion;

    Uri imageUri;

    ActivityResultLauncher<String> imagePickerLauncher =
            registerForActivityResult(
                    new ActivityResultContracts.GetContent(),
                    uri -> {
                        if (uri != null) {
                            imageUri = uri;
                            imgDisease.setImageURI(uri);
                        }
                    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease);

        // Initialize Views
        imgDisease = findViewById(R.id.imgDisease);
        imgNext = findViewById(R.id.imgNext);

        btnUpload = findViewById(R.id.btnUpload);
        btnDetect = findViewById(R.id.btnDetect);

        txtResult = findViewById(R.id.txtResult);
        txtSuggestion = findViewById(R.id.txtSuggestion);

        // Next Arrow Click
        imgNext.setOnClickListener(v -> {
            Intent intent = new Intent(DiseaseActivity.this,
                    AboutActivity.class); // पुढची Activity
            startActivity(intent);
        });

        // Upload Image
        btnUpload.setOnClickListener(v -> {
            imagePickerLauncher.launch("image/*");
        });

        // Detect Disease
        btnDetect.setOnClickListener(v -> {

            if (imageUri == null) {
                txtResult.setText("Please upload an image first.");
                txtSuggestion.setText("");
                return;
            }

            // Demo Result
            txtResult.setText("Disease Detected: Leaf Spot");

            txtSuggestion.setText(
                    "Recommended Treatment:\n\n" +
                            "• Remove infected leaves.\n" +
                            "• Spray a suitable fungicide.\n" +
                            "• Avoid overwatering.\n" +
                            "• Maintain proper field hygiene."
            );
        });
    }
}