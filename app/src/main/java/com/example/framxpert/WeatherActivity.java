package com.example.framxpert;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONObject;

public class WeatherActivity extends AppCompatActivity {

    private TextView tvLocation, tvTemperature, tvStatus,
            tvHumidity, tvWind, tvRain;
    TextView tvTempMax, tvTempMin;

    private ImageView imgWeather;
    private Button btnRefresh,btnCrop;
    private BottomNavigationView bottomNavigation;

    private FusedLocationProviderClient fusedLocationClient;

    private final String API_KEY = "c8c83fb5c072d9137f8e5def2f318e6a";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        tvLocation = findViewById(R.id.tvLocation);
        tvTemperature = findViewById(R.id.tvTemperature);
        tvStatus = findViewById(R.id.tvStatus);
        tvHumidity = findViewById(R.id.tvHumidity);
        tvWind = findViewById(R.id.tvWind);
        tvRain = findViewById(R.id.tvRain);

        imgWeather = findViewById(R.id.imgWeather);
        btnRefresh = findViewById(R.id.btnRefresh);
        bottomNavigation = findViewById(R.id.bottomNavigation);
        tvTempMax = findViewById(R.id.tvTempMax);
        tvTempMin = findViewById(R.id.tvTempMin);
        Button btnCrop = findViewById(R.id.btncrop);
        btnCrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WeatherActivity.this, CropActivity.class);
                startActivity(intent);
            }
        });

        fusedLocationClient =
                LocationServices.getFusedLocationProviderClient(this);

        getCurrentLocation();

        btnRefresh.setOnClickListener(v -> getCurrentLocation());

        bottomNavigation.setSelectedItemId(R.id.home);

        bottomNavigation.setOnItemSelectedListener(item -> {

            if (item.getItemId() == R.id.home) {
                return true;
            }

            if (item.getItemId() == R.id.market) {
                startActivity(new Intent(this, MarketPrices.class));
                finish();
                return true;
            }

            if (item.getItemId() == R.id.alerts) {
                startActivity(new Intent(this, NotificationActivity.class));
                finish();
                return true;
            }

            if (item.getItemId() == R.id.profile) {
                startActivity(new Intent(this, ProfileActivity.class));
                finish();
                return true;
            }

            return false;
        });
    }
    private void getCurrentLocation() {

        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    100
            );
            return;
        }

        fusedLocationClient.getLastLocation().addOnSuccessListener(location -> {

            if (location != null) {

                getWeather(location.getLatitude(), location.getLongitude());

            } else {

                Toast.makeText(this,
                        "Location not found. Turn ON GPS and try again.",
                        Toast.LENGTH_SHORT).show();
            }

        }).addOnFailureListener(e -> {

            Toast.makeText(this,
                    "Failed to get location",
                    Toast.LENGTH_SHORT).show();

        });
    }

    private void getWeather(double lat, double lon) {

        String url = "https://api.openweathermap.org/data/2.5/weather?lat="
                + lat
                + "&lon="
                + lon
                + "&units=metric&appid="
                + API_KEY;

        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,

                response -> {

                    try {

                        JSONObject main = response.getJSONObject("main");

                        JSONObject wind = response.getJSONObject("wind");
                        JSONArray weatherArray = response.getJSONArray("weather");
                        JSONObject weather = weatherArray.getJSONObject(0);

                        String city = response.getString("name");
                        double temp = main.getDouble("temp");
                        int humidity = main.getInt("humidity");
                        double windSpeed = wind.getDouble("speed");
                        String condition = weather.getString("main");

                        tvLocation.setText(city);
                        tvTemperature.setText(temp + " °C");
                        tvStatus.setText(condition);
                        tvHumidity.setText("Humidity : " + humidity + "%");
                        tvWind.setText("Wind : " + windSpeed + " m/s");
                        tvRain.setText("Rain Chance : --");

                        double maxTemp = main.getDouble("temp_max");
                        double minTemp = main.getDouble("temp_min");

                        tvTempMax.setText("Temp Max : " + Math.round(maxTemp) + "°C");
                        tvTempMin.setText("Temp Min : " + Math.round(minTemp) + "°C");

                        if (condition.equalsIgnoreCase("Clear")) {
                            imgWeather.setImageResource(R.drawable.sun);

                        } else if (condition.equalsIgnoreCase("Clouds")) {
                            imgWeather.setImageResource(R.drawable.wf);

                        } else if (condition.equalsIgnoreCase("Rain")) {
                            imgWeather.setImageResource(R.drawable.wf);

                        } else {
                            imgWeather.setImageResource(R.drawable.wf);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                },

                error -> Toast.makeText(
                        WeatherActivity.this,
                        "Weather Loading Failed",
                        Toast.LENGTH_SHORT
                ).show()

        );

        queue.add(request);
    }
    @Override
    public void onRequestPermissionsResult(
            int requestCode,
            @NonNull String[] permissions,
            @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(
                requestCode,
                permissions,
                grantResults);

        if (requestCode == 100) {

            if (grantResults.length > 0 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                getCurrentLocation();

            } else {

                Toast.makeText(this,
                        "Location Permission Denied",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

}