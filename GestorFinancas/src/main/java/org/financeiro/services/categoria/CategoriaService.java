package org.financeiro.services.categoria;

import org.financeiro.models.Categoria;

import java.util.List;

public interface CategoriaService {
    void cadastrarCategoria(Categoria categoria);
    List<Categoria> listarCategorias();
    void excluirCategoria(int id);
    void alterarCategoria(Categoria categoria);
    Categoria buscarCategoria(String nome);
    int getIdUltimaCategoria();
}
