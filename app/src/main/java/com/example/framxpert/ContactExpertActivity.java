package com.example.framxpert;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactExpertActivity extends AppCompatActivity {

    RecyclerView recyclerExperts;
    ArrayList<ExpertModel> expertList;
    ExpertAdapter adapter;
    ImageView imgNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_expert);

        // Initialize Views
        recyclerExperts = findViewById(R.id.recyclerExperts);
        imgNext = findViewById(R.id.imgNext);

        // Next Button Click
        imgNext.setOnClickListener(v -> {
            Intent intent = new Intent(ContactExpertActivity.this,
                    DiseaseActivity.class);
            startActivity(intent);
        });

        // RecyclerView
        recyclerExperts.setLayoutManager(new LinearLayoutManager(this));

        expertList = new ArrayList<>();

        expertList.add(new ExpertModel(
                "Dr. Rahul Patil",
                "Wheat & Rice Specialist",
                "9876543210",
                "rahulpatil@gmail.com"
        ));

        expertList.add(new ExpertModel(
                "Dr. Sneha Kulkarni",
                "Sugarcane Specialist",
                "9876543211",
                "snehakulkarni@gmail.com"
        ));

        expertList.add(new ExpertModel(
                "Dr. Amit Deshmukh",
                "Cotton & Maize Specialist",
                "9876543212",
                "amitdeshmukh@gmail.com"
        ));

        adapter = new ExpertAdapter(this, expertList);
        recyclerExperts.setAdapter(adapter);
    }
}