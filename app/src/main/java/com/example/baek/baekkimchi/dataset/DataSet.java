package com.example.baek.baekkimchi.dataset;

import java.io.Serializable;

/**
 * Created by Baek on 2015-04-27.
 */

public class DataSet implements Serializable {
    private String index;
    private String name;
    private String model;
    private int price;
    private String type;
    private String engine_type;
    private String supply_method;
    private String displacement;
    private String fuel_type;
    private String fuel_economy;
    private String riding_personal;
    private String drive_type;
    private String mission;
    private String max_token;
    private String max_output;
    private String xml;

    public DataSet(String index, String name, String model, int price, String type, String engine_type, String supply_method
            , String displacement, String fuel_type, String fuel_economy, String riding_personal, String drive_type
            , String mission, String max_token, String max_output, String xml) {
        this.index = index;
        this.name = name;
        this.model = model;
        this.price = price;
        this.type = type;
        this.engine_type = engine_type;
        this.supply_method = supply_method;
        this.displacement = displacement;
        this.fuel_type = fuel_type;
        this.fuel_economy = fuel_economy;
        this.riding_personal = riding_personal;
        this.drive_type = drive_type;
        this.mission = mission;
        this.max_token = max_token;
        this.max_output = max_output;
        this.xml = xml;
    }

    public String getIndex(){ return index; }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return this.model;
    }

    public int getPrice() {
        return this.price;
    }

    public String getType() { return this.type ;}

    public String getEngine_type() { return this.engine_type; }

    public String getSupply_method() { return this.supply_method; }

    public String getDisplacement() { return this.displacement; }

    public String getFuel_type() { return this.fuel_type; }

    public String getFuel_economy() { return this.fuel_economy; }

    public String getRiding_personal() { return this.riding_personal; }

    public String getDrive_type() { return this.drive_type; }

    public String getMission() { return this.mission; }

    public String getMax_token() { return this.max_token; }

    public String getMax_output() { return this.max_output; }

    public String getXml(){ return this.xml; }

}
