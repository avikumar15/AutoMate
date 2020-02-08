package com.example.automate.models;

import java.util.List;

public class DriverClass{

    String name;
    String mobileNumber;
    float rating;
    RideHistoryDriver history;

    public DriverClass(String name, String mobileNumber, float rating, RideHistoryDriver history) {
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.rating = rating;
        this.history = history;
    }

}
