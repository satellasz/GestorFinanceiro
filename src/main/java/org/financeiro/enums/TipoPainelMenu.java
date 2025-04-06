package org.financeiro.enums;

public enum TipoPainelMenu {
    TRANSACOES("Transações"),
    CATEGORIAS("Categorias"),
    RESUMO("Resumo"),
    PERFIL("Perfil");

    private final String nome;

    TipoPainelMenu(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
