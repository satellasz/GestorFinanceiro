package org.financeiro.enums;

public enum ClassificacaoTransacao {
    RECEITA("Receita"),
    DESPESA("Despesa");

    private final String nome;

    ClassificacaoTransacao(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public static ClassificacaoTransacao buscarClassificacao(String nome) {
        for (ClassificacaoTransacao classificacao : ClassificacaoTransacao.values()) {
            if (classificacao.getNome().equals(nome)) {
                return classificacao;
            }
        }
        return null;
    }
}
