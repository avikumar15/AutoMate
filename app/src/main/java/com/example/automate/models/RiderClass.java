package com.example.automate.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RiderClass {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("driverId")
    @Expose
    private Integer driverId;
    @SerializedName("passengerId")
    @Expose
    private String passengerId;
    @SerializedName("stops")
    @Expose
    private String stops;
    @SerializedName("next_stop")
    @Expose
    private String nextStop;
    @SerializedName("capacity")
    @Expose
    private Integer capacity;

    /**
     * No args constructor for use in serialization
     *
     */
    public RiderClass() {
    }

    /**
     *
     * @param nextStop
     * @param driverId
     * @param passengerId
     * @param id
     * @param stops
     * @param capacity
     */
    public RiderClass(Integer id, Integer driverId, String passengerId, String stops, String nextStop, Integer capacity) {
        super();
        this.id = id;
        this.driverId = driverId;
        this.passengerId = passengerId;
        this.stops = stops;
        this.nextStop = nextStop;
        this.capacity = capacity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public String getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(String passengerId) {
        this.passengerId = passengerId;
    }

    public String getStops() {
        return stops;
    }

    public void setStops(String stops) {
        this.stops = stops;
    }

    public String getNextStop() {
        return nextStop;
    }

    public void setNextStop(String nextStop) {
        this.nextStop = nextStop;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

}