package com.example.framxpert;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;



public class NotificationActivity extends AppCompatActivity {


    RecyclerView recyclerNotification;

    ArrayList<NotificationModel> notificationList;

    NotificationAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_notification);



        recyclerNotification =
                findViewById(R.id.recyclerNotification);



        notificationList = new ArrayList<>();



        notificationList.add(new NotificationModel(
                "🌦 Weather Alert",
                "Heavy rain expected tomorrow. Protect your crops."
        ));



        notificationList.add(new NotificationModel(
                "💰 Market Price Update",
                "Wheat price updated in Pune market."
        ));



        notificationList.add(new NotificationModel(
                "🌱 Disease Detection",
                "Check your crop health regularly."
        ));



        notificationList.add(new NotificationModel(
                "👨‍🌾 Expert Advice",
                "New farming tips available."
        ));



        adapter = new NotificationAdapter(notificationList);



        recyclerNotification.setLayoutManager(
                new LinearLayoutManager(this));


        recyclerNotification.setAdapter(adapter);


    }

}