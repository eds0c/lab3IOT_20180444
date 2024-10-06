package com.example.lab3iot_20180444.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.lab3iot_20180444.adapter.ResultadosAdapter;
import com.example.lab3iot_20180444.apiservice.LigaApiService;
import com.example.lab3iot_20180444.client.RetrofitClient;
import com.example.lab3iot_20180444.databinding.FragmentResultadosBinding;
import com.example.lab3iot_20180444.model.Resultado;
import com.example.lab3iot_20180444.response.ResultadosResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultadosFragment extends Fragment {

    private FragmentResultadosBinding binding;
    private LigaApiService apiService;
    private ResultadosAdapter resultadosAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inicializamos ViewBinding
        binding = FragmentResultadosBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.recyclerViewResultados.setLayoutManager(new LinearLayoutManager(getContext()));

        apiService = RetrofitClient.getClient("https://www.thesportsdb.com/api/v1/json/3/").create(LigaApiService.class);

        binding.btnBuscarResultados.setOnClickListener(v -> {
            String idLiga = binding.etIdLigaResultados.getText().toString().trim();
            String temporada = binding.etTemporadaResultados.getText().toString().trim();
            String ronda = binding.etRondaResultados.getText().toString().trim();
            if (idLiga.isEmpty() || temporada.isEmpty() || ronda.isEmpty()) {
                Toast.makeText(getContext(), "Por favor, ingrese todos los campos", Toast.LENGTH_SHORT).show();
            } else {
                buscarResultados(idLiga, temporada, ronda);
            }
        });
    }

    private void buscarResultados(String idLiga, String temporada, String ronda) {
        apiService.getResultadosByLiga(idLiga, temporada, ronda).enqueue(new Callback<ResultadosResponse>() {
            @Override
            public void onResponse(Call<ResultadosResponse> call, Response<ResultadosResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Resultado> resultados = response.body().getResultados();
                    resultadosAdapter = new ResultadosAdapter(resultados);
                    binding.recyclerViewResultados.setAdapter(resultadosAdapter);
                } else {
                    Toast.makeText(getContext(), "Error al obtener los resultados", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResultadosResponse> call, Throwable t) {
                Log.e("ResultadosFragment", "Error: " + t.getMessage());
                Toast.makeText(getContext(), "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
