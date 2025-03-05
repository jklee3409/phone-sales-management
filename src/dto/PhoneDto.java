package dto;

import java.util.Date;

public class PhoneDto {
    private int phone_id;
    private String model;
    private String brand;
    private Date released_at;
    private int price;
    private int stock;

    public PhoneDto (int phone_id, String model, String brand, Date released_at, int price, int stock) {
        this.phone_id = phone_id;
        this.model = model;
        this.brand = brand;
        this.released_at = released_at;
        this.price = price;
        this.stock = stock;
    }

    public int getPhone_id() {
        return phone_id;
    }

    public void setPhone_id(int phone_id) {
        this.phone_id = phone_id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Date getReleased_at() {
        return released_at;
    }

    public void setReleased_at(Date released_at) {
        this.released_at = released_at;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
