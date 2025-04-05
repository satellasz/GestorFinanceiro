package org.financeiro.enums;

public enum TipoColunaTabela {
    EDITAR("Editar"),
    EXCLUIR("Excluir");

    private final String nome;

    TipoColunaTabela(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
