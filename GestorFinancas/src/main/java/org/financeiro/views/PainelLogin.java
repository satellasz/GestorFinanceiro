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

public class PainelLogin extends JPanel {
    private final transient BufferedImage image;

    public PainelLogin() {
        this.setLayout(null);
        this.image = ImageSingleton.getInstance().getImage("Login");

        CampoTexto campoTextoUsername = new CampoTexto(Color.WHITE, TipoInputComponente.LOGIN_USER, "Usuário: ", TipoCampoTexto.TEXTO, true);
        CampoTexto campoTextoSenha = new CampoTexto(Color.WHITE, TipoInputComponente.LOGIN_SENHA, "Senha:", TipoCampoTexto.TEXTO, true);

        Formulario formulario = new Formulario(450, 250, 400, 100);
        formulario.addComponente(campoTextoUsername);
        formulario.addComponente(campoTextoSenha);

        JButton botaoLogin = new JButton("Entrar");
        botaoLogin.addActionListener(new PostActionListener(new LoginController(), formulario));
        botaoLogin.setBounds(465, 350, 100, 40);

        JButton botaoCriarConta = new JButton("Criar Conta");
        botaoCriarConta.addActionListener(new GetActionListener(new CriarContaController()));
        botaoCriarConta.setBounds(730, 350, 100, 40);

        this.add(botaoLogin);
        this.add(botaoCriarConta);
        this.add(formulario.getPanel());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
