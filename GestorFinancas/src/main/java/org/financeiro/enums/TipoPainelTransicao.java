package org.financeiro.enums;

public enum TipoPainelTransicao {
    TRANSACOES("Transações", "/icons/transacoes.png"),
    CATEGORIAS("Categorias", "/icons/category.png"),
    RESUMO("Resumo", "/icons/resumo.png"),
    PERFIL("Perfil", "");

    private final String nome;
    private final String pathImagem;

    TipoPainelTransicao(String nome, String pathImagem) {
        this.nome = nome;
        this.pathImagem = pathImagem;
    }

    public String getNome() {
        return nome;
    }

    public String getPathImagem() {
        return pathImagem;
    }
}
