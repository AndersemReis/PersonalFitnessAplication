package com.example.personafitnessapplication;

import androidx.annotation.NonNull;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.personafitnessapplication.adapter.AlunosAdapter;
import com.example.personafitnessapplication.model.Pessoa;

import java.util.ArrayList;
import java.util.List;

public class ListaAlunosActivity extends AppCompatActivity {
    private static final int LOADER_ID = 1;
    private AlunosAdapter alunosAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lista_alunos);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_personal);

    }

    @Override
    protected void onStart() {
        super.onStart();

        alunosAdapter = new AlunosAdapter(getApplicationContext(), R.layout.activity_main_itens,new ArrayList<Pessoa>());

        ListView listView = (ListView) findViewById(R.id.listViewAlunos);
        listView.setAdapter(alunosAdapter);
        listView.setOnItemClickListener(itemClickListener);
        listView.setOnItemLongClickListener(longClickListener);

        LoaderManager loaderManager = getSupportLoaderManager();
        loaderManager.initLoader(LOADER_ID,null,cursorLoaderCallbacks);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.alunos_menu,menu);
        MenuItem searcItem = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) searcItem.getActionView();
        searchView.setOnQueryTextListener(queryTextListener);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuAlunosAdicionar:
                startActivity(new Intent(this, InserirAlunoActivity.class));
                return true;
            case R.id.menuAlunosPerfil:
                startActivity(new Intent(this, PerfilActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        Bundle param = new Bundle();
        param.putString("filter", newText);

        LoaderManager lm = getSupportLoaderManager();
        lm.restartLoader(LOADER_ID, param,cursorLoaderCallbacks);
        return true;
    }
};
    LoaderManager.LoaderCallbacks<Cursor> cursorLoaderCallbacks = new LoaderManager.LoaderCallbacks<Cursor>() {
        @NonNull
        @Override
        public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
            if(args != null) {
                if (args.containsKey("filter")) {
                    String filter = args.getString("filter");

                    String selection = PersonalDbHelper.C_USUARIO + "like ? or " + PersonalDbHelper.C_EMAIL + " like ?";
                    String[] selecionArgs = {"%" + filter + "%", "%" + filter + "%"};
                    return new CursorLoader(getApplicationContext(), PersonalProvider.CONTENT_URI, null, selection, selecionArgs, null);

                }
            }
            return new CursorLoader(getApplicationContext(), PersonalProvider.CONTENT_URI, null,null,null,null);
        }

        @Override
        public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
            List<Pessoa> pessoaList = getList(data);
            alunosAdapter.setDados(pessoaList);

        }

        @Override
        public void onLoaderReset(@NonNull Loader<Cursor> loader) {
            alunosAdapter.setDados(new ArrayList<Pessoa>());

        }
    };
    private List<Pessoa> getList(Cursor cursor){
        List<Pessoa> pessoaList = new ArrayList<>();
        try {
            if (cursor.moveToFirst()){
                do {
                    Pessoa aluno = new Pessoa();
                    aluno.setId(cursor.getLong(0));
                    aluno.setNome(cursor.getString(1));
                    aluno.setEmail((cursor.getString(2)));
                    aluno.setSexo(cursor.getString(3));
                    aluno.setIdade(cursor.getInt(4));
                    aluno.setPeso(cursor.getInt(5));
                    aluno.setAltura(cursor.getInt(6));
                    aluno.setImc(cursor.getInt(7));
                    aluno.setPersonal(cursor.getString(8));

                    pessoaList.add(aluno);
                }while (cursor.moveToNext());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return pessoaList;
    }
    private AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(getBaseContext(), ListaExerciciosActivity.class);
            intent.putExtra("id", String.valueOf(id));
            startActivity(intent);
            return;
        }
    };

    private AdapterView.OnItemLongClickListener longClickListener = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
            Intent intent = new Intent(getBaseContext(), EditarAlunoActivity.class);
            intent.putExtra("id", String.valueOf(id));
            startActivity(intent);
            return true;
        }
    };
}