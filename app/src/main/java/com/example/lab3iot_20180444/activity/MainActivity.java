package com.example.lab3iot_20180444.activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;

import androidx.appcompat.app.AlertDialog;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lab3iot_20180444.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //inicializamos ViewBinding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Listener para el botón Ingresar
        binding.btnIngresar.setOnClickListener(view -> {
            if (isConnectedToInternet()) {
                // Navega a AppActivity si hay conexión
                Intent intent = new Intent(MainActivity.this, AppActivity.class);
                startActivity(intent);
            } else {
                // Muestra el Dialog si no hay conexión
                showInternetSettingsDialog();
            }
        });
    }

    // Método para verificar la conexión a Internet
    private boolean isConnectedToInternet() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    // Método para mostrar un Dialog que redirige a los ajustes
    private void showInternetSettingsDialog() {
        new AlertDialog.Builder(this)
                .setTitle("No Internet Connection")
                .setMessage("Por favor, habilita la conexión a Internet.")
                .setPositiveButton("Ajustes", (dialog, which) -> {
                    // Redirige a los ajustes de Internet del dispositivo
                    Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
                    startActivity(intent);
                })
                .setNegativeButton("Cancelar", (dialog, which) -> dialog.dismiss())
                .create()
                .show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Limpia la referencia de binding cuando la actividad se destruye
        binding = null;
    }
}