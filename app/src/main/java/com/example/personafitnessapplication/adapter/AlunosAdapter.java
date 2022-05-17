package com.example.personafitnessapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.personafitnessapplication.model.Pessoa;
import com.example.personafitnessapplication.R;

import java.util.List;

public class AlunosAdapter extends ArrayAdapter {
    Context context;
    int layoutResourceid;
    List<Pessoa> dados;

    public AlunosAdapter(Context context, int layoutResourceid, List<Pessoa> dados){
        super(context, layoutResourceid, dados);

        this.context = context;
        this.layoutResourceid = layoutResourceid;
        this.dados = dados;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;

        if (view == null){
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            view = layoutInflater.inflate(layoutResourceid, parent, false);
        }

        TextView id = (TextView) view.findViewById(R.id.textViewId);
        TextView nome = (TextView) view.findViewById(R.id.textViewNome);
        TextView email = (TextView) view.findViewById(R.id.textViewEmail);
        TextView idade = (TextView) view.findViewById(R.id.textViewIdade);
        TextView altura = (TextView) view.findViewById(R.id.textViewAltura);
        TextView peso = (TextView)  view.findViewById(R.id.textViewPeso);
        TextView personal = (TextView) view.findViewById(R.id.textViewPersonal);

        Pessoa aluno = dados.get(position);

        id.setText(String.valueOf(aluno.getId()));
        nome.setText(String.valueOf(aluno.getNome()));
        email.setText(String.valueOf(aluno.getEmail()));
        idade.setText(String.valueOf(aluno.getIdade()));
        altura.setText(String.valueOf(aluno.getAltura()));
        peso.setText(String.valueOf(aluno.getPeso()));
        personal.setText(String.valueOf(aluno.getPersonal()));

        return view;
    }

    public void setDados(List<Pessoa> dados) {
        this.clear();
        this.addAll(dados);
        this.dados = dados;
        this.notifyDataSetChanged();
    }

    @Override
    public long getItemId(int position) {
        return dados.get(position).getId();
    }
}
