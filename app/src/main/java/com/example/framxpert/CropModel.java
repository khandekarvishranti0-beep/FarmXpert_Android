package com.example.framxpert;




public class CropModel {

    private int image;
    private String cropName;
    private String temperature;
    private String soil;
    private String water;
    private String duration;
    private String fertilizer;
    private String disease;
    private String description;
    private String season;

    public CropModel(int image,
                     String cropName,
                     String temperature,
                     String soil,
                     String water,
                     String duration,
                     String fertilizer,
                     String disease,
                     String description) {

        this.image = image;
        this.cropName = cropName;
        this.temperature = temperature;
        this.soil = soil;
        this.water = water;
        this.duration = duration;
        this.fertilizer = fertilizer;
        this.disease = disease;
        this.description = description;
    }

    public int getImage() {
        return image;
    }

    public String getCropName() {
        return cropName;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getSoil() {
        return soil;
    }

    public String getWater() {
        return water;
    }

    public String getDuration() {
        return duration;
    }

    public String getFertilizer() {
        return fertilizer;
    }

    public String getDisease() {
        return disease;
    }

    public String getDescription() {
        return description;
    }

    public String getSeason() {

        return season;


    }
}