package com.example.onde_encontrar;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Conexao extends SQLiteOpenHelper { //classe criada para implementar o banco de dados

    private static final String name = "banco.db";
    private static final int version = 1;

    public Conexao(Context context) {
        super(context, name, null, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table titulo(id integer primary key autoincrement, titulo varchar(50), genero varchar(50), duracao integer, sinopse varchar(200), tipo varchar(1))");
        db.execSQL("create table streaming(id integer primary key autoincrement, nome varchar(50), preco real, ativo varchar(1))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
