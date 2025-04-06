package org.financeiro.views.paineisconteudo;

import org.financeiro.controllers.LoginController;
import org.financeiro.dtos.UsuarioDto;
import org.financeiro.listeners.GetActionListener;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class PainelPerfil extends AbstractPainelCentral {
    private final transient UsuarioDto usuario;

    public PainelPerfil(UsuarioDto usuarioDto) {
        this.usuario = usuarioDto;
    }

    @Override
    public void onLoad() {
        this.painelService.setProcessoEmAndamento(false);
        this.add(getPainelCima(), BorderLayout.NORTH);
        this.add(getPainelBaixo(), BorderLayout.SOUTH);
        customizarPainelBaixo();
        customizarPainelCima();
    }

    @Override
    protected void customizarPainelCima() {
        JPanel painelFoto = new JPanel(null);
        painelFoto.setBorder(BorderFactory.createLineBorder(Color.black, 2, true));
        painelFoto.setBounds(440, 0, 200, 200);

        this.painelCima.setLayout(null);
        this.painelCima.add(painelFoto);
    }

    @Override
    protected void customizarPainelBaixo() {
        JPanel painelInformacoes = new JPanel();

        painelInformacoes.add(getPainelInfo("Nome", usuario.nome()));
        painelInformacoes.add(getPainelInfo("Email", usuario.email()));

        this.painelBaixo.setLayout(new GridLayout(1, 2));
        this.painelBaixo.add(painelInformacoes);
        this.painelBaixo.add(getPainelBotaoLogout());
    }

    private JPanel getPainelInfo(String label, String info) {
        JPanel painelInfo = new JPanel(new BorderLayout(25, 5));

        JLabel labelDaInfo = new JLabel(label);

        JLabel informacao = new JLabel(info);
        informacao.setFont(informacao.getFont().deriveFont(30f));
        informacao.setBorder(getBorders());
        informacao.setPreferredSize(new Dimension(500, 75));

        painelInfo.add(labelDaInfo, BorderLayout.NORTH);
        painelInfo.add(informacao, BorderLayout.CENTER);

        return painelInfo;
    }

    private JPanel getPainelBotaoLogout() {
        JPanel painelBotaoLogout = new JPanel(null);

        JButton button = new JButton("Logout");
        button.setBounds(200, 300, 100, 50);

        button.addActionListener(new GetActionListener(new LoginController()));

        painelBotaoLogout.add(button);

        return painelBotaoLogout;
    }

    private Border getBorders() {
        Border innerBorder = BorderFactory.createLineBorder(Color.BLACK, 1, true);

        Border outerBorder = BorderFactory.createEmptyBorder(5, 15, 20, 0);

        return BorderFactory.createCompoundBorder(outerBorder, innerBorder);
    }
 }
