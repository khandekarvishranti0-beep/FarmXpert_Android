package com.example.framxpert;


public class NotificationModel {


    String title;
    String message;


    public NotificationModel(String title, String message) {

        this.title = title;
        this.message = message;

    }


    public String getTitle() {
        return title;
    }


    public String getMessage() {
        return message;
    }

}