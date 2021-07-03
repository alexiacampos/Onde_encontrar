package com.example.onde_encontrar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class Primeira_tela extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primeira_tela);//abre a activity da primeira tela do app
    }

    public boolean onCreateOptionsMenu (Menu menu) { //infla o menu criado
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_principal, menu);
        return true;
    }

    public void serie(MenuItem item) {//implementa a ação de clique na opção do menu e leva pra tela que lista as séries
        Intent it = new Intent(this, ListarSeries.class);
        startActivity(it);
    }
    public void filme(MenuItem item) {//implementa a ação de clique na opção do menu e leva pra tela que lista os filmes
        //Intent it = new Intent(this, ListarFilme.class);
        Intent it = new Intent(this, ListarFilme.class);
        startActivity(it);
    }
    public void streaming(MenuItem item){//implementa a ação de clique na opção do menu e leva pra tela que lista os streamings
       Intent it = new Intent(this, ListarStreaming.class);
       startActivity(it);
    }
    /*public void adicionar_serie(View view){
        Intent it = new Intent(this, CadastrarSerie.class);
        startActivity(it);*/
    }
