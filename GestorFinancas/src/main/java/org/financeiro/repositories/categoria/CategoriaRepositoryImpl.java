package org.financeiro.repositories.categoria;

import org.financeiro.models.Categoria;
import org.financeiro.singletons.CategoriaSingleton;

import java.util.List;

public class CategoriaRepositoryImpl implements CategoriaRepository {
    @Override
    public void cadastrarCategoria(Categoria categoria) {
        CategoriaSingleton.getInstance().getCategorias().add(categoria);
    }

    @Override
    public List<Categoria> listarCategorias() {
        return CategoriaSingleton.getInstance().getCategorias();
    }

    @Override
    public void excluirCategoria(int id) {
        CategoriaSingleton.getInstance().getCategorias().removeIf(x -> x.getId() == id);
    }

    @Override
    public void alterarCategoria(Categoria categoria) {
        //CategoriaSingleton.getInstance().getCategorias().set(categoria.getId(), categoria);
    }

    @Override
    public Categoria buscarCategoria(String nome) {
        return CategoriaSingleton.getInstance().getCategorias().stream().filter(x -> x.getNome() == nome).findFirst().orElse(null);
    }
}
