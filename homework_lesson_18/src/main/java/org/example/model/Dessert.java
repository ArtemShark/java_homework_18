package org.example.model;

import java.math.BigDecimal;

public class Dessert {
    private int dessertId;
    private String nameEN;
    private String nameOtherLanguage;
    private BigDecimal price;

    public Dessert() {}

    public Dessert(int dessertId, String nameEN, String nameOtherLanguage, BigDecimal price) {
        this.dessertId = dessertId;
        this.nameEN = nameEN;
        this.nameOtherLanguage = nameOtherLanguage;
        this.price = price;
    }

    public int getDessertId() { return dessertId; }
    public void setDessertId(int dessertId) { this.dessertId = dessertId; }
    public String getNameEN() { return nameEN; }
    public void setNameEN(String nameEN) { this.nameEN = nameEN; }
    public String getNameOtherLanguage() { return nameOtherLanguage; }
    public void setNameOtherLanguage(String nameOtherLanguage) { this.nameOtherLanguage = nameOtherLanguage; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
}
