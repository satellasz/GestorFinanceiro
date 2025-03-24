package org.financeiro.repositories.categoria;

import org.financeiro.models.Categoria;

import java.util.List;

public interface CategoriaRepository {
    void cadastrarCategoria(Categoria Categoria);
    List<Categoria> listarCategorias();
    void excluirCategoria(String nome);
    void alterarCategoria(Categoria categoria);
    Categoria buscarCategoria(String nome);
}
