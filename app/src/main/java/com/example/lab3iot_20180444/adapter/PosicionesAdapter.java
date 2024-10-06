package com.example.lab3iot_20180444.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab3iot_20180444.R;
import com.example.lab3iot_20180444.model.Posicion;

import java.util.List;

public class PosicionesAdapter extends RecyclerView.Adapter<PosicionesAdapter.PosicionViewHolder>{
    private final List<Posicion> posiciones;

    public PosicionesAdapter(List<Posicion> posiciones) {
        this.posiciones = posiciones;
    }

    @NonNull
    @Override
    public PosicionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_posicion, parent, false);
        return new PosicionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PosicionViewHolder holder, int position) {
        Posicion posicion = posiciones.get(position);
        holder.nombreEquipoTextView.setText(posicion.getNombreEquipo());
        holder.rankingTextView.setText(String.valueOf(posicion.getRanking()));
        holder.estadisticasTextView.setText(posicion.getVictorias() + "V " + posicion.getEmpates() + "E " + posicion.getDerrotas() + "D");
        holder.golesTextView.setText(posicion.getGolesAnotados() + "/" + posicion.getGolesConcedidos());
    }

    @Override
    public int getItemCount() {
        return posiciones.size();
    }

    static class PosicionViewHolder extends RecyclerView.ViewHolder {

        TextView nombreEquipoTextView;
        TextView rankingTextView;
        TextView estadisticasTextView;
        TextView golesTextView;

        public PosicionViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreEquipoTextView = itemView.findViewById(R.id.nombreEquipoTextView);
            rankingTextView = itemView.findViewById(R.id.rankingTextView);
            estadisticasTextView = itemView.findViewById(R.id.estadisticasTextView);
            golesTextView = itemView.findViewById(R.id.golesTextView);
        }
    }
}
