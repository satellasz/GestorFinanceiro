package org.financeiro.models;

import java.util.Objects;

public class Categoria {
    private int id;
    private String nome;
    private String descricao;
    private Usuario usuario;

    public Categoria(String nome, String descricao, Usuario usuario) {
        this.nome = nome;
        this.descricao = descricao;
        this.usuario = usuario;
    }

    public Categoria(int id, String nome, String descricao, Usuario usuario) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.usuario = usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Categoria that = (Categoria) obj;
        return this.id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
