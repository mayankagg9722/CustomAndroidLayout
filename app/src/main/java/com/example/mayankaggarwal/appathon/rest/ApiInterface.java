package com.example.mayankaggarwal.appathon.rest;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by mayankaggarwal on 12/02/17.
 */

public interface ApiInterface {

    @POST("appPushDetails2")
    Call<JsonObject> pushData(@Body LoginRequest loginRequest);


}
