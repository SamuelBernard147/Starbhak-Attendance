package com.samuelbernard.starbhakattendance.model;

import com.google.gson.annotations.SerializedName;

public class ResponseScan {
    @SerializedName("data")
    private String data;
    @SerializedName("status")
    private int status;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
