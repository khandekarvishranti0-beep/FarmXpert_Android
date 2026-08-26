package com.example.framxpert;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CropActivity extends AppCompatActivity {

    RecyclerView recyclerCrop;
    SearchView searchCrop;
    ImageView btnBack;

    ArrayList<CropModel> cropList;
    ArrayList<CropModel> filteredList;

    CropAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop);

        recyclerCrop = findViewById(R.id.recyclerCrop);
        searchCrop = findViewById(R.id.searchCrop);
        btnBack = findViewById(R.id.btnBack);
        ImageView imgMarket = findViewById(R.id.imgMarket);

        imgMarket.setOnClickListener(v -> {
            Intent intent = new Intent(CropActivity.this, MarketPrices.class);
            startActivity(intent);
        });

        recyclerCrop.setLayoutManager(new LinearLayoutManager(this));

        cropList = new ArrayList<>();

        // Rice
        cropList.add(new CropModel(
                R.drawable.rice,
                "Rice",
                "20°C - 35°C",
                "Clay Soil",
                "High",
                "120 Days",
                "NPK",
                "Blast Disease",
                "Rice is one of the most important food crops in the world. It grows best in warm climates with adequate water and fertile soil. Proper fertilizer and disease management improve crop yield.\"\n" +
                        "               "
        ));

        // Wheat
        cropList.add(new CropModel(
                R.drawable.wheat,
                "Wheat",
                "15°C - 25°C",
                "Loamy Soil",
                "Medium",
                "110 Days",
                "Nitrogen",
                "Rust Disease",
                "Wheat is one of the most important cereal crops in the world and a major staple food in " +
                        "India. It is mainly grown during the Rabi season and requires cool weather for proper growth. Wheat is rich in carbohydrates, protein, fiber, vitamins, and minerals, making it an essential part of a healthy diet. It is used to prepare flour for making chapati, bread, biscuits, pasta, noodles, and other food products. Wheat grows best in fertile, well-drained loamy soil with moderate irrigation and adequate sunlight. Proper fertilizer application and timely pest" +
                        " and disease management help achieve higher yield and better grain quality."
        ));

        // Cotton
        cropList.add(new CropModel(
                R.drawable.cotton,
                "Cotton",
                "21°C - 30°C",
                "Black Soil",
                "Medium",
                "180 Days",
                "Potash",
                "Bollworm",
                "Cotton is an important Kharif crop grown in warm climates. It requires black cotton soil, moderate rainfall, and plenty of sunlight for healthy growth. Cotton is mainly cultivated for its soft fiber, which is used to make clothes, textiles, and other fabric products. Proper irrigation, fertilizer application, and pest management help improve cotton yield and fiber quality."
        ));

        // Maize
        cropList.add(new CropModel(
                R.drawable.maiz,
                "Maize",
                "18°C - 27°C",
                "Well Drained Soil",
                "Medium",
                "95 Days",
                "NPK",
                "Leaf Blight",
                "Cotton is an important Kharif crop grown in warm climates. It requires black cotton soil, moderate rainfall, and plenty of sunlight for healthy growth. Cotton is mainly cultivated for its soft fiber, which is used to make clothes, textiles, and other fabric products. Proper irrigation, fertilizer application, and pest management help improve cotton yield and fiber quality.."
        ));

        // Sugarcane
        cropList.add(new CropModel(
                R.drawable.sugarcane,
                "Sugarcane",
                "20°C - 38°C",
                "Loamy Soil",
                "High",
                "12 Months",
                "Organic + NPK",
                "Red Rot",
                "Sugarcane is a tropical crop that requires a warm climate, fertile soil, and regular irrigation for good growth. It is mainly grown to produce sugar, jaggery, molasses, and ethanol. Sugarcane has a long growing period and needs proper fertilizer application and pest control to achieve high yield and good quality."
        ));

        adapter = new CropAdapter(this, cropList);
        recyclerCrop.setAdapter(adapter);

        // Back Button
        btnBack.setOnClickListener(v -> finish());

        // Search
        searchCrop.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return true;
            }
        });
    }

    private void filter(String text) {

        filteredList = new ArrayList<>();

        for (CropModel crop : cropList) {

            if (crop.getCropName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(crop);
            }
        }

        adapter = new CropAdapter(this, filteredList);
        recyclerCrop.setAdapter(adapter);
    }
}