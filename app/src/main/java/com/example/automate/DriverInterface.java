package com.example.automate;

import com.example.automate.models.DriverClass;
import com.example.automate.models.DriverHistoryClass;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DriverInterface {

    @GET("drivers/")
    Call<List<DriverClass>> getDriverClass();
}
