package org.financeiro.models;

import java.util.Objects;

public class Categoria {
    private final int id;
    private final String nome;
    private final String descricao;
    private final int idUsuario;

    public Categoria(int id, String nome, String descricao, int idUsuario) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getId() {
        return id;
    }

    public int getIdUsuario() {
        return idUsuario;
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
