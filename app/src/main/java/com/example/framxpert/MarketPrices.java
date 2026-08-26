package com.example.framxpert;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MarketPrices extends AppCompatActivity {

    private RecyclerView recyclerMarket;
    private ArrayList<marketModel> marketList;
    private MarketAdapter adapter;

    private final String API_KEY =
            "579b464db66ec23bdd000001e9518ca99a1243966cb1bd4a3ec0c7a8";

    private final String API_URL =
            "https://api.data.gov.in/resource/9ef84268-d588-465a-a308-a864a43d0070";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market_prices);

        ImageView imgNext = findViewById(R.id.imgNext);
        recyclerMarket = findViewById(R.id.recyclerMarket);

        recyclerMarket.setLayoutManager(
                new LinearLayoutManager(this)
        );

        marketList = new ArrayList<>();

        adapter = new MarketAdapter(
                this,
                marketList
        );

        recyclerMarket.setAdapter(adapter);

        // Next → Contact Expert
        imgNext.setOnClickListener(v -> {

            Intent intent = new Intent(
                    MarketPrices.this,
                    ContactExpertActivity.class
            );

            startActivity(intent);
        });

        loadMarketPrices();
    }

    private void loadMarketPrices() {

        String url = API_URL
                + "?api-key=" + API_KEY
                + "&format=json"
                + "&limit=100";

        RequestQueue queue =
                Volley.newRequestQueue(this);

        JsonObjectRequest request =
                new JsonObjectRequest(
                        Request.Method.GET,
                        url,
                        null,

                        response -> {

                            try {

                                // Full response debugging
                                System.out.println(
                                        "MARKET API RESPONSE = "
                                                + response.toString()
                                );

                                if (!response.has("records")) {

                                    Toast.makeText(
                                            MarketPrices.this,
                                            "API response मध्ये records नाहीत",
                                            Toast.LENGTH_LONG
                                    ).show();

                                    return;
                                }

                                JSONArray records =
                                        response.getJSONArray("records");

                                marketList.clear();

                                if (records.length() == 0) {

                                    Toast.makeText(
                                            MarketPrices.this,
                                            "Market data available नाही",
                                            Toast.LENGTH_LONG
                                    ).show();

                                    adapter.notifyDataSetChanged();

                                    return;
                                }

                                for (int i = 0;
                                     i < records.length();
                                     i++) {

                                    JSONObject obj =
                                            records.getJSONObject(i);

                                    // Debug प्रत्येक record
                                    System.out.println(
                                            "RECORD = "
                                                    + obj.toString()
                                    );

                                    String commodity =
                                            obj.optString(
                                                    "commodity",
                                                    ""
                                            );

                                    String market =
                                            obj.optString(
                                                    "market",
                                                    ""
                                            );

                                    String modalPrice =
                                            obj.optString(
                                                    "modal_price",
                                                    ""
                                            );

                                    String minPrice =
                                            obj.optString(
                                                    "min_price",
                                                    ""
                                            );

                                    String maxPrice =
                                            obj.optString(
                                                    "max_price",
                                                    ""
                                            );

                                    // Empty values avoid करा
                                    if (commodity.trim().isEmpty()) {
                                        commodity = "Unknown Crop";
                                    }

                                    if (market.trim().isEmpty()) {
                                        market = "Maharashtra Market";
                                    }

                                    if (modalPrice.trim().isEmpty()) {
                                        modalPrice = "N/A";
                                    }

                                    String priceText;

                                    if (!modalPrice.equals("N/A")) {

                                        priceText =
                                                "₹ "
                                                        + modalPrice
                                                        + " / Quintal";

                                    } else if (
                                            !minPrice.isEmpty()
                                                    && !maxPrice.isEmpty()) {

                                        priceText =
                                                "₹ "
                                                        + minPrice
                                                        + " - ₹ "
                                                        + maxPrice
                                                        + " / Quintal";

                                    } else {

                                        priceText =
                                                "Price not available";
                                    }

                                    marketList.add(
                                            new marketModel(
                                                    commodity,
                                                    market,
                                                    priceText
                                            )
                                    );
                                }

                                adapter.notifyDataSetChanged();

                                Toast.makeText(
                                        MarketPrices.this,
                                        marketList.size()
                                                + " market records loaded",
                                        Toast.LENGTH_SHORT
                                ).show();

                            } catch (Exception e) {

                                e.printStackTrace();

                                Toast.makeText(
                                        MarketPrices.this,
                                        "Parsing Error: "
                                                + e.getMessage(),
                                        Toast.LENGTH_LONG
                                ).show();
                            }
                        },

                        error -> {

                            error.printStackTrace();

                            String message =
                                    "Unable to load market prices";

                            if (error.networkResponse != null) {

                                int statusCode =
                                        error.networkResponse.statusCode;

                                message =
                                        "API Error: "
                                                + statusCode;
                            }

                            Toast.makeText(
                                    MarketPrices.this,
                                    message,
                                    Toast.LENGTH_LONG
                            ).show();
                        }
                );

        queue.add(request);
    }
}