package org.financeiro.daos.transacao;

import org.financeiro.models.Transacao;

import java.util.List;

public interface TransacaoDAO {
    void cadastrarTransacao(Transacao transacao);

    List<Transacao> listarTransacoes();

    boolean excluirTransacao(long id);

    void alterarTransacao(Transacao transacao);

    Transacao buscarTransacao(long id);
}
