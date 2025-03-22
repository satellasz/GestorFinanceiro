package org.financeiro.services.usuario;

import org.financeiro.models.Usuario;
import org.financeiro.models.Usuario;
import org.financeiro.repositories.usuario.UsuarioRepository;

import java.util.List;

public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void cadastrarUsuario(Usuario usuario) {
        usuarioRepository.cadastrarUsuario(usuario);
    }

    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.listarUsuarios();
    }

    @Override
    public void excluirUsuario(int id) {
        usuarioRepository.excluirUsuario(id);
    }

    @Override
    public void alterarUsuario(Usuario usuario) {
        usuarioRepository.alterarUsuario(usuario);
    }

    @Override
    public Usuario buscarUsuario(int id) {
        return usuarioRepository.buscarUsuario(id);
    }

    @Override
    public Usuario buscarUsuarioLogado() {
        return usuarioRepository.buscarUsuarioLogado();
    }
}
