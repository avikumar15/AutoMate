package com.example.automate;

import com.example.automate.models.FriendClass;
import com.example.automate.models.PassengerClass;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PassangerInterface {

    @GET("passengers/")
    Call<List<PassengerClass>> getPassengerClass();
}
