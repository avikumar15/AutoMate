package com.example.automate;

import com.example.automate.models.AutoClass;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AutoInterface {

    @GET("autoStatus/")
    Call<List<AutoClass>> getAutoList();
}
