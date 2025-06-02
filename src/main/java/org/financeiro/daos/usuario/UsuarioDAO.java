package org.financeiro.daos.usuario;

import org.financeiro.models.Usuario;

import java.util.List;

public interface UsuarioDAO {
    void cadastrarUsuario(Usuario usuario);

    List<Usuario> listarUsuarios();

    void excluirUsuario(long id);

    void alterarUsuario(Usuario usuario);

    Usuario buscarUsuario(long id);

    Usuario buscarUsuarioLogado();

    Usuario buscarUsuario(String login);

    void setUsuarioLogado(Usuario usuario);
}
