package org.financeiro.services.usuario;

import org.financeiro.dtos.UsuarioDto;
import org.financeiro.exceptions.CadastroContaException;
import org.financeiro.exceptions.DadoNaoEncontradoException;
import org.financeiro.models.Usuario;
import org.financeiro.daos.usuario.UsuarioDAO;
import org.financeiro.daos.usuario.UsuarioDAOBancoImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioDAO usuarioDAO = new UsuarioDAOBancoImpl();

    @Override
    public void cadastrarUsuario(String nome, String email, String senha) throws CadastroContaException {
        Usuario usuarioEncontrado = this.usuarioDAO.buscarUsuario(nome.trim());
        
        if (usuarioEncontrado != null) {
            throw new CadastroContaException("Já existe um usuário com este nome");
        }

        Usuario novoUsuario = new Usuario(nome, email, senha);
        
        this.usuarioDAO.cadastrarUsuario(novoUsuario);
    }

    @Override
    public List<UsuarioDto> listarUsuarios() {
        List<UsuarioDto> usuarioDtos = new ArrayList<>();

        for (Usuario usuario : this.usuarioDAO.listarUsuarios()) {
            UsuarioDto usuarioDto = new UsuarioDto(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getSenha());
            usuarioDtos.add(usuarioDto);
        }

        return usuarioDtos;
    }

    @Override
    public UsuarioDto buscarUsuarioLogado() {
        Usuario usuario = this.usuarioDAO.buscarUsuarioLogado();

        return new UsuarioDto(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getSenha());
    }

    @Override
    public Usuario buscarUsuario(long id) throws DadoNaoEncontradoException {
        Usuario usuario = this.usuarioDAO.buscarUsuario(id);

        if (usuario == null) {
            throw new DadoNaoEncontradoException("Usuário não foi encontrado");
        }

        return new Usuario(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getSenha());
    }

    @Override
    public UsuarioDto buscarUsuarioDto(long id) throws DadoNaoEncontradoException {
        Usuario usuario = this.usuarioDAO.buscarUsuario(id);

        if (usuario == null) {
            throw new DadoNaoEncontradoException("Usuário não foi encontrado");
        }

        return new UsuarioDto(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getSenha());
    }

    @Override
    public UsuarioDto buscarUsuarioDto(String nomeUsuario, String senha) throws DadoNaoEncontradoException {
        Usuario usuarioEncontrado = this.usuarioDAO.buscarUsuario(nomeUsuario);

        if (usuarioEncontrado == null || !Objects.equals(usuarioEncontrado.getSenha(), senha)) {
            throw new DadoNaoEncontradoException("Usuário não encontrado ou senha incorreta");
        }

        return new UsuarioDto(usuarioEncontrado.getId(), usuarioEncontrado.getNome(), usuarioEncontrado.getEmail(), senha);
    }

    @Override
    public boolean validarSenhar(String senha, String confirmarSenha) {
        return senha.trim().equals(confirmarSenha.trim());
    }

    @Override
    public void setUsuarioLogado(UsuarioDto usuario) {
        if (usuario == null) {
            this.usuarioDAO.setUsuarioLogado(null);

            return;
        }

        Usuario usuarioEncontrado = this.usuarioDAO.buscarUsuario(usuario.id());

        this.usuarioDAO.setUsuarioLogado(usuarioEncontrado);
    }
}
