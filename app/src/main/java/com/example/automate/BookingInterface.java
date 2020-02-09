package com.example.automate;

import com.example.automate.models.AutoClass;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface BookingInterface {

    @Headers("Content-Type: application/json")
    @POST("requests/")
    Call<AutoClass> bookRide(@Body String body);

    @GET("rides/nextStop")
    Call<Integer> getWhichAuto();

}
