package com.example.automate.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DriverHistoryPostClass {

    @SerializedName("driverId")
    @Expose
    private Integer driverId;
    @SerializedName("passengerId")
    @Expose
    private Integer passengerId;
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
    @SerializedName("start_time")
    @Expose
    private String startTime;

    DriverHistoryPostClass(Integer driverId, Integer passengerId, Integer driverRating, Integer noOfPassengers,
                           String source, String destination, String startTime) {
        this.driverId = driverId;
        this.passengerId = passengerId;
        this.driverRating = driverRating;
        this.noOfPassengers = noOfPassengers;
        this.source = source;
        this.destination = destination;
        this.startTime = startTime;

    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public Integer getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(Integer passengerId) {
        this.passengerId = passengerId;
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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

}
