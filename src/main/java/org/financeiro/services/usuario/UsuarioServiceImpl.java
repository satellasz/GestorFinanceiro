package org.financeiro.services.usuario;

import org.financeiro.dtos.UsuarioDto;
import org.financeiro.exceptions.CadastroContaException;
import org.financeiro.exceptions.DadoNaoEncontradoException;
import org.financeiro.models.Usuario;
import org.financeiro.repositories.usuario.UsuarioRepository;
import org.financeiro.repositories.usuario.UsuarioRepositoryImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioRepository usuarioRepository = new UsuarioRepositoryImpl();

    @Override
    public void cadastrarUsuario(UsuarioDto usuario) throws CadastroContaException {
        Usuario usuarioEncontrado = this.usuarioRepository.buscarUsuario(usuario.nome().trim());
        
        if (usuarioEncontrado != null) {
            throw new CadastroContaException("Já existe um usuário com este nome");
        }

        Usuario novoUsuario = new Usuario(this.getIdProximoUsuario() + 1, usuario.nome(), usuario.email(), usuario.senha());
        
        this.usuarioRepository.cadastrarUsuario(novoUsuario);
    }

    @Override
    public List<UsuarioDto> listarUsuarios() {
        List<UsuarioDto> usuarioDtos = new ArrayList<>();

        for (Usuario usuario : this.usuarioRepository.listarUsuarios()) {
            UsuarioDto usuarioDto = new UsuarioDto(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getSenha());
            usuarioDtos.add(usuarioDto);
        }

        return usuarioDtos;
    }

    @Override
    public UsuarioDto buscarUsuarioLogado() {
        Usuario usuario = this.usuarioRepository.buscarUsuarioLogado();

        return new UsuarioDto(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getSenha());
    }

    @Override
    public UsuarioDto buscarUsuario(int id) throws DadoNaoEncontradoException {
        Usuario usuario = this.usuarioRepository.buscarUsuario(id);

        if (usuario == null) {
            throw new DadoNaoEncontradoException("Usuário não foi encontrado");
        }

        return new UsuarioDto(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getSenha());
    }

    @Override
    public UsuarioDto buscarUsuario(String nomeUsuario, String senha) throws DadoNaoEncontradoException {
        Usuario usuarioEncontrado = this.usuarioRepository.buscarUsuario(nomeUsuario);

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
    public int getIdProximoUsuario() {
        List<UsuarioDto> usuarios = this.listarUsuarios();

        if (usuarios.isEmpty()) {
            return 1;
        }

        UsuarioDto ultimoUsuario = usuarios.getLast();

        return ultimoUsuario.id() + 1;
    }

    @Override
    public void setUsuarioLogado(UsuarioDto usuario) {
        if (usuario == null) {
            this.usuarioRepository.setUsuarioLogado(null);

            return;
        }

        Usuario usuarioEncontrado = this.usuarioRepository.buscarUsuario(usuario.id());

        this.usuarioRepository.setUsuarioLogado(usuarioEncontrado);
    }
}
