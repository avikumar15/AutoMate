package com.example.automate.models;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class DriverClass
{

    @SerializedName("driverId")
    @Expose
    private Integer driverId;
    @SerializedName("drivername")
    @Expose
    private String drivername;
    @SerializedName("rating")
    @Expose
    private Integer rating;
    @SerializedName("auto_no")
    @Expose
    private String autoNo;
    @SerializedName("mobile_no")
    @Expose
    private String mobileNo;
    @SerializedName("history")
    @Expose
    private List<History> history = null;
    private final static long serialVersionUID = -5009207865571708809L;

    /**
     * No args constructor for use in serialization
     *
     */

    /**
     *
     * @param driverId
     * @param drivername
     * @param rating
     * @param mobileNo
     * @param history
     * @param autoNo
     */

    public DriverClass() {

    }

    public DriverClass(Integer driverId, String drivername, Integer rating, String autoNo, String mobileNo, List<History> history) {
        super();
        this.driverId = driverId;
        this.drivername = drivername;
        this.rating = rating;
        this.autoNo = autoNo;
        this.mobileNo = mobileNo;
        this.history = history;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public String getDrivername() {
        return drivername;
    }

    public void setDrivername(String drivername) {
        this.drivername = drivername;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getAutoNo() {
        return autoNo;
    }

    public void setAutoNo(String autoNo) {
        this.autoNo = autoNo;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public List<History> getHistory() {
        return history;
    }

    public void setHistory(List<History> history) {
        this.history = history;
    }
    public class History implements Serializable
    {

        @SerializedName("ride_id")
        @Expose
        private Integer rideId;
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
        private final static long serialVersionUID = -508799664061734410L;

        /**
         * No args constructor for use in serialization
         *
         */
        public History() {
        }

        /**
         *
         * @param driverId
         * @param destination
         * @param passengerId
         * @param startTime
         * @param driverRating
         * @param noOfPassengers
         * @param source
         * @param rideId
         */
        public History(Integer rideId, Integer driverId, Integer passengerId, Integer driverRating, Integer noOfPassengers, String source, String destination, String startTime) {
            super();
            this.rideId = rideId;
            this.driverId = driverId;
            this.passengerId = passengerId;
            this.driverRating = driverRating;
            this.noOfPassengers = noOfPassengers;
            this.source = source;
            this.destination = destination;
            this.startTime = startTime;
        }

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
}