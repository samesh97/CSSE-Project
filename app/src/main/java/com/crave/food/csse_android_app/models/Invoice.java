package com.crave.food.csse_android_app.models;

public class Invoice {
    private String id;
    private String cost;
    private double price;

    public Invoice(String id, String cost){
        this.id = id;
        this.cost = cost;
    }
    public String getId() {
        return id;
    }

    public void setId(String id){
        this.id=id;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost){
        this.cost=cost;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price){
        this.price=price;
    }
}
