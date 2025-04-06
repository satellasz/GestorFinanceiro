package org.financeiro.controllers;

import org.financeiro.componentes.Formulario;
import org.financeiro.dtos.UsuarioDto;
import org.financeiro.enums.TipoInputComponente;
import org.financeiro.exceptions.CampoInvalidoException;
import org.financeiro.exceptions.CampoObrigatorioException;
import org.financeiro.exceptions.DadoNaoEncontradoException;
import org.financeiro.singletons.SwingSingleton;
import org.financeiro.views.PainelLogin;

import javax.swing.*;

public class LoginController extends AbstractController {
    @Override
    public void get() {
        this.loginService.logout();
        this.painelService.setPainelMenuAtual(null);
        painelService.setContainerConteudo(SwingSingleton.getInstance().getPainelPrincipal());
        painelService.setContainerConteudo(new PainelLogin());
    }

    @Override
    public void post(Formulario formulario) {
        try {
            this.validateCampos(formulario);

            String nome = this.formularioService.getInputComponente(TipoInputComponente.LOGIN_USER, formulario);
            String senha = this.formularioService.getInputComponente(TipoInputComponente.LOGIN_SENHA, formulario);

            UsuarioDto usuario = this.usuarioService.buscarUsuario(nome, senha);

            this.loginService.login(usuario);

            painelService.setContainerConteudo(SwingSingleton.getInstance().getPainelPrincipal());
            new TransacoesController().get();
        } catch (CampoObrigatorioException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Campo obrigatório", JOptionPane.ERROR_MESSAGE);
        } catch (DadoNaoEncontradoException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Não encontrado", JOptionPane.ERROR_MESSAGE);
        } catch (CampoInvalidoException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Campo inválido", JOptionPane.ERROR_MESSAGE);
        }
    }
}
