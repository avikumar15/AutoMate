package com.example.automate;

import com.example.automate.models.DriverHistoryPostClass;

import retrofit2.http.Body;
import retrofit2.http.POST;

public interface DriverHistoryPostInterface {
    @POST("/driverHistory")
    DriverHistoryPostInterface postJson(@Body DriverHistoryPostClass body);
}
