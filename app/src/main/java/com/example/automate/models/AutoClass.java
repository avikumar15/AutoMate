package com.example.automate.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AutoClass {

    @SerializedName("autoId")
    @Expose
    public Integer autoId;
    @SerializedName("autoLatitude")
    @Expose
    public Double autoLatitude;
    @SerializedName("autoLongitude")
    @Expose
    public Double autoLongitude;
    @SerializedName("lastUpdatedAt")
    @Expose
    public String lastUpdatedAt;

    public Integer getAutoId() {
        return autoId;
    }

    public void setAutoId(Integer autoId) {
        this.autoId = autoId;
    }

    public Double getAutoLatitude() {
        return autoLatitude;
    }

    public void setAutoLatitude(Double autoLatitude) {
        this.autoLatitude = autoLatitude;
    }

    public Double getAutoLongitude() {
        return autoLongitude;
    }

    public void setAutoLongitude(Double autoLongitude) {
        this.autoLongitude = autoLongitude;
    }

    public String getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    public void setLastUpdatedAt(String lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
    }
}