package com.example.framxpert;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class NextActivity extends AppCompatActivity {

    Toolbar toolbar;
    ImageView menu, notification;

    CardView cardWeather, cardCrop, cardMarket,
            cardContact, cardAbout, cardDisease;

    BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Change this to your actual layout
        setContentView(R.layout.activity_next);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        notification = findViewById(R.id.notification);

        cardWeather = findViewById(R.id.cardWeather);
        cardCrop = findViewById(R.id.cardCrop);
        cardMarket = findViewById(R.id.cardMarket);
        cardContact = findViewById(R.id.cardContact);
        cardAbout = findViewById(R.id.cardAbout);
        cardDisease = findViewById(R.id.cardDisease);

        bottomNavigation = findViewById(R.id.bottomNavigation);



        cardCrop.setOnClickListener(v -> {
            Intent intent = new Intent(NextActivity.this, CropActivity.class);
            startActivity(intent);
        });
        cardWeather.setOnClickListener(v -> {
            Intent intent = new Intent(NextActivity.this, WeatherActivity.class);
            startActivity(intent);
        });
        cardMarket.setOnClickListener(v -> {
            Intent intent = new Intent(NextActivity.this, MarketPrices.class);
            startActivity(intent);
        });
        cardContact.setOnClickListener(v -> {
            Intent intent = new Intent(NextActivity.this, ContactExpertActivity.class);
            startActivity(intent);
        });
        cardAbout .setOnClickListener(v -> {
            Intent intent = new Intent(NextActivity.this, DiseaseActivity.class);
            startActivity(intent);
        });
        cardDisease.setOnClickListener(v -> {
            Intent intent = new Intent(NextActivity.this, AboutActivity.class);
            startActivity(intent);
        });
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);

        bottomNavigationView.setOnItemSelectedListener(item -> {

            if (item.getItemId() == R.id.home) {
                return true;
            }

            if (item.getItemId() == R.id.market) {
                startActivity(new Intent(NextActivity.this, MarketPrices.class));
                return true;
            }

            if (item.getItemId() == R.id.alerts) {
                startActivity(new Intent(NextActivity.this, NotificationActivity.class));
                return true;
            }

            if (item.getItemId() == R.id.profile) {
                startActivity(new Intent(NextActivity.this, ProfileActivity.class));
                return true;
            }

            return false;
        });

    }
}