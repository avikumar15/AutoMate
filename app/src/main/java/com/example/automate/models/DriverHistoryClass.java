package com.example.automate.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DriverHistoryClass {

    @SerializedName("ride_id")
    @Expose
    private Integer rideId;
    @SerializedName("driverId")
    @Expose
    private Integer driverId;
    @SerializedName("driver_rating")
    @Expose
    private Integer driverRating;
    @SerializedName("no_of_passengers")
    @Expose
    private Integer noOfPassengers;
    @SerializedName("source")
    @Expose
    private String source;
    @SerializedName("destination")
    @Expose
    private String destination;
    @SerializedName("date")
    @Expose
    private String date;

    public Integer getRideId() {
        return rideId;
    }

    public void setRideId(Integer rideId) {
        this.rideId = rideId;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public Integer getDriverRating() {
        return driverRating;
    }

    public void setDriverRating(Integer driverRating) {
        this.driverRating = driverRating;
    }

    public Integer getNoOfPassengers() {
        return noOfPassengers;
    }

    public void setNoOfPassengers(Integer noOfPassengers) {
        this.noOfPassengers = noOfPassengers;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}