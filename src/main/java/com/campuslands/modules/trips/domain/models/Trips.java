package com.campuslands.modules.trips.domain.models;

import java.sql.Date;

public class Trips {

    int id;
    Date date;
    double price;

    public Trips(int id, Date date, double price) {
        this.id = id;
        this.date = date;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}