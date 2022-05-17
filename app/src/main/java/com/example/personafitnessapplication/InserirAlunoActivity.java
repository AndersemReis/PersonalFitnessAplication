package com.example.personafitnessapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;


public class InserirAlunoActivity extends AppCompatActivity {
    EditText editTextemail, editTextNome, editTextIdade, editTextAltura, editTextPeso;
    CheckBox checkBoxMasculino, checkBoxFeminino;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_inserir_aluno);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        IniciaComponentes();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_inserir_aluno, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public void save() {
        IniciaComponentes();

        if (editTextemail != null) {
            PersonalDbHelper db = new PersonalDbHelper(getApplicationContext());
            String sexo = "";

            String email = editTextemail.getText().toString();
            String nome = editTextNome.getText().toString();
            int idade = Integer.parseInt(editTextIdade.getText().toString());
            int altura = Integer.parseInt(editTextAltura.getText().toString());
            int peso = Integer.parseInt(editTextPeso.getText().toString());
            if (checkBoxFeminino.isChecked()){
                sexo = "Feminino";
            }else {
                sexo = "Masculino";
            }
            int imc = altura / peso;
            String personal = FirebaseAuth.getInstance().getCurrentUser().getEmail();

            ContentValues values = new ContentValues();
            values.put(PersonalDbHelper.C_USUARIO, nome);
            values.put(PersonalDbHelper.C_EMAIL,email);
            values.put(PersonalDbHelper.C_IDADE,Integer.valueOf(idade));
            values.put(PersonalDbHelper.C_ALTURA, Integer.valueOf(altura));
            values.put(PersonalDbHelper.C_PESO, Integer.valueOf(peso));
            values.put(PersonalDbHelper.C_SEXO, sexo);
            values.put(PersonalDbHelper.C_IMC, Integer.valueOf(imc));
            values.put(PersonalDbHelper.C_PERSONAL, personal);
            getContentResolver().insert(PersonalProvider.CONTENT_URI,values);

            db.close();

            Intent intent = new Intent(this, ListaAlunosActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), "Preencha o campo com email do aluno", Toast.LENGTH_SHORT);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.inserir_salvar_aluno:
                save();
                return true;
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void IniciaComponentes(){
        editTextemail = findViewById(R.id.editTextEmail);
        editTextNome = findViewById(R.id.editTextNome);
        editTextIdade = findViewById(R.id.editTextIdade);
        editTextAltura = findViewById(R.id.editTextAltura);
        editTextPeso = findViewById(R.id.editTextPeso);
        checkBoxFeminino = findViewById(R.id.checkBoxFeminino);
        checkBoxMasculino = findViewById(R.id.checkBoxMasculino);

    }
}
