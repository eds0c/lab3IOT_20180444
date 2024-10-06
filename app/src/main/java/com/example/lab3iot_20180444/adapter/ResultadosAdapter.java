package com.example.lab3iot_20180444.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab3iot_20180444.R;
import com.example.lab3iot_20180444.model.Resultado;

import java.util.List;

public class ResultadosAdapter extends  RecyclerView.Adapter<ResultadosAdapter.ResultadoViewHolder>{
    private final List<Resultado> resultados;

    public ResultadosAdapter(List<Resultado> resultados) {
        this.resultados = resultados;
    }

    @NonNull
    @Override
    public ResultadoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_resultado, parent, false);
        return new ResultadoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultadoViewHolder holder, int position) {
        Resultado resultado = resultados.get(position);
        holder.equipoLocalTextView.setText(resultado.getEquipoLocal());
        holder.equipoVisitanteTextView.setText(resultado.getEquipoVisitante());
        holder.marcadorTextView.setText(resultado.getMarcador());
    }

    @Override
    public int getItemCount() {
        return resultados.size();
    }

    static class ResultadoViewHolder extends RecyclerView.ViewHolder {

        TextView equipoLocalTextView;
        TextView equipoVisitanteTextView;
        TextView marcadorTextView;

        public ResultadoViewHolder(@NonNull View itemView) {
            super(itemView);
            equipoLocalTextView = itemView.findViewById(R.id.equipoLocalTextView);
            equipoVisitanteTextView = itemView.findViewById(R.id.equipoVisitanteTextView);
            marcadorTextView = itemView.findViewById(R.id.marcadorTextView);
        }
    }
}
