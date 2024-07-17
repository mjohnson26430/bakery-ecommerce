package com.example.antadvisor.interfaces;

import com.example.antadvisor.models.RequestModel;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface APIInterface {


    @POST("objects")
    Call<JsonObject> getList(@Body RequestModel model);

}