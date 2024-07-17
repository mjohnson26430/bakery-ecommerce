package com.example.antadvisor.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


// child model for a request, contains details such as year, price, CPU model, & hard disk size
public class RequestChild {

    // field representing the year of the request
    @SerializedName("year")
    @Expose
    private Integer year;

    // field representing the price of the request
    @SerializedName("price")
    @Expose
    private Double price;

    // field representing the CPU_model of the request
    @SerializedName("CPU_model")
    @Expose
    private String cPUModel;

    // field representing the Hard_disk_size of the request
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

}// end 'RequestChild' class

