package org.financeiro.views;

import javax.swing.*;
import java.awt.*;

public class FramePrincipal extends JFrame {
    private final Container conteinerConteudo = getContentPane();
    private final PainelPrincipal painelPrincipal = new PainelPrincipal();
    private final PainelLogin painelLogin = new PainelLogin();

    public void inicializar() {
        conteinerConteudo.add(painelLogin);

        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(new Dimension(1290, 760));

        this.setLocationRelativeTo(getOwner());
    }

    public Container getConteinerConteudo() {
        return conteinerConteudo;
    }

    public PainelPrincipal getPainelPrincipal() {
        return painelPrincipal;
    }
}
