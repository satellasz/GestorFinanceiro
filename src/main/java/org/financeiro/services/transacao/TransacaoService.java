package org.financeiro.services.transacao;

import org.financeiro.dtos.CategoriaDto;
import org.financeiro.dtos.FiltroDto;
import org.financeiro.dtos.TransacaoDto;
import org.financeiro.dtos.UsuarioDto;
import org.financeiro.enums.ClassificacaoTransacao;
import org.financeiro.exceptions.CampoInvalidoException;
import org.financeiro.exceptions.DadoNaoEncontradoException;

import java.time.LocalDate;
import java.util.List;

public interface TransacaoService {
    void cadastrarTransacao(String descricao,
                            double valor,
                            CategoriaDto categoriaDto,
                            ClassificacaoTransacao classificacao,
                            UsuarioDto usuarioDto,
                            LocalDate dataTransacao) throws CampoInvalidoException, DadoNaoEncontradoException;

    List<TransacaoDto> listarTransacoes() throws DadoNaoEncontradoException;

    void excluirTransacao(long id) throws DadoNaoEncontradoException;

    void alterarTransacao(TransacaoDto transacao) throws DadoNaoEncontradoException, CampoInvalidoException;

    TransacaoDto buscarTransacao(long id) throws DadoNaoEncontradoException;

    List<TransacaoDto> getTransacoesFiltradas(FiltroDto filtroDto) throws DadoNaoEncontradoException;
}
