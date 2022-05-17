package com.example.personafitnessapplication.activity;

import android.content.Intent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;

import com.example.personafitnessapplication.FormCadastroActivity;
import com.example.personafitnessapplication.ListaAlunosActivity;
import com.example.personafitnessapplication.PerfilActivity;
import com.example.personafitnessapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;


public class MainActivity extends AppCompatActivity {

    private EditText editTextUser, editTextSenha;
    private TextView text_tela_cadastro;
    private Button btnLogin;
    private ProgressBar progressBar;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String[] mensagens = {"Preencha todos os campos"};


       @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();
        IniciarComponente();


        text_tela_cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FormCadastroActivity.class);
                startActivity(intent);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextUser.getText().toString();
                String senha = editTextSenha.getText().toString();

                if (email.isEmpty() || senha.isEmpty()){
                    Snackbar snackbar = Snackbar.make(v, mensagens[0],Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();

                }else{
                    AutenticarUsuario(v);

                }
            }
        });

    }

    private void AutenticarUsuario(View view){
        String email = editTextUser.getText().toString();
        String senha = editTextSenha.getText().toString();

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    progressBar.setVisibility(View.VISIBLE);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            TelaPrincipal();

                        }
                    },3000);
                }else{
                    String erro;
                    try {
                        throw task.getException();

                    }catch (Exception e){
                        erro = "Erro ao logar usuario";
                    }
                    Snackbar snackbar = Snackbar.make(view, erro,Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser usuarioAtual = FirebaseAuth.getInstance().getCurrentUser();
        if (usuarioAtual != null){
            TelaPrincipal();
        }
    }

    private void TelaPrincipal(){


        String usuarioID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DocumentReference documentReference = db.collection("Usuarios").document(usuarioID);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {

                if (documentSnapshot != null){
                    String usuario = documentSnapshot.getString("tipo");
                    if( usuario.equals("Personal")) {
                        Intent intent = new Intent(MainActivity.this, ListaAlunosActivity.class);
                        startActivity(intent);
                        finish();
                    }else{
                        Intent intent = new Intent(MainActivity.this, PerfilActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        });

}

    private void IniciarComponente(){
        text_tela_cadastro = findViewById(R.id.txt_tela_cadastrar);
        editTextUser = (EditText) findViewById(R.id.editTextEmail);
        editTextSenha = (EditText) findViewById(R.id.editTextSenha);
        btnLogin = findViewById(R.id.btnLogin);
        progressBar = findViewById(R.id.progress_bar);

    }


}