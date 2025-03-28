package org.financeiro.services.usuario;

import org.financeiro.exceptions.DadoNaoEncontradoException;
import org.financeiro.models.Usuario;
import org.financeiro.repositories.usuario.UsuarioRepository;
import org.financeiro.repositories.usuario.UsuarioRepositoryImpl;

import java.util.List;
import java.util.Objects;

public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioRepository usuarioRepository = new UsuarioRepositoryImpl();

    @Override
    public void cadastrarUsuario(Usuario usuario) {
        this.usuarioRepository.cadastrarUsuario(usuario);
    }

    @Override
    public List<Usuario> listarUsuarios() {
        return this.usuarioRepository.listarUsuarios();
    }

    @Override
    public void excluirUsuario(int id) {
        this.usuarioRepository.excluirUsuario(id);
    }

    @Override
    public void alterarUsuario(Usuario usuario) {
        this.usuarioRepository.alterarUsuario(usuario);
    }

    @Override
    public Usuario buscarUsuario(int id) {
        return this.usuarioRepository.buscarUsuario(id);
    }

    @Override
    public Usuario buscarUsuarioLogado() {
        return this.usuarioRepository.buscarUsuarioLogado();
    }

    @Override
    public Usuario buscarUsuario(Usuario usuario) throws DadoNaoEncontradoException {
        Usuario usuarioEncontrado = this.usuarioRepository.buscarUsuario(usuario.getNome());

        if (usuarioEncontrado == null) {
            throw new DadoNaoEncontradoException("Usuário não foi encontrado");
        }

        if (!Objects.equals(usuarioEncontrado.getSenha(), usuario.getSenha())) {
            throw new DadoNaoEncontradoException("Senha incorreta");
        }

        return usuarioEncontrado;
    }
}
