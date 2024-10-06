package com.example.lab3iot_20180444.model;

import com.google.gson.annotations.SerializedName;

public class Posicion {
    @SerializedName("teamid")
    private String idEquipo;

    @SerializedName("name")
    private String nombreEquipo;

    @SerializedName("rank")
    private int ranking;

    @SerializedName("win")
    private int victorias;

    @SerializedName("draw")
    private int empates;

    @SerializedName("loss")
    private int derrotas;

    @SerializedName("goalsfor")
    private int golesAnotados;

    @SerializedName("goalsagainst")
    private int golesConcedidos;

    // Getters y Setters

    public String getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(String idEquipo) {
        this.idEquipo = idEquipo;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public int getVictorias() {
        return victorias;
    }

    public void setVictorias(int victorias) {
        this.victorias = victorias;
    }

    public int getEmpates() {
        return empates;
    }

    public void setEmpates(int empates) {
        this.empates = empates;
    }

    public int getDerrotas() {
        return derrotas;
    }

    public void setDerrotas(int derrotas) {
        this.derrotas = derrotas;
    }

    public int getGolesAnotados() {
        return golesAnotados;
    }

    public void setGolesAnotados(int golesAnotados) {
        this.golesAnotados = golesAnotados;
    }

    public int getGolesConcedidos() {
        return golesConcedidos;
    }

    public void setGolesConcedidos(int golesConcedidos) {
        this.golesConcedidos = golesConcedidos;
    }
}
