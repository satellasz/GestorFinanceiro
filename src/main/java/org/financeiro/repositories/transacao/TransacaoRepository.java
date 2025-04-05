package org.financeiro.repositories.transacao;

import org.financeiro.models.Transacao;

import java.util.List;

public interface TransacaoRepository {
    void cadastrarTransacao(Transacao transacao);

    List<Transacao> listarTransacoes();

    boolean excluirTransacao(int id);

    void alterarTransacao(Transacao transacao);

    Transacao buscarTransacao(int id);
}
