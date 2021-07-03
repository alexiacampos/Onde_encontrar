package com.example.onde_encontrar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;
import android.view.View;

public class ListarStreaming extends AppCompatActivity {
    private ListView listView;
    private StreamingDAO dao;
    private List<Streaming> streaming;
    private List<Streaming> streamingFiltrados = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_streaming);

       /* listView = findViewById(R.id.lista_streaming);
        dao = new StreamingDAO(this);
        streaming = dao.obterTodos();
        streamingFiltrados.addAll(streaming);
        ArrayAdapter<Streaming> adaptador = new ArrayAdapter<Streaming>(this, android.R.layout.simple_list_item_1, streaming);
        listView.setAdapter(adaptador);*/ //está travando o app
    }

    public void adicionar_streaming(View view){////método para o clique no botão "adicionar" abrir a tela de cadastro
        Intent it = new Intent(this, CadastrarStreaming.class);
        startActivity(it);
    }
}