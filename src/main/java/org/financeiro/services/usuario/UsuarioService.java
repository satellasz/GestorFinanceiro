package org.financeiro.services.usuario;

import org.financeiro.dtos.UsuarioDto;
import org.financeiro.exceptions.CadastroContaException;
import org.financeiro.exceptions.DadoNaoEncontradoException;

import java.util.List;

public interface UsuarioService {
    void cadastrarUsuario(UsuarioDto usuario) throws CadastroContaException;

    List<UsuarioDto> listarUsuarios();

    UsuarioDto buscarUsuarioLogado();

    UsuarioDto buscarUsuario(int id) throws DadoNaoEncontradoException;

    UsuarioDto buscarUsuario(String nomeUsuario, String senha) throws DadoNaoEncontradoException;

    boolean validarSenhar(String senha, String confirmarSenha);

    int getIdProximoUsuario();

    void setUsuarioLogado(UsuarioDto usuario);
}
