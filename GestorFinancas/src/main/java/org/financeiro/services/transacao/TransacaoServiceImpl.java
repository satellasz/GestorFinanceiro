package org.financeiro.services.transacao;

import org.financeiro.models.Transacao;
import org.financeiro.repositories.transacao.TransacaoRepository;

import java.util.List;

public class TransacaoServiceImpl implements TransacaoService {
    private final TransacaoRepository transacaoRepository;

    public TransacaoServiceImpl(TransacaoRepository transacaoRepository) {
        this.transacaoRepository = transacaoRepository;
    }

    @Override
    public void cadastrarTransacao(Transacao transacao) {
        transacaoRepository.cadastrarTransacao(transacao);
    }

    @Override
    public List<Transacao> listarTransacoes() {
        return transacaoRepository.listarTransacoes();
    }

    @Override
    public void excluirTransacao(int id) {
        transacaoRepository.excluirTransacao(id);
    }

    @Override
    public void alterarTransacao(Transacao transacao) {
        transacaoRepository.alterarTransacao(transacao);
    }

    @Override
    public Transacao buscarTransacao(int id) {
        return transacaoRepository.buscarTransacao(id);
    }
}
