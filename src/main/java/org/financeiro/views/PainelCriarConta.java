package org.financeiro.views;

import org.financeiro.componentes.CampoTexto;
import org.financeiro.componentes.Formulario;
import org.financeiro.controllers.CriarContaController;
import org.financeiro.controllers.LoginController;
import org.financeiro.enums.TipoCampoTexto;
import org.financeiro.enums.TipoInputComponente;
import org.financeiro.listeners.GetActionListener;
import org.financeiro.listeners.PostActionListener;
import org.financeiro.singletons.ImageSingleton;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PainelCriarConta extends JPanel {
    private final transient BufferedImage image;

    public PainelCriarConta() {
        this.setLayout(null);
        this.image = ImageSingleton.getInstance().getImage("Login");

        Formulario formulario = getFormulario();

        JButton botaoLogin = new JButton("Voltar");
        botaoLogin.addActionListener(new GetActionListener(new LoginController()));
        botaoLogin.setBounds(465, 400, 100, 40);

        JButton botaoCriarConta = new JButton("Criar Conta");
        botaoCriarConta.addActionListener(new PostActionListener(new CriarContaController(), formulario));
        botaoCriarConta.setBounds(730, 400, 100, 40);

        this.add(botaoLogin);
        this.add(botaoCriarConta);
        this.add(formulario.getPanel());
    }

    private static Formulario getFormulario() {
        CampoTexto campoTextoUsername = new CampoTexto(Color.WHITE, TipoInputComponente.USUARIO_NOME, "Usuário: ", TipoCampoTexto.TEXTO, true);
        CampoTexto campoTextoEmail = new CampoTexto(Color.WHITE, TipoInputComponente.USUARIO_EMAIL, "Email:", TipoCampoTexto.TEXTO, true);
        CampoTexto campoTextoSenha = new CampoTexto(Color.WHITE, TipoInputComponente.USUARIO_SENHA, "Senha:", TipoCampoTexto.TEXTO, true);
        CampoTexto campoTextoConfirmarSenha = new CampoTexto(Color.WHITE, TipoInputComponente.USUARIO_CONFIRMAR_SENHA, "Confirmar senha:", TipoCampoTexto.TEXTO, true);

        Formulario formulario = new Formulario(450, 250, 400, 200);
        formulario.addComponente(campoTextoUsername);
        formulario.addComponente(campoTextoEmail);
        formulario.addComponente(campoTextoSenha);
        formulario.addComponente(campoTextoConfirmarSenha);
        return formulario;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
