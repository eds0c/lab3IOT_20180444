package com.example.lab3iot_20180444.response;

import com.example.lab3iot_20180444.model.Posicion;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PosicionesResponse {
    @SerializedName("table")
    private List<Posicion> table;

    // Getter para obtener la tabla de posiciones
    public List<Posicion> getTable() {
        return table;
    }

    public void setTable(List<Posicion> table) {
        this.table = table;
    }
}
