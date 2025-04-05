package org.financeiro.services.transacao;

import org.financeiro.dtos.CategoriaDto;
import org.financeiro.dtos.FiltroDto;
import org.financeiro.dtos.TransacaoDto;
import org.financeiro.exceptions.CampoInvalidoException;
import org.financeiro.exceptions.DadoNaoEncontradoException;

import java.util.List;

public interface TransacaoService {
    void cadastrarTransacao(TransacaoDto transacao) throws CampoInvalidoException, DadoNaoEncontradoException;

    List<TransacaoDto> listarTransacoes() throws DadoNaoEncontradoException;

    void excluirTransacao(int id) throws DadoNaoEncontradoException;

    void alterarTransacao(TransacaoDto transacao) throws DadoNaoEncontradoException, CampoInvalidoException;

    TransacaoDto buscarTransacao(int id) throws DadoNaoEncontradoException;

    int getIdProximaTransacao() throws DadoNaoEncontradoException;

    List<TransacaoDto> getTransacoesFiltradas(FiltroDto filtroDto) throws DadoNaoEncontradoException;
}
