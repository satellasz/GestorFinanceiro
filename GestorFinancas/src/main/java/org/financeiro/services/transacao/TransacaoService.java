package org.financeiro.services.transacao;

import org.financeiro.exceptions.DadoNaoEncontradoException;
import org.financeiro.models.Transacao;

import java.util.List;

public interface TransacaoService {
    void cadastrarTransacao(Transacao transacao);
    List<Transacao> listarTransacoes();
    void excluirTransacao(int id) throws DadoNaoEncontradoException;
    void alterarTransacao(Transacao transacao) throws DadoNaoEncontradoException;
    Transacao buscarTransacao(int id);
    int getIdUltimaTransacao();
    boolean transacaoContemCategoria(int idCategoria);
}
