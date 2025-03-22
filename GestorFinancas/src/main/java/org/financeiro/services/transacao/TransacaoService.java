package org.financeiro.services.transacao;

import org.financeiro.models.Transacao;

import java.util.List;

public interface TransacaoService {
    void cadastrarTransacao(Transacao transacao);
    List<Transacao> listarTransacoes();
    void excluirTransacao(int id);
    void alterarTransacao(Transacao transacao);
    Transacao buscarTransacao(int id);
}
