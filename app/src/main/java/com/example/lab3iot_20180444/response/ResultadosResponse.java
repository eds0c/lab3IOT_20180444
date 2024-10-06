package com.example.lab3iot_20180444.response;

import com.example.lab3iot_20180444.model.Resultado;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultadosResponse {
    @SerializedName("events")
    private List<Resultado> resultados;

    public List<Resultado> getResultados() {
        return resultados;
    }
}
