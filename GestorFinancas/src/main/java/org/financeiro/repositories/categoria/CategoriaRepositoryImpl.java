package org.financeiro.repositories.categoria;

import org.financeiro.models.Categoria;
import org.financeiro.singletons.CategoriaSingleton;

import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

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
    public boolean excluirCategoria(int id) {
        return CategoriaSingleton.getInstance().getCategorias().removeIf(x -> x.getId() == id);
    }

    @Override
    public void alterarCategoria(Categoria categoria) {
        int index = CategoriaSingleton.getInstance().getCategorias().indexOf(categoria);

        CategoriaSingleton.getInstance().getCategorias().set(index, categoria);
    }

    @Override
    public Categoria buscarCategoria(String nome) {
        return CategoriaSingleton.getInstance().getCategorias().stream().filter(x -> Objects.equals(x.getNome(), nome)).findFirst().orElse(null);
    }

    @Override
    public Categoria buscarCategoria(int id) {
        return CategoriaSingleton.getInstance().getCategorias().stream().filter(x -> x.getId() == id).findFirst().orElse(null);
    }
}
