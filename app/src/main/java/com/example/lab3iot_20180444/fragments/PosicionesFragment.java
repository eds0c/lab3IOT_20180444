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

import com.example.lab3iot_20180444.adapter.PosicionesAdapter;
import com.example.lab3iot_20180444.apiservice.LigaApiService;
import com.example.lab3iot_20180444.client.RetrofitClient;
import com.example.lab3iot_20180444.databinding.FragmentPosicionesBinding;
import com.example.lab3iot_20180444.model.Posicion;
import com.example.lab3iot_20180444.response.PosicionesResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PosicionesFragment extends Fragment {
    private FragmentPosicionesBinding binding;
    private LigaApiService apiService;
    private PosicionesAdapter posicionesAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inicializamos ViewBinding
        binding = FragmentPosicionesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.recyclerViewPosiciones.setLayoutManager(new LinearLayoutManager(getContext()));

        apiService = RetrofitClient.getClient("https://www.thesportsdb.com/api/v1/json/3/").create(LigaApiService.class);

        binding.btnBuscarPosiciones.setOnClickListener(v -> {
            String idLiga = binding.etIdLiga.getText().toString().trim();
            String temporada = binding.etTemporada.getText().toString().trim();
            if (idLiga.isEmpty() || temporada.isEmpty()) {
                Toast.makeText(getContext(), "Por favor, ingrese todos los campos", Toast.LENGTH_SHORT).show();
            } else {
                buscarPosiciones(idLiga, temporada);
            }
        });
    }

    private void buscarPosiciones(String idLiga, String temporada) {
        apiService.getPosicionesByLiga(idLiga, temporada).enqueue(new Callback<PosicionesResponse>() {
            @Override
            public void onResponse(Call<PosicionesResponse> call, Response<PosicionesResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Posicion> posiciones = response.body().getTable();
                    posicionesAdapter = new PosicionesAdapter(posiciones);
                    binding.recyclerViewPosiciones.setAdapter(posicionesAdapter);
                } else {
                    Toast.makeText(getContext(), "Error al obtener posiciones", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PosicionesResponse> call, Throwable t) {
                Log.e("PosicionesFragment", "Error: " + t.getMessage());
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
