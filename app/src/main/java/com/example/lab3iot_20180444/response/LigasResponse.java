package com.example.lab3iot_20180444.response;

import com.example.lab3iot_20180444.model.Liga;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LigasResponse {
    @SerializedName("leagues")
    private List<Liga> leagues;

    public List<Liga> getLeagues() {
        return leagues;
    }
}
