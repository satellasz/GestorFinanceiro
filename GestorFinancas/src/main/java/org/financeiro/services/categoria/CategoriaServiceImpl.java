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
        Categoria categoriaNova = new Categoria(getIdUltimaCategoria() + 1, categoria.getNome(), categoria.getDescricao(), categoria.getUsuario());

        categoriaRepository.cadastrarCategoria(categoriaNova);
    }

    @Override
    public List<Categoria> listarCategorias() {
        return categoriaRepository.listarCategorias();
    }

    @Override
    public void excluirCategoria(int id) {
        categoriaRepository.excluirCategoria(id);
    }

    @Override
    public void alterarCategoria(Categoria categoria) {
        categoriaRepository.alterarCategoria(categoria);
    }

    @Override
    public Categoria buscarCategoria(String nome) {
        return categoriaRepository.buscarCategoria(nome);
    }

    @Override
    public int getIdUltimaCategoria() {
        List<Categoria> categorias = listarCategorias();

        if (categorias.isEmpty()) {
            return 0;
        }

        Categoria categoria = categoriaRepository.listarCategorias().getLast();

        return categoria.getId();
    }
}
