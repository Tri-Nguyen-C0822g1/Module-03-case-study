package model;

import java.awt.*;

public class Product {
    private int id;
    private String name;
    private String description;
    private double price;
    private String image;


    public Product(int id, String name, String description, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getPhoto() {
        return image;
    }

    public void setPhoto(String photo) {
        this.image = photo;
    }

    public Product(int id, String name, String description, double price, String photo) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = photo;
    }
    public Product( String name, String description, double price, String photo) {

        this.name = name;
        this.description = description;
        this.price = price;
        this.image = photo;
    }

    public Product(){};

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
