package com.example.personafitnessapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;

import com.example.personafitnessapplication.adapter.AdapterExercicios;
import com.example.personafitnessapplication.model.Exercicio;

import java.util.ArrayList;
import java.util.List;

public class ListaExerciciosActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Exercicio> listaExercicios = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_exercicios);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_personal);

        recyclerView = findViewById(R.id.recyclerExercicio);
         //Lista de Exercicios
        this.criarExercicios();

        //configurar o adapter
        AdapterExercicios adapterExercicios = new AdapterExercicios();




        // Configurar recyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapterExercicios);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_exercicios,menu);
        return super.onCreateOptionsMenu(menu);
    }

public void criarExercicios(){

}
}