package com.example.onde_encontrar;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import android.os.Bundle;

public class CadastrarStreaming extends AppCompatActivity {
    //atributos
    private EditText nome;
    private EditText preco;
    private Switch ativo;
    private StreamingDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_streaming);//abre a activity da tela de cadastrar streamings

        //atribui o conteúdo dos elementos da tela nos atributos
        nome = findViewById(R.id.editar_nome);
        preco = findViewById(R.id.editar_preco);
        ativo = findViewById(R.id.switch_ativo);
        dao = new StreamingDAO(this);
    }

    public void salvar (View view){ //método para salvar no banco de dados
        Streaming a = new Streaming();
        a.setNome(nome.getText().toString());
        a.setPreco(Double.valueOf(preco.getText().toString()));
        if(ativo.isChecked()){
            a.setAtivo("S");
        } else {
            a.setAtivo("N");
        }

        long id = dao.inserir(a);
        Toast.makeText(this, "Streaming inserido com id: " + id, Toast.LENGTH_LONG).show();
    }
}