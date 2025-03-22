package org.financeiro.models;

import org.financeiro.enums.ClassificacaoTransacao;

import java.util.Date;

public class Transacao {
    private int id;
    private String descricao;
    private double valor;
    private Categoria categoria;
    private ClassificacaoTransacao classificacao;
    private Usuario usuario;
    private Date dataTransacao;

    public Transacao(int id, String descricao, double valor, Categoria categoria, ClassificacaoTransacao classificacao, Usuario usuario, Date dataTransacao) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.categoria = categoria;
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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public ClassificacaoTransacao getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(ClassificacaoTransacao classificacao) {
        this.classificacao = classificacao;
    }
}
