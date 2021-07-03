package com.example.onde_encontrar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

public class SerieDAO {//classe responsável pelas interações com o banco de dados

    private Conexao conexao;
    private SQLiteDatabase banco;

    public SerieDAO(Context context){
        conexao = new Conexao(context);
        banco = conexao.getWritableDatabase();
    }

    public long inserir(Serie serie){
        ContentValues values = new ContentValues();
        values.put("titulo", serie.getTitulo());
        values.put("genero", serie.getGenero());
        values.put("duracao", 0);
        values.put("sinopse", serie.getSinopse());
        values.put("tipo", "S");
        return banco.insert("titulo", null, values );
    }

    public List<Serie> obterTodos(){
        List<Serie> serie = new ArrayList<>();
        Cursor cursor = banco.query("titulo", new String[]{"id", "titulo", "genero", "sinopse"},
                null, null,null,null,null);
        while (cursor.moveToNext()){
            Serie a = new Serie();
            a.setId(cursor.getInt(0));
            a.setTitulo(cursor.getString(1));
            a.setGenero(cursor.getString(2));
            a.setSinopse(cursor.getString(3));
            serie.add(a);
        }
        return serie;
    }

    public void excluir (Serie a){
        banco.delete("titulo", "id = ?", new String[]{a.getId().toString()});
    }

    public void atualizar(Serie serie){
        ContentValues values = new ContentValues();
        values.put("titulo", serie.getTitulo());
        values.put("genero", serie.getGenero());
        values.put("duracao", 0);
        values.put("sinopse", serie.getSinopse());
        values.put("tipo", "S");
        banco.update("titulo", values, "id = ?", new String[]{serie.getId().toString()});
    }
}
