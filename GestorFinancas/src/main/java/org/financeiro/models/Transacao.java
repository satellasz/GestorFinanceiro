package org.financeiro.models;

import org.financeiro.enums.ClassificacaoTransacao;

import java.time.LocalDate;
import java.util.Objects;

public class Transacao {
    private int id;
    private String descricao;
    private double valor;
    private int idCategoria;
    private ClassificacaoTransacao classificacao;
    private Usuario usuario;
    private String dataTransacao;

    public Transacao(double valor, String descricao, int idCategoria, ClassificacaoTransacao classificacao, Usuario usuario, String dataTransacao) {
        this.descricao = descricao;
        this.valor = valor;
        this.idCategoria = idCategoria;
        this.classificacao = classificacao;
        this.usuario = usuario;
        this.dataTransacao = dataTransacao;
    }

    public Transacao(int id, double valor, String descricao, int idCategoria, ClassificacaoTransacao classificacao, Usuario usuario, String dataTransacao) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.idCategoria = idCategoria;
        this.classificacao = classificacao;
        this.usuario = usuario;
        this.dataTransacao = dataTransacao;
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public ClassificacaoTransacao getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(ClassificacaoTransacao classificacao) {
        this.classificacao = classificacao;
    }

    public String getDataTransacao() {
        return dataTransacao;
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
