package org.financeiro.repositories.usuario;

import org.financeiro.models.Usuario;

import java.util.List;

public interface UsuarioRepository {
    void cadastrarUsuario(Usuario usuario);
    List<Usuario> listarUsuarios();
    void excluirUsuario(int id);
    void alterarUsuario(Usuario usuario);
    Usuario buscarUsuario(int id);
    Usuario buscarUsuarioLogado();
    Usuario buscarUsuario(String login);
    void setUsuarioLogado(Usuario usuario);
}
