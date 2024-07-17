package com.example.antadvisor.interfaces;

import com.example.antadvisor.models.RequestModel;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

// blueprint for making API calls using Retrofit
// getList() method for sending a POST request to get a list of objects from 'objects' endpoint, with 'RequestModel' object as the request body
public interface APIInterface {

    /* send a POST request to the 'objects' endpoint to get a list of objects

     model - the request model containing necessary data for the request
     return - a 'Call' object representing the asynchronous operation to get the list of objects
     */
    // a POST request to the 'objects' api endpoint
    @POST("objects")
    // pass 'RequestModel' object (model) as request body. this model has data required for the API call
    Call<JsonObject> getList(@Body RequestModel model);

}// end 'APIInterface' interface
