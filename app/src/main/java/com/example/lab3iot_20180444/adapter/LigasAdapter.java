package com.example.lab3iot_20180444.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab3iot_20180444.R;
import com.example.lab3iot_20180444.model.Liga;

import java.util.List;

public class LigasAdapter extends RecyclerView.Adapter<LigasAdapter.LigaViewHolder>{
    private final List<Liga> ligas;

    public LigasAdapter(List<Liga> ligas) {
        this.ligas = ligas;
    }

    @NonNull
    @Override
    public LigaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_liga, parent, false);
        return new LigaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LigaViewHolder holder, int position) {
        Liga liga = ligas.get(position);
        holder.nameTextView.setText(liga.getName());
        holder.sportTextView.setText(liga.getSport());
        holder.alternateNameTextView.setText(liga.getAlternateName());
    }

    @Override
    public int getItemCount() {
        return ligas.size();
    }

    static class LigaViewHolder extends RecyclerView.ViewHolder {

        TextView nameTextView;
        TextView sportTextView;
        TextView alternateNameTextView;

        public LigaViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            sportTextView = itemView.findViewById(R.id.sportTextView);
            alternateNameTextView = itemView.findViewById(R.id.alternateNameTextView);
        }
    }
}
