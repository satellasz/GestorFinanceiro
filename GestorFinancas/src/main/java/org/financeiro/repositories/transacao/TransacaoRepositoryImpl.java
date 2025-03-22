package org.financeiro.repositories.transacao;

import org.financeiro.models.Transacao;
import org.financeiro.singletons.TransacaoSingleton;

import java.util.List;

public class TransacaoRepositoryImpl implements TransacaoRepository {
    @Override
    public void cadastrarTransacao(Transacao transacao) {
        TransacaoSingleton.getInstance().getTransacoes().add(transacao);
    }

    @Override
    public List<Transacao> listarTransacoes() {
        return TransacaoSingleton.getInstance().getTransacoes();
    }

    @Override
    public void excluirTransacao(int id) {
        TransacaoSingleton.getInstance().getTransacoes().removeIf(x -> x.getId() == id);
    }

    @Override
    public void alterarTransacao(Transacao transacao) {
        TransacaoSingleton.getInstance().getTransacoes().set(transacao.getId(), transacao);
    }

    @Override
    public Transacao buscarTransacao(int id) {
        return TransacaoSingleton.getInstance().getTransacoes().stream().filter(x -> x.getId() == id).findFirst().orElse(null);
    }
}
