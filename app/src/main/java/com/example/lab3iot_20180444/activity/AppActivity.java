package com.example.lab3iot_20180444.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.example.lab3iot_20180444.R;
import com.example.lab3iot_20180444.databinding.ActivityAppBinding;

public class AppActivity extends AppCompatActivity {

    private ActivityAppBinding binding;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAppBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        if (navHostFragment != null) {
            navController = navHostFragment.getNavController();
        }

        setupNavigationButtons();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        navController = navHostFragment.getNavController();


        setupNavigationButtons();
    }

    private void setupNavigationButtons() {
        binding.btnLigas.setOnClickListener(v -> navController.navigate(R.id.ligasFragment));

        binding.btnPosiciones.setOnClickListener(v -> navController.navigate(R.id.posicionesFragment));

        binding.btnResultados.setOnClickListener(v -> navController.navigate(R.id.resultadosFragment));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}