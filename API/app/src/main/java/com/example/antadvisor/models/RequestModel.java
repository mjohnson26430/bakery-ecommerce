package com.example.antadvisor.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestModel {

    @SerializedName("name")
    @Expose
    private String name;

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

}
