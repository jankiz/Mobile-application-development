package com.example.shop;

public class ShopingItem {
    private String name;
    private String info;
    private String price;
    private float ratedInfo;
    private final int imageResource;

    public ShopingItem(String name, String info, String price, float ratedInfo, int imageResource) {
        this.name = name;
        this.info = info;
        this.price = price;
        this.ratedInfo = ratedInfo;
        this.imageResource = imageResource;
    }

    String getName() {
        return name;
    }
    String getInfo() {
        return info;
    }
    String getPrice() {
        return price;
    }
    float getRatedInfo() { return ratedInfo; }
    public int getImageResource() {
        return imageResource;
    }
}
