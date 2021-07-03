package com.example.onde_encontrar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

public class StreamingDAO { //classe responsável pelas interações com o banco de dados

    private Conexao conexao;
    private SQLiteDatabase banco;

    public StreamingDAO(Context context){
        conexao = new Conexao(context);
        banco = conexao.getWritableDatabase();
    }

    public long inserir(Streaming streaming){
        ContentValues values = new ContentValues();
        values.put("nome", streaming.getNome());
        values.put("preco", streaming.getPreco());
        values.put("ativo", streaming.getAtivo());
        return banco.insert("streaming", null, values);
    }

    public List<Streaming> obterTodos(){
        List<Streaming> streaming = new ArrayList<>();
        Cursor cursor = banco.query("streaming", new String[]{"id", "nome", "preco", "ativo"},
                null, null,null,null,null);
        while (cursor.moveToNext()){
            Streaming a = new Streaming();
            a.setId(cursor.getInt(0));
            a.setNome(cursor.getString(1));
            a.setPreco(cursor.getDouble(2));
            a.setAtivo(cursor.getString(3));
            streaming.add(a);
        }
        return streaming;
    }
}

