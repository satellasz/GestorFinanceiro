package org.financeiro.services.usuario;

import org.financeiro.exceptions.CadastroContaException;
import org.financeiro.exceptions.DadoNaoEncontradoException;
import org.financeiro.models.Usuario;

import java.util.List;

public interface UsuarioService {
    void cadastrarUsuario(Usuario usuario) throws CadastroContaException;

    List<Usuario> listarUsuarios();

    void excluirUsuario(int id);

    void alterarUsuario(Usuario usuario);

    Usuario buscarUsuario(int id);

    Usuario buscarUsuarioLogado();

    Usuario buscarUsuario(Usuario usuario) throws DadoNaoEncontradoException;

    boolean validarSenhar(String senha, String confirmarSenha);

    int getIdUltimoUsuario();
}
