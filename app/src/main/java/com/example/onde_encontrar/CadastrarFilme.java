package com.example.onde_encontrar;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CadastrarFilme extends AppCompatActivity {
    //atributos
    private EditText titulo;
    private EditText genero;
    private EditText sinopse;
    private EditText duracao;
    private FilmeDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_filme);//abre a activity de cadastro de filmes

        //atribui o conteúdo dos elementos da tela aos atributos
        titulo = findViewById(R.id.editar_titulo);
        genero = findViewById(R.id.editar_genero);
        sinopse = findViewById(R.id.editar_sinopse);
        duracao = findViewById(R.id.editar_duracao);
        dao = new FilmeDAO(this);
    }

    public void salvar (View view){ //método para salvar os dados no banco
        Filme a = new Filme();
        a.setTitulo(titulo.getText().toString());
        a.setGenero(genero.getText().toString());
        a.setSinopse(sinopse.getText().toString());
        a.setDuracao(Integer.valueOf(duracao.getText().toString()));
        long id = dao.inserir(a);
        Toast.makeText(this, "Filme inserido com id: " + id, Toast.LENGTH_LONG).show();
    }
}
