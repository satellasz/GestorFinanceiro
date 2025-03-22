package org.financeiro.enums;

public enum ClassificacaoTransacao {
    RECEITA("Receita"),
    DESPESA("Despesa");

    private String nome;

    private ClassificacaoTransacao(String nome) {
        this.nome = nome;
    }
}
