package com.example.baek.baekkimchi.dataset;

/**
 * Created by Baek on 2015-04-27.
 */

public class DataSet {
    private String name;
    private String company;
    private int price;

    public DataSet(String name){
        this.name = name;
    }

    public DataSet(String name, String company, int price) {
        this.name = name;
        this.company = company;
        this.price = price;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return this.company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
