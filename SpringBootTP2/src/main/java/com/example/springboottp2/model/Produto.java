package com.example.springboottp2.model;

public class Produto {
    private int id;
    private String nome;
    private String codigo;
    private float preco;

    public Produto(int id, String nome, String codigo, float preco) {
        this.id = id;
        this.nome = nome;
        this.codigo = codigo;
        this.preco = preco;
    }

    public Produto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }
}
