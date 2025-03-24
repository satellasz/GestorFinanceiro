package org.financeiro.componentes;

import org.financeiro.enums.TipoCampoTexto;

import javax.swing.*;
import java.awt.*;

public abstract class AbstractInputComponente {
    protected static final int LARGURA_PADRAO_PAINEL = 100;
    protected static final int ALTURA_PADRAO_TEXT_FIELD = 25;
    protected static final int ALTURA_PADRAO_TEXT_AREA = 300;

    private final JPanel panel = new JPanel();
    protected String nome = "";
    protected boolean isObrigatorio = false;
    protected TipoCampoTexto tipoCampoTexto = TipoCampoTexto.TEXTO;

    protected void setLayoutPadrao(int altura) {
        this.getPanel().setLayout(new BorderLayout(5, 10));
        this.getPanel().setPreferredSize(new Dimension(400, altura));
    }

    protected void addLabelCampo() {
        JLabel label = new JLabel(nome);
        label.setPreferredSize(new Dimension(100, 25));
        this.getPanel().add(label, BorderLayout.WEST);
    }

    protected void addLabelObrigatorio() {
        JLabel labelObrigatorio = new JLabel("");

        if (isObrigatorio) {
            labelObrigatorio.setText("*");
            labelObrigatorio.setForeground(Color.red);
            labelObrigatorio.setToolTipText("Campo '" + nome + "' é obrigatorio.");
        }

        this.getPanel().add(labelObrigatorio, BorderLayout.EAST);
    }


    public abstract String getInput();

    public abstract void addComponentePrincipal();

    public JPanel getPanel() {
        return panel;
    }

    public String getNome() {
        return nome;
    }

    public boolean isObrigatorio() {
        return isObrigatorio;
    }

    public TipoCampoTexto getTipoCampoTexto() {
        return tipoCampoTexto;
    }
}
