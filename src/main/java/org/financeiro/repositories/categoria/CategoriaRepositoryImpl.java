package org.financeiro.repositories.categoria;

import org.financeiro.models.Categoria;
import org.financeiro.services.usuario.UsuarioService;
import org.financeiro.services.usuario.UsuarioServiceImpl;
import org.financeiro.singletons.CategoriaSingleton;

import java.util.List;
import java.util.Objects;

public class CategoriaRepositoryImpl implements CategoriaRepository {
    private final UsuarioService usuarioService = new UsuarioServiceImpl();

    @Override
    public void cadastrarCategoria(Categoria categoria) {
        CategoriaSingleton.getInstance().getCategorias().add(categoria);
    }

    @Override
    public List<Categoria> listarCategorias() {
        return CategoriaSingleton.getInstance().getCategorias().stream().filter(x -> x.getIdUsuario() == this.usuarioService.buscarUsuarioLogado().id()).toList();
    }

    @Override
    public boolean excluirCategoria(int id) {
        return CategoriaSingleton.getInstance().getCategorias().removeIf(x -> x.getId() == id);
    }

    @Override
    public void alterarCategoria(Categoria categoria) {
        int index = this.listarCategorias().indexOf(categoria);

        CategoriaSingleton.getInstance().getCategorias().set(index, categoria);
    }

    @Override
    public Categoria buscarCategoria(String nome) {
        return CategoriaSingleton.getInstance().getCategorias().stream().filter(x -> Objects.equals(x.getNome(), nome) && x.getIdUsuario() == this.usuarioService.buscarUsuarioLogado().id()).findFirst().orElse(null);
    }

    @Override
    public Categoria buscarCategoria(int id) {
        return CategoriaSingleton.getInstance().getCategorias().stream().filter(x -> x.getId() == id && x.getIdUsuario() == this.usuarioService.buscarUsuarioLogado().id()).findFirst().orElse(null);
    }
}
