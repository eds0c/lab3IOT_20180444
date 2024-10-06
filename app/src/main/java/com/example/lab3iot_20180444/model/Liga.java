package com.example.lab3iot_20180444.model;

import com.google.gson.annotations.SerializedName;

public class Liga {
    @SerializedName("idLeague")
    private String idLeague;

    @SerializedName("strLeague")
    private String name;

    @SerializedName("strSport")
    private String sport;

    @SerializedName("strLeagueAlternate")
    private String alternateName;

    public String getIdLeague() {
        return idLeague;
    }

    public String getName() {
        return name;
    }

    public String getSport() {
        return sport;
    }

    public String getAlternateName() {
        return alternateName;
    }
}
