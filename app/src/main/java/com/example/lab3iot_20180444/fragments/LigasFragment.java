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

import com.example.lab3iot_20180444.adapter.LigasAdapter;
import com.example.lab3iot_20180444.apiservice.LigaApiService;
import com.example.lab3iot_20180444.client.RetrofitClient;
import com.example.lab3iot_20180444.databinding.FragmentLigasBinding;
import com.example.lab3iot_20180444.model.Liga;
import com.example.lab3iot_20180444.response.LigasResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LigasFragment extends Fragment{

    private FragmentLigasBinding binding;
    private LigaApiService apiService;
    private LigasAdapter ligasAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inicializamos ViewBinding
        binding = FragmentLigasBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Configura el RecyclerView
        binding.recyclerViewLigas.setLayoutManager(new LinearLayoutManager(getContext()));

        // Inicializa el cliente Retrofits
        apiService = RetrofitClient.getClient("https://www.thesportsdb.com/api/v1/json/3/").create(LigaApiService.class);

        // Configura el botón de búsqueda
        binding.btnBuscar.setOnClickListener(v -> {
            String pais = binding.etBuscarPais.getText().toString().trim();
            if (pais.isEmpty()) {
                Toast.makeText(getContext(), "Por favor, ingrese un país", Toast.LENGTH_SHORT).show();
            } else {
                buscarLigasPorPais(pais);
            }
        });

        // Cargar las ligas generales al inicio
        cargarLigasGenerales();
    }

    private void cargarLigasGenerales() {
        apiService.getAllLeagues().enqueue(new Callback<LigasResponse>() {
            @Override
            public void onResponse(Call<LigasResponse> call, Response<LigasResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Liga> ligas = response.body().getLeagues();
                    ligasAdapter = new LigasAdapter(ligas);
                    binding.recyclerViewLigas.setAdapter(ligasAdapter);
                } else {
                    Toast.makeText(getContext(), "Error al obtener ligas generales", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LigasResponse> call, Throwable t) {
                Log.e("LigasFragment", "Error: " + t.getMessage());
                Toast.makeText(getContext(), "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void buscarLigasPorPais(String pais) {
        apiService.getLeaguesByCountry(pais).enqueue(new Callback<LigasResponse>() {
            @Override
            public void onResponse(Call<LigasResponse> call, Response<LigasResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Liga> ligas = response.body().getLeagues();
                    ligasAdapter = new LigasAdapter(ligas);
                    binding.recyclerViewLigas.setAdapter(ligasAdapter);
                } else {
                    Toast.makeText(getContext(), "Error al obtener ligas por país", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LigasResponse> call, Throwable t) {
                Log.e("LigasFragment", "Error: " + t.getMessage());
                Toast.makeText(getContext(), "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Limpia la referencia de binding
        binding = null;
    }
}
