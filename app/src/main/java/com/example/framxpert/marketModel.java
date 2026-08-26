package com.example.framxpert;

public class marketModel {

    String crop, market, price;

    public marketModel(String crop, String market, String price) {
        this.crop = crop;
        this.market = market;
        this.price = price;
    }

    public String getCrop() {
        return crop;
    }

    public String getMarket() {
        return market;
    }

    public String getPrice() {
        return price;
    }
}