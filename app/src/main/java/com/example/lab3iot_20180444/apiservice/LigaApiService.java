package com.example.lab3iot_20180444.apiservice;

import com.example.lab3iot_20180444.response.LigasResponse;
import com.example.lab3iot_20180444.response.PosicionesResponse;
import com.example.lab3iot_20180444.response.ResultadosResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LigaApiService {
    @GET("all_leagues.php")
    Call<LigasResponse> getAllLeagues();

    @GET("search_all_leagues.php")
    Call<LigasResponse> getLeaguesByCountry(@Query("c") String country);

    @GET("lookuptable.php")
    Call<PosicionesResponse> getPosicionesByLiga(@Query("l") String idLiga, @Query("s") String temporada);
    @GET("eventspastleague.php")
    Call<ResultadosResponse> getResultadosByLiga(@Query("id") String idLiga, @Query("s") String temporada, @Query("r") String ronda);
}
