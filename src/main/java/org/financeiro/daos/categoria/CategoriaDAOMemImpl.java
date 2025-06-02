package org.financeiro.daos.categoria;

import org.financeiro.models.Categoria;
import org.financeiro.services.usuario.UsuarioService;
import org.financeiro.services.usuario.UsuarioServiceImpl;
import org.financeiro.singletons.CategoriaSingleton;

import java.util.List;
import java.util.Objects;

public class CategoriaDAOMemImpl implements CategoriaDAO {
    private final UsuarioService usuarioService = new UsuarioServiceImpl();

    @Override
    public void cadastrarCategoria(Categoria categoria) {
        categoria.setId(this.getIdProximaCategoria());
        CategoriaSingleton.getInstance().getCategorias().add(categoria);
    }

    @Override
    public List<Categoria> listarCategorias() {
        return CategoriaSingleton.getInstance().getCategorias().stream().filter(x -> x.getUsuario().getId() == this.usuarioService.buscarUsuarioLogado().id()).toList();
    }

    @Override
    public boolean excluirCategoria(long id) {
        return CategoriaSingleton.getInstance().getCategorias().removeIf(x -> x.getId() == id);
    }

    @Override
    public void alterarCategoria(Categoria categoria) {
        int index = this.listarCategorias().indexOf(categoria);

        CategoriaSingleton.getInstance().getCategorias().set(index, categoria);
    }

    @Override
    public Categoria buscarCategoria(String nome) {
        return CategoriaSingleton.getInstance().getCategorias().stream().filter(x -> Objects.equals(x.getNome(), nome) && x.getUsuario().getId() == this.usuarioService.buscarUsuarioLogado().id()).findFirst().orElse(null);
    }

    @Override
    public Categoria buscarCategoria(long id) {
        return CategoriaSingleton.getInstance().getCategorias().stream().filter(x -> x.getId() == id && x.getUsuario().getId() == this.usuarioService.buscarUsuarioLogado().id()).findFirst().orElse(null);
    }

    public long getIdProximaCategoria() {
        List<Categoria> categorias = this.listarCategorias();

        if (categorias.isEmpty()) {
            return 1;
        }

        Categoria categoria = categorias.getLast();

        return categoria.getId() + 1L;
    }
}
