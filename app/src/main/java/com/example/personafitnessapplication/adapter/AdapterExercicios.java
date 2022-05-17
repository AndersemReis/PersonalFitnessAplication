package com.example.personafitnessapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.personafitnessapplication.R;

public class AdapterExercicios extends RecyclerView.Adapter<AdapterExercicios.MyViewHolder> {


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.exercicio_lista, parent, false);

        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.nomeExercicio.setText("Teste");
        holder.frequencia.setText("test");
        holder.repeticao.setText("test");

    }

    @Override
    public int getItemCount() {
        return 9;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView nomeExercicio;
        TextView repeticao;
        TextView frequencia;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nomeExercicio = itemView.findViewById(R.id.textViewExercicios);
            repeticao = itemView.findViewById(R.id.textViewRepeticoes);
            frequencia = itemView.findViewById(R.id.textViewFrequencia);
        }
    }


}
