package com.example.automate;

import com.example.automate.models.AutoClass;
import com.example.automate.models.DriverHistoryClass;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DriverHistoryInterface {

    @GET("driverHistory/")
    Call<List<DriverHistoryClass>> getDriverHistory();
}
