package org.financeiro.services.transacao;

import org.financeiro.dtos.FiltroDto;
import org.financeiro.exceptions.CampoInvalidoException;
import org.financeiro.exceptions.DadoNaoEncontradoException;
import org.financeiro.models.Categoria;
import org.financeiro.models.Transacao;

import java.time.LocalDate;
import java.util.List;

public interface TransacaoService {
    void cadastrarTransacao(Transacao transacao) throws CampoInvalidoException;
    List<Transacao> listarTransacoes();
    void excluirTransacao(int id) throws DadoNaoEncontradoException;
    void alterarTransacao(Transacao transacao) throws DadoNaoEncontradoException, CampoInvalidoException;
    Transacao buscarTransacao(int id);
    int getIdUltimaTransacao();
    boolean transacaoContemCategoria(int idCategoria);
    List<Transacao> getTransacoesFiltradas(FiltroDto filtroDto);
}
