package com.example.automate.models;

import com.example.automate.routemodels.DirectionModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RouteInterface {

    @GET("json")
    Call<DirectionModel> getDirectionDetails(@Query("origin") String origin, @Query("destination") String destination, @Query("waypoints") String waypoints, @Query("key") String key);

}
