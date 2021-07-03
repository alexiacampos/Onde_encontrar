package com.example.onde_encontrar;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;
import android.view.View;

public class ListarSeries extends AppCompatActivity {
    private ListView listView;
    private SerieDAO dao;
    private List<Serie> serie;
    private List<Serie> serieFiltrados = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_series);

        listView = findViewById(R.id.lista_series);
        dao = new SerieDAO(this);
        serie = dao.obterTodos();
        serieFiltrados.addAll(serie);
        ArrayAdapter<Serie> adaptador = new ArrayAdapter<Serie>(this, android.R.layout.simple_list_item_1, serie);
        listView.setAdapter(adaptador);

        registerForContextMenu(listView);
    }
    public void adicionar_serie(View view){//método para o clique no botão "adicionar" abrir a tela de cadastro
        Intent it = new Intent(ListarSeries.this, CadastrarSerie.class);
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
        final Serie serieExcluir = serieFiltrados.get(menuInfo.position);

        //confirmação da exclusão
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("ATENÇÃO!")
                .setMessage("Tem certeza que deseja excluir?")
                .setNegativeButton("Não", null)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        serieFiltrados.remove(serieExcluir);
                        serie.remove(serieExcluir);
                        dao.excluir(serieExcluir);
                        listView.invalidateViews();
                    }
                }).create();
        dialog.show();
    }

    public void atualizar(MenuItem item){
        AdapterView.AdapterContextMenuInfo menuInfo = //identificar posição do item clicado na lista
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final Serie serieAtualizar = serieFiltrados.get(menuInfo.position);
        Intent it = new Intent(this, CadastrarSerie.class);
        it.putExtra("serie", serieAtualizar);
        startActivity(it);

    }
}