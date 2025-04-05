package org.financeiro.repositories.usuario;

import org.financeiro.models.Usuario;
import org.financeiro.singletons.UsuarioSingleton;

import java.util.List;

public class UsuarioRepositoryImpl implements UsuarioRepository {
    @Override
    public void cadastrarUsuario(Usuario usuario) {
        UsuarioSingleton.getInstance().getUsuarios().add(usuario);
    }

    @Override
    public List<Usuario> listarUsuarios() {
        return UsuarioSingleton.getInstance().getUsuarios();
    }

    @Override
    public void excluirUsuario(int id) {
        UsuarioSingleton.getInstance().getUsuarios().removeIf(x -> x.getId() == id);
    }

    @Override
    public void alterarUsuario(Usuario usuario) {
        UsuarioSingleton.getInstance().getUsuarios().set(usuario.getId(), usuario);
    }

    @Override
    public Usuario buscarUsuario(int id) {
        return UsuarioSingleton.getInstance().getUsuarios().stream().filter(x -> x.getId() == id).findFirst().orElse(null);
    }

    @Override
    public Usuario buscarUsuarioLogado() {
        return UsuarioSingleton.getInstance().getUsuarioLogado();
    }

    @Override
    public Usuario buscarUsuario(String login) {
        return UsuarioSingleton.getInstance().getUsuarios().stream().filter(x -> x.getNome().equals(login)).findFirst().orElse(null);
    }

    @Override
    public void setUsuarioLogado(Usuario usuario) {
        UsuarioSingleton.getInstance().setUsuarioLogado(usuario);
    }
}
