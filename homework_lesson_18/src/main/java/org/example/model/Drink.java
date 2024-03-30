package org.example.model;

import java.math.BigDecimal;

public class Drink {
    private int drinkId;
    private String nameEN;
    private String nameOtherLanguage;
    private BigDecimal price;

    public Drink() {}

    public Drink(int drinkId, String nameEN, String nameOtherLanguage, BigDecimal price) {
        this.drinkId = drinkId;
        this.nameEN = nameEN;
        this.nameOtherLanguage = nameOtherLanguage;
        this.price = price;
    }

    public int getDrinkId() { return drinkId; }
    public void setDrinkId(int drinkId) { this.drinkId = drinkId; }
    public String getNameEN() { return nameEN; }
    public void setNameEN(String nameEN) { this.nameEN = nameEN; }
    public String getNameOtherLanguage() { return nameOtherLanguage; }
    public void setNameOtherLanguage(String nameOtherLanguage) { this.nameOtherLanguage = nameOtherLanguage; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
}
