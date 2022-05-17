package com.example.personafitnessapplication;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class PersonalDbHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "personalfit.db";
    public static final int DB_VERSION = 6;
    public static final String TABLE = "pessoa";
    public static final String C_ID = "_id";
    public static final String C_USUARIO = "nome";
    public static final String C_EMAIL = "email";
    public static final String C_SEXO  = "sexo";
    public static final String C_IDADE = "idade";
    public static final String C_ALTURA = "altura";
    public static final String C_PESO= "peso";
    public static final String C_IMC = "imc";
    public static final String C_PERSONAL = "personal";
    public static final String TABLE1 = "exercicios";
    public static final String E_IDALUNO = "_idaluno";
    public static final String E_EXERC = "idexercicio";
    public static final String E_FREQUENCIA = "frequencia";
    public static final String E_REPETICAO = "repeticao";
    public static final String E_TEMPO = "tempo";
    public static final String E_MENS = "mensagem";
    public static final String TABLE2 = "tipoexercicios";
    public static final String T_ID = "_idtipoexer";
    public static final String T_NOME = "nomeexerc";
    public static final String T_TIPO = "tipoexerc";



    public PersonalDbHelper (Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE
                + " (" + C_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + C_USUARIO + " TEXT, "
                + C_EMAIL + " TEXT, "
                + C_SEXO + " TEXT, "
                + C_IDADE + " INTEGER, "
                + C_ALTURA + " REAL, "
                + C_PESO + " REAL, "
                + C_IMC + " REAL,"
                + C_PERSONAL + " TEXT )";

        String sqlExercicios = "CREATE TABLE " + TABLE1
                + " (" + E_IDALUNO + " INTEGER PRIMARY KEY, "
                + E_EXERC + " TEXT, "
                + E_FREQUENCIA + " TEXT, "
                + E_REPETICAO + " TEXT, "
                + E_TEMPO + " TEXT, "
                + E_MENS + " TEXT, "
                + "FOREIGN KEY ("+ E_IDALUNO + ") REFERENCES " + TABLE +" " + "(" + C_ID + "), "
                + "FOREIGN KEY (" + E_EXERC + ") REFERENCES " + TABLE2 + " " + "(" + T_TIPO + "))";

        String sqlTipoExercicios = "CREATE TABLE " + TABLE2
                + " (" + T_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + T_NOME + " TEXT, "
                + T_TIPO + " TEXT )";



        try {
            db.execSQL(sql);
            db.execSQL(sqlExercicios);
            db.execSQL(sqlTipoExercicios);
        }catch (Exception e){
            Log.e("Error dbHelper", e.getMessage());
        }

    }

    public void onOpen(SQLiteDatabase db){
        super.onOpen(db);
        if(!db.isReadOnly()){
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            backup(db);
            db.execSQL("drop table if exists " + TABLE);
            db.execSQL("drop table if exists " + TABLE1);
            db.execSQL("drop table if exists " + TABLE2);
            onCreate(db);
            restore(db);
        }catch (Exception e){
            Log.e("Error dbHelper", e.getMessage());
        }
    }
    private List<ContentValues> dados;
    private List<ContentValues> dadosexerc;
    private List<ContentValues> dadostipoexerc;

    private void backup(SQLiteDatabase db){
            dados = new ArrayList<ContentValues>();
            dadosexerc = new ArrayList<ContentValues>();
            dadostipoexerc = new ArrayList<ContentValues>();


            //criar backup da tabela de alunos
            try{
                Cursor cursor = db.query(TABLE,null,null,null,null,null,null);
                try {
                    while (cursor.moveToNext()){
                        String nome = cursor.getString(1);
                        String email = cursor.getString(2);
                        String sexo = cursor.getString(3);
                        String idade = cursor.getString(4);
                        String peso = cursor.getString(5);
                        String altura = cursor.getString(6);
                        String imc = cursor.getString(7);
                        String personal = cursor.getString(8);

                        ContentValues values = new ContentValues();

                        values.put(PersonalDbHelper.C_USUARIO, nome);
                        values.put(PersonalDbHelper.C_EMAIL, email);
                        values.put(PersonalDbHelper.C_SEXO, sexo);
                        values.put(PersonalDbHelper.C_IDADE,idade);
                        values.put(PersonalDbHelper.C_PESO, peso);
                        values.put(PersonalDbHelper.C_ALTURA, altura);
                        values.put(PersonalDbHelper.C_IMC, imc);
                        values.put(PersonalDbHelper.C_PERSONAL, personal);

                        dados.add(values);
                    }
                }finally {
                    cursor.close();
                }
                Log.i("DbHelper", "Backup do banco de dados realizaSSSdo");

            }catch (Exception e){
                Log.e("Error DbHelper", e.getMessage());
            }

        //criar backup da tabela de exercicios dos alunos
            try{
            Cursor cursor = db.query(TABLE1,null,null,null,null,null,null);
            try {
                while (cursor.moveToNext()){
                    String aluno = cursor.getString(0);
                    String exerc = cursor.getString(1);
                    String frequencia = cursor.getString(2);
                    String repeticao = cursor.getString(3);
                    String tempo = cursor.getString(4);
                    String mensagem = cursor.getString(5);

                    ContentValues values = new ContentValues();

                    values.put(PersonalDbHelper.E_IDALUNO, aluno);
                    values.put(PersonalDbHelper.E_EXERC, exerc);
                    values.put(PersonalDbHelper.E_FREQUENCIA, frequencia);
                    values.put(PersonalDbHelper.E_REPETICAO,repeticao);
                    values.put(PersonalDbHelper.E_TEMPO, tempo);
                    values.put(PersonalDbHelper.E_MENS,mensagem);


                    dadosexerc.add(values);
                }
            }finally {
                cursor.close();
            }
            Log.i("DbHelper", "Backup do banco de dados realizaSSSdo");

        }catch (Exception e){
            Log.e("Error DbHelper", e.getMessage());
        }

        //criar backup da tabela de exercicios
        try{
            Cursor cursor = db.query(TABLE2,null,null,null,null,null,null);
            try {
                while (cursor.moveToNext()){
                    String nome = cursor.getString(1);
                    String tipo = cursor.getString(2);

                    ContentValues values = new ContentValues();

                    values.put(PersonalDbHelper.T_NOME, nome);
                    values.put(PersonalDbHelper.T_TIPO, tipo);


                    dadostipoexerc.add(values);
                }
            }finally {
                cursor.close();
            }
            Log.i("DbHelper", "Backup do banco de dados realizaSSSdo");

        }catch (Exception e){
            Log.e("Error DbHelper", e.getMessage());
        }

    }
    public void restore(SQLiteDatabase db){

        // restaurar os alunos cadastrados
        try {
            for (ContentValues values : dados){
                db.insertOrThrow(PersonalDbHelper.TABLE, null,values);

            }
            Log.i("DbHelper", "Restore do banco de dados realizado!");

        }catch (Exception e){
            Log.e("Error Dbhelper", e.getMessage());
        }

        //restaurar tabela com os exercicios cadastrados para os alunos
        try {
            for (ContentValues values : dadosexerc){
                db.insertOrThrow(PersonalDbHelper.TABLE1, null,values);

            }
            Log.i("DbHelper", "Restore do banco de dados realizado!");

        }catch (Exception e){
            Log.e("Error Dbhelper", e.getMessage());
        }

        //restaurar tabela com os nomes do exercicios
        try {
            for (ContentValues values : dadostipoexerc){
                db.insertOrThrow(PersonalDbHelper.TABLE2, null,values);

            }
            Log.i("DbHelper", "Restore do banco de dados realizado!");

        }catch (Exception e){
            Log.e("Error Dbhelper", e.getMessage());
        }
    }


}
