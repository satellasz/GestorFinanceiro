package org.financeiro.models;

import org.financeiro.enums.ClassificacaoTransacao;

import java.time.LocalDate;
import java.util.Objects;

public class Transacao {
    private final int id;
    private final String descricao;
    private final double valor;
    private final int idCategoria;
    private final ClassificacaoTransacao classificacao;
    private final int idUsuario;
    private final LocalDate dataTransacao;

    public Transacao(int id, double valor, String descricao, int idCategoria, ClassificacaoTransacao classificacao, int idUsuario, LocalDate dataTransacao) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.idCategoria = idCategoria;
        this.classificacao = classificacao;
        this.idUsuario = idUsuario;
        this.dataTransacao = dataTransacao;
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getValor() {
        return valor;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public ClassificacaoTransacao getClassificacao() {
        return classificacao;
    }

    public LocalDate getDataTransacao() {
        return dataTransacao;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Transacao that = (Transacao) obj;
        return this.id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
