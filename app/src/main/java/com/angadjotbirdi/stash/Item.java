package com.angadjotbirdi.stash;

/**
 * Created by root on 10/24/16.
 */

public class Item {

    private int id;
    private int price;
    private String name;


    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}