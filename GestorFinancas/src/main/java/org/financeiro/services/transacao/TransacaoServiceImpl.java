package org.financeiro.services.transacao;

import org.financeiro.exceptions.DadoNaoEncontradoException;
import org.financeiro.models.Transacao;
import org.financeiro.repositories.transacao.TransacaoRepository;
import org.financeiro.repositories.transacao.TransacaoRepositoryImpl;

import java.util.List;

public class TransacaoServiceImpl implements TransacaoService {
    private final TransacaoRepository transacaoRepository = new TransacaoRepositoryImpl();

    @Override
    public void cadastrarTransacao(Transacao transacao) {
        Transacao transacaoNova = new Transacao(getIdUltimaTransacao() + 1, transacao.getValor(), transacao.getDescricao(), transacao.getIdCategoria(), transacao.getClassificacao(), null, transacao.getDataTransacao());

        this.transacaoRepository.cadastrarTransacao(transacaoNova);
    }

    @Override
    public List<Transacao> listarTransacoes() {
        return this.transacaoRepository.listarTransacoes();
    }

    @Override
    public void excluirTransacao(int id) throws DadoNaoEncontradoException {
        if (id <= 0) {
            throw new DadoNaoEncontradoException("Transação não foi encontrada para remoção");
        }

        boolean sucesso = this.transacaoRepository.excluirTransacao(id);

        if (!sucesso) {
            throw new DadoNaoEncontradoException("Não foi possível remover a transação");
        }
    }

    @Override
    public void alterarTransacao(Transacao transacao) throws DadoNaoEncontradoException {
        if (transacao.getId() <= 0) {
            throw new DadoNaoEncontradoException("Transação não foi encontrada para edição");
        }

        this.transacaoRepository.alterarTransacao(transacao);
    }

    @Override
    public Transacao buscarTransacao(int id) {
        return this.transacaoRepository.buscarTransacao(id);
    }

    @Override
    public int getIdUltimaTransacao() {
        List<Transacao> transacoes = listarTransacoes();

        if (transacoes.isEmpty()) {
            return 0;
        }

        Transacao transacao = this.transacaoRepository.listarTransacoes().getLast();

        return transacao.getId();
    }

    @Override
    public boolean transacaoContemCategoria(int idCategoria) {
        return this.transacaoRepository.listarTransacoes().stream().anyMatch(x -> x.getIdCategoria() == idCategoria);
    }
}
