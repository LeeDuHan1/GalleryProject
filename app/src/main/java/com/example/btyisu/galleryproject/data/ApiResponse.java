package com.example.btyisu.galleryproject.data;

import com.google.gson.annotations.SerializedName;

public class ApiResponse {

    @SerializedName("result")
    private int result;

    @SerializedName("data")
    private Data data;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
