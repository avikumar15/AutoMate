package com.example.automate.models;

public class RideHistoryDriver {
    float avgRating;
    int noOfPassengers;
    String source;
    String destination;
    String date;

    public RideHistoryDriver(float avgRating, int noOfPassengers, String source, String destination, String date) {
        this.avgRating = avgRating;
        this.noOfPassengers = noOfPassengers;
        this.source = source;
        this.destination = destination;
        this.date = date;
    }
}