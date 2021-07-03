package com.example.onde_encontrar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class FilmeDAO { //classe responsável pelas interações com o banco de dados

    private Conexao conexao;
    private SQLiteDatabase banco;

    public FilmeDAO(Context context){
        conexao = new Conexao(context);
        banco = conexao.getWritableDatabase();
    }

    public long inserir(Filme filme){
        ContentValues values = new ContentValues();
        values.put("titulo", filme.getTitulo());
        values.put("genero", filme.getGenero());
        values.put("duracao", filme.getDuracao());
        values.put("sinopse", filme.getSinopse());
        values.put("tipo", "F");
        return banco.insert("titulo", null, values );
    }

    public List<Filme> obterTodos(){
        List<Filme> filme = new ArrayList<>();
        Cursor cursor = banco.query("titulo", new String[]{"id", "titulo", "genero", "sinopse", "duracao"},
                null, null,null,null,null);
        while (cursor.moveToNext()){
            Filme a = new Filme();
            a.setId(cursor.getInt(0));
            a.setTitulo(cursor.getString(1));
            a.setGenero(cursor.getString(2));
            a.setSinopse(cursor.getString(3));
            a.setDuracao(cursor.getInt(4));
            filme.add(a);
        }
        return filme;
    }
    public void excluir (Filme a){
        banco.delete("titulo", "id = ?", new String[]{a.getId().toString()});
    }

    public void atualizar(Filme filme){
        ContentValues values = new ContentValues();
        values.put("titulo", filme.getTitulo());
        values.put("genero", filme.getGenero());
        values.put("duracao", filme.getDuracao());
        values.put("sinopse", filme.getSinopse());
        values.put("tipo", "F");
        banco.update("titulo", values, "id = ?", new String[]{filme.getId().toString()});
    }
}
