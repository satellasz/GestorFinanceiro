package org.financeiro.daos.categoria;

import org.financeiro.models.Categoria;

import java.util.List;

public interface CategoriaDAO {
    void cadastrarCategoria(Categoria Categoria);
    List<Categoria> listarCategorias();
    boolean excluirCategoria(long id);
    void alterarCategoria(Categoria categoria);
    Categoria buscarCategoria(String nome, long idUsuario);
    Categoria buscarCategoria(String nome);
    Categoria buscarCategoria(long id);
}
