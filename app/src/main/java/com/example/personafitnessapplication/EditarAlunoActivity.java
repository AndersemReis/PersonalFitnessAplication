package com.example.personafitnessapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.widget.CheckBox;
import android.widget.EditText;

public class EditarAlunoActivity extends AppCompatActivity {

    EditText editTextemail, editTextNome, editTextIdade, editTextAltura, editTextPeso;
    CheckBox checkBoxMasculino, checkBoxFeminino;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_aluno);

        IniciaComponentes();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_activity_editar_alunos,menu);
        return super.onCreateOptionsMenu(menu);
    }


    private void IniciaComponentes(){
        editTextemail = findViewById(R.id.editTextEditEmail);
        editTextNome = findViewById(R.id.editTextEditarNome);
        editTextIdade = findViewById(R.id.editTextEditIdade);
        editTextAltura = findViewById(R.id.editTextEditAltura);
        editTextPeso = findViewById(R.id.editTextEditPeso);
        checkBoxFeminino = findViewById(R.id.checkBoxFeminino);
        checkBoxMasculino = findViewById(R.id.checkBoxMasculino);

    }
}