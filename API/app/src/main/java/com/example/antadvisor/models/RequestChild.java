package com.example.antadvisor.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class RequestChild {

    @SerializedName("year")
    @Expose
    private Integer year;

    @SerializedName("price")
    @Expose
    private Double price;

    @SerializedName("CPU_model")
    @Expose
    private String cPUModel;

    @SerializedName("Hard_disk_size")
    @Expose
    private String hardDiskSize;

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCPUModel() {
        return cPUModel;
    }

    public void setCPUModel(String cPUModel) {
        this.cPUModel = cPUModel;
    }

    public String getHardDiskSize() {
        return hardDiskSize;
    }

    public void setHardDiskSize(String hardDiskSize) {
        this.hardDiskSize = hardDiskSize;
    }

}

