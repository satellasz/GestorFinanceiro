package org.financeiro.services.categoria;

import org.financeiro.models.Categoria;
import org.financeiro.repositories.categoria.CategoriaRepository;

import java.util.List;

public class CategoriaServiceImpl implements CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public void cadastrarCategoria(Categoria categoria) {
        categoriaRepository.cadastrarCategoria(categoria);
    }

    @Override
    public List<Categoria> listarCategorias() {
        return categoriaRepository.listarCategorias();
    }

    @Override
    public void excluirCategoria(String nome) {
        categoriaRepository.excluirCategoria(nome);
    }

    @Override
    public void alterarCategoria(Categoria categoria) {
        categoriaRepository.alterarCategoria(categoria);
    }

    @Override
    public Categoria buscarCategoria(String nome) {
        return categoriaRepository.buscarCategoria(nome);
    }
}
