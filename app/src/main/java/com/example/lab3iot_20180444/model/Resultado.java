package com.example.lab3iot_20180444.model;

import com.google.gson.annotations.SerializedName;

public class Resultado {
    @SerializedName("strHomeTeam")
    private String equipoLocal;

    @SerializedName("strAwayTeam")
    private String equipoVisitante;

    @SerializedName("intHomeScore")
    private String marcadorLocal;

    @SerializedName("intAwayScore")
    private String marcadorVisitante;

    public String getEquipoLocal() {
        return equipoLocal;
    }

    public String getEquipoVisitante() {
        return equipoVisitante;
    }

    public String getMarcador() {
        return marcadorLocal + " - " + marcadorVisitante;
    }
}
