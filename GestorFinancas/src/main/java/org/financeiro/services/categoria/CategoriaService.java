package org.financeiro.services.categoria;

import org.financeiro.exceptions.DadoNaoEncontradoException;
import org.financeiro.exceptions.IntegridadeException;
import org.financeiro.models.Categoria;

import java.util.List;

public interface CategoriaService {
    void cadastrarCategoria(Categoria categoria);
    List<Categoria> listarCategorias();
    void excluirCategoria(int id) throws DadoNaoEncontradoException, IntegridadeException;
    void alterarCategoria(Categoria categoria) throws DadoNaoEncontradoException;
    Categoria buscarCategoria(String nome) throws DadoNaoEncontradoException;
    Categoria buscarCategoria(int id) throws DadoNaoEncontradoException;
    int getIdUltimaCategoria();
}
