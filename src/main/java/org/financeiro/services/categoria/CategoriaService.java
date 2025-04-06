package org.financeiro.services.categoria;

import org.financeiro.dtos.CategoriaDto;
import org.financeiro.dtos.TransacaoDto;
import org.financeiro.exceptions.DadoNaoEncontradoException;
import org.financeiro.exceptions.IntegridadeException;

import java.util.List;

public interface CategoriaService {
    void cadastrarCategoria(CategoriaDto categoria) throws DadoNaoEncontradoException, IntegridadeException;

    List<CategoriaDto> listarCategorias() throws DadoNaoEncontradoException;

    void excluirCategoria(int id, List<TransacaoDto> transacaoDtos) throws DadoNaoEncontradoException, IntegridadeException;

    void alterarCategoria(CategoriaDto categoria) throws DadoNaoEncontradoException;

    CategoriaDto buscarCategoria(String nome) throws DadoNaoEncontradoException;

    CategoriaDto buscarCategoria(int id) throws DadoNaoEncontradoException;

    int getIdProximaCategoria() throws DadoNaoEncontradoException;
}
