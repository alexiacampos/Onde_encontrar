package com.example.onde_encontrar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CadastrarSerie extends AppCompatActivity {
    //atributos
    private EditText titulo;
    private EditText genero;
    private EditText sinopse;
    private SerieDAO dao;
    private Serie serie = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_serie);//abre a activity da tela de cadastrar séries

        //atribui o conteúdo dos elementos da tela aos atributos
        titulo = findViewById(R.id.editar_titulo);
        genero = findViewById(R.id.editar_genero);
        sinopse = findViewById(R.id.editar_sinopse);
        dao = new SerieDAO(this);

        Intent it = getIntent();
        if (it.hasExtra("serie")) {
            serie = (Serie) it.getSerializableExtra("serie");
            titulo.setText(serie.getTitulo());
            genero.setText(serie.getGenero());
            sinopse.setText(serie.getSinopse());
        }
    }

    //método para salvar no banco de dados
    public void salvar(View view) {

        if (serie == null) {
            serie = new Serie();
            serie.setTitulo(titulo.getText().toString());
            serie.setGenero(genero.getText().toString());
            serie.setSinopse((sinopse.getText().toString()));
            long id = dao.inserir(serie);
            Toast.makeText(this, "Serie inserido com id: " + id, Toast.LENGTH_LONG).show();
        } else {
            serie.setTitulo(titulo.getText().toString());
            serie.setGenero(genero.getText().toString());
            serie.setSinopse((sinopse.getText().toString()));
            dao.atualizar(serie);
            Toast.makeText(this, "Serie foi atualizada!", Toast.LENGTH_LONG).show();
        }
        Intent it = new Intent(CadastrarSerie.this, ListarSeries.class);
        startActivity(it);
    }
}
