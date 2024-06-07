package com.enigmacamp.store.products;

import com.enigmacamp.store.services.handlers.ProductServiceImpl;

public class Product {
    private final String id;
    private String name;
    private String brand;
    private Integer price;
    private String createDate;

    public Product(String name, String brand, Integer price, String createDate) {
        this.id = generateId();
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.createDate = createDate;
    }

    private String generateId() {
        return "P00" + (ProductServiceImpl.getProductId());
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}