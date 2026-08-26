package com.example.framxpert;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class CropDetailsActivity extends AppCompatActivity {

    ImageView imgCrop;
    TextView txtCropName, txtTemperature, txtSoil,
            txtWater, txtDuration, txtFertilizer,
            txtDisease, txtDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop_details);

        // Back Arrow Enable
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Crop Details");
        }

        imgCrop = findViewById(R.id.imgCrop);
        txtCropName = findViewById(R.id.txtCropName);
        txtTemperature = findViewById(R.id.txtTemperature);
        txtSoil = findViewById(R.id.txtSoil);
        txtWater = findViewById(R.id.txtWater);
        txtDuration = findViewById(R.id.txtDuration);
        txtFertilizer = findViewById(R.id.txtFertilizer);
        txtDisease = findViewById(R.id.txtDisease);
        txtDescription = findViewById(R.id.txtDescription);
        View btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        imgCrop.setImageResource(getIntent().getIntExtra("image", 0));

        txtCropName.setText(getIntent().getStringExtra("name"));
        txtTemperature.setText("🌡 Temperature : " + getIntent().getStringExtra("temperature"));
        txtSoil.setText("🌱 Soil : " + getIntent().getStringExtra("soil"));
        txtWater.setText("💧 Water : " + getIntent().getStringExtra("water"));
        txtDuration.setText("⏳ Duration : " + getIntent().getStringExtra("duration"));
        txtFertilizer.setText("🌿 Fertilizer : " + getIntent().getStringExtra("fertilizer"));
        txtDisease.setText("🐛 Disease : " + getIntent().getStringExtra("disease"));
        txtDescription.setText(getIntent().getStringExtra("description"));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}