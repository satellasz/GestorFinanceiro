package org.financeiro.controllers;

import org.financeiro.componentes.Formulario;
import org.financeiro.enums.TipoInputComponente;
import org.financeiro.exceptions.CadastroContaException;
import org.financeiro.exceptions.CampoInvalidoException;
import org.financeiro.exceptions.CampoObrigatorioException;
import org.financeiro.views.PainelCriarConta;

import javax.swing.*;

public class CriarContaController extends AbstractController {
    @Override
    public void get() {
        this.painelService.setContainerConteudo(new PainelCriarConta());
    }

    @Override
    public void post(Formulario formulario) {
        try {
            this.validateCampos(formulario);

            String nome = this.formularioService.getInputComponente(TipoInputComponente.USUARIO_NOME, formulario);
            String email = this.formularioService.getInputComponente(TipoInputComponente.USUARIO_EMAIL, formulario);
            String senha = this.formularioService.getInputComponente(TipoInputComponente.USUARIO_SENHA, formulario);
            String confirmarSenha = this.formularioService.getInputComponente(TipoInputComponente.USUARIO_CONFIRMAR_SENHA, formulario);

            if (!this.usuarioService.validarSenhar(senha, confirmarSenha)) {
                throw new CadastroContaException("Senhas não são iguais!");
            }

            this.usuarioService.cadastrarUsuario(nome, email, senha);

            JOptionPane.showMessageDialog(null, "Cadastro feito com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

            new LoginController().get();
        } catch (CampoObrigatorioException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Campo obrigatório", JOptionPane.ERROR_MESSAGE);
        } catch (CampoInvalidoException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Campo inválido", JOptionPane.ERROR_MESSAGE);
        } catch (CadastroContaException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro no cadastro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
