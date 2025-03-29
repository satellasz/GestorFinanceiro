package org.financeiro.enums;

import org.financeiro.exceptions.CampoInvalidoException;

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

    public static ClassificacaoTransacao buscarClassificacao(String nome) throws CampoInvalidoException {
        for (ClassificacaoTransacao classificacao : ClassificacaoTransacao.values()) {
            if (classificacao.getNome().equals(nome)) {
                return classificacao;
            }
        }
        throw new CampoInvalidoException("Classificação da transação não encontrada");
    }
}
