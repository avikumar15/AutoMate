package com.example.automate;

import com.example.automate.models.AutoClass;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AutoInterface {

    @GET("autoStatus/")
    Call<List<AutoClass>> getAutoList();

    @Headers("Content-Type: application/json")
    @POST("autoStatus/{id}")
    Call<AutoClass> setLocation(@Path("id") int groupId,@Body String body);

}
