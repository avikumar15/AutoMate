package com.example.automate;

import com.example.automate.models.DriverClass;
import com.example.automate.models.FriendClass;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Friendinterface {

    @GET("friends/")
    Call<List<FriendClass>> getFriendClass();
}
