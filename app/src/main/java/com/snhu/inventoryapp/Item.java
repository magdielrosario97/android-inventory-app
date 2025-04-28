package com.snhu.inventoryapp;

public class Item {
    private int id;
    private String name;
    private String sku;
    private int stock;
    private int imageId;

    public Item(int id, int imageId, String name, int stock, String sku) {
        this.id = id;
        this.imageId = imageId;
        this.name = name;
        this.stock = stock;
        this.sku = sku;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku.toUpperCase();
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
