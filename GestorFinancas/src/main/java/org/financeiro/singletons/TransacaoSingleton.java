package org.financeiro.singletons;

import org.financeiro.models.Transacao;

import java.util.ArrayList;
import java.util.List;

public class TransacaoSingleton {
    private static TransacaoSingleton instance = null;
    private final List<Transacao> transacoes;

    private TransacaoSingleton() {
        this.transacoes = new ArrayList<>();
    }

    public static synchronized TransacaoSingleton getInstance() {
        if (instance == null) {
            instance = new TransacaoSingleton();
        }

        return instance;
    }

    public List<Transacao> getTransacoes() {
        return transacoes;
    }
}
