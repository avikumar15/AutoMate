package com.example.automate;

import com.example.automate.models.PassengerClass;
import com.example.automate.models.RiderClass;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RiderInterface {
    @GET("rides/")
    Call<List<RiderClass>> getRiderClass();
}
