package org.financeiro.services.categoria;

import org.financeiro.dtos.CategoriaDto;
import org.financeiro.dtos.TransacaoDto;
import org.financeiro.dtos.UsuarioDto;
import org.financeiro.exceptions.DadoNaoEncontradoException;
import org.financeiro.exceptions.IntegridadeException;
import org.financeiro.models.Categoria;

import java.util.List;

public interface CategoriaService {
    void cadastrarCategoria(String nome,
                            String descricao,
                            UsuarioDto usuarioDto) throws DadoNaoEncontradoException, IntegridadeException;

    List<CategoriaDto> listarCategorias() throws DadoNaoEncontradoException;

    void excluirCategoria(long id,
                          List<TransacaoDto> transacaoDtos) throws DadoNaoEncontradoException, IntegridadeException;

    void alterarCategoria(CategoriaDto categoria) throws DadoNaoEncontradoException;

    CategoriaDto buscarCategoriaDto(String nome) throws DadoNaoEncontradoException;

    CategoriaDto buscarCategoriaDto(long id) throws DadoNaoEncontradoException;

    Categoria buscarCategoria(long id) throws DadoNaoEncontradoException;
}
