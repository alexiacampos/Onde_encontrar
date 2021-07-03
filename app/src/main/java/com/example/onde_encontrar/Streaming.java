package com.example.onde_encontrar;

import android.widget.Switch;

import androidx.annotation.NonNull;
import java.io.Serializable;

public class Streaming implements Serializable { //classe com todos os atributos e métodos referentes aos streamings
    private Integer id;
    private String nome;
    private Double preco;
    private String ativo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getAtivo() {
        return ativo;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }
}
