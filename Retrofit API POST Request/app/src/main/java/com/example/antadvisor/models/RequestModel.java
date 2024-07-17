package com.example.antadvisor.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

// construct model for a request
public class RequestModel {

    // field representing the name of the request
    @SerializedName("name")
    @Expose
    private String name;

    // field representing the data of the request
    @SerializedName("data")
    @Expose
    private RequestChild data;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RequestChild getData() {
        return data;
    }

    public void setData(RequestChild data) {
        this.data = data;
    }

}// end 'RequestModel' class data model
