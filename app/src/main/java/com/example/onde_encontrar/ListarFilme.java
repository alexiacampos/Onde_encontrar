package com.example.onde_encontrar;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListarFilme extends AppCompatActivity {
    //atributos
    private ListView listView;
    private FilmeDAO dao;
    private List<Filme> filme;
    private List<Filme> filmeFiltrados = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_filme);//abre a activity de listar filmes

        listView = findViewById(R.id.lista_filme);
        dao = new FilmeDAO(this);
        filme = dao.obterTodos();
        filmeFiltrados.addAll(filme);
        ArrayAdapter<Filme> adaptador = new ArrayAdapter<Filme>(this, android.R.layout.simple_list_item_1, filme);
        listView.setAdapter(adaptador);
    }
    public void adicionar_filme(View view){ //método para o clique no botão "adicionar" abrir a tela de cadastro
        Intent it = new Intent(this, CadastrarFilme.class);
        startActivity(it);
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {//cria mini menu para atualizar e excluir um registro
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_contexto, menu);
    }

    public void excluir(MenuItem item){
        AdapterView.AdapterContextMenuInfo menuInfo = //identificar posição do item clicado na lista
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final Filme filmeExcluir = filmeFiltrados.get(menuInfo.position);

        //confirmação da exclusão
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("ATENÇÃO!")
                .setMessage("Tem certeza que deseja excluir?")
                .setNegativeButton("Não", null)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        filmeFiltrados.remove(filmeExcluir);
                        filme.remove(filmeExcluir);
                        dao.excluir(filmeExcluir);
                        listView.invalidateViews();
                    }
                }).create();
        dialog.show();
    }

    public void atualizar(MenuItem item){
        AdapterView.AdapterContextMenuInfo menuInfo = //identificar posição do item clicado na lista
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final Filme filmeAtualizar = filmeFiltrados.get(menuInfo.position);
        Intent it = new Intent(this, CadastrarFilme.class);
        it.putExtra("filme", filmeAtualizar);
        startActivity(it);

    }
}