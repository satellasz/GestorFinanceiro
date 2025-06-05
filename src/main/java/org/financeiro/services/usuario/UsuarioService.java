package org.financeiro.services.usuario;

import org.financeiro.dtos.UsuarioDto;
import org.financeiro.exceptions.CadastroContaException;
import org.financeiro.exceptions.DadoNaoEncontradoException;
import org.financeiro.models.Usuario;

import java.util.List;

public interface UsuarioService {
    void cadastrarUsuario(String nome, String email, String senha) throws CadastroContaException;

    List<UsuarioDto> listarUsuarios();

    UsuarioDto buscarUsuarioLogado();

    UsuarioDto buscarUsuarioDto(long id) throws DadoNaoEncontradoException;

    UsuarioDto buscarUsuarioDto(String nomeUsuario, String senha) throws DadoNaoEncontradoException;

    Usuario buscarUsuario(long id) throws DadoNaoEncontradoException;

    boolean validarSenhar(String senha, String confirmarSenha);

    void setUsuarioLogado(UsuarioDto usuario);
}
