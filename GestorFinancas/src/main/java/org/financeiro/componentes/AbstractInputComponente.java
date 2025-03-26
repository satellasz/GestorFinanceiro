package org.financeiro.componentes;

import org.financeiro.enums.TipoCampoTexto;
import org.financeiro.enums.TipoInputComponente;

import javax.swing.*;
import java.awt.*;

public abstract class AbstractInputComponente {
    protected static final int LARGURA_PADRAO_PAINEL = 100;
    protected static final int ALTURA_PADRAO_TEXT_FIELD = 25;
    protected static final int ALTURA_PADRAO_TEXT_AREA = 300;

    private final JPanel panel = new JPanel();
    protected TipoInputComponente tipoInputComponente;
    protected String labelCampo;
    protected boolean isObrigatorio;
    protected TipoCampoTexto tipoCampoTexto = TipoCampoTexto.TEXTO;

    protected AbstractInputComponente(TipoInputComponente tipoInputComponente, String labelCampo, TipoCampoTexto tipoCampoTexto, boolean isObrigatorio) {
        this.tipoInputComponente = tipoInputComponente;
        this.tipoCampoTexto = tipoCampoTexto;
        this.isObrigatorio = isObrigatorio;
        this.labelCampo = labelCampo;
    }

    protected AbstractInputComponente(TipoInputComponente tipoInputComponente, String labelCampo, boolean isObrigatorio) {
        this.tipoInputComponente = tipoInputComponente;
        this.isObrigatorio = isObrigatorio;
        this.labelCampo = labelCampo;
    }

    protected void setLayoutPadrao(int altura) {
        this.getPanel().setLayout(new BorderLayout(2, 10));
        this.getPanel().setPreferredSize(new Dimension(400, altura));
    }

    protected void addLabelCampo() {
        JLabel label = new JLabel(labelCampo);
        label.setPreferredSize(new Dimension(150, 25));
        this.getPanel().add(label, BorderLayout.WEST);
    }

    protected void addLabelObrigatorio() {
        JLabel labelObrigatorio = new JLabel("");

        if (isObrigatorio) {
            labelObrigatorio.setText("*");
            labelObrigatorio.setForeground(Color.red);
            labelObrigatorio.setToolTipText("Campo '" + labelCampo + "' é obrigatorio.");
        }

        this.getPanel().add(labelObrigatorio, BorderLayout.EAST);
    }


    public abstract String getInput();

    public abstract void addComponentePrincipal();

    public JPanel getPanel() {
        return panel;
    }

    public TipoInputComponente getNome() {
        return tipoInputComponente;
    }

    public boolean isObrigatorio() {
        return isObrigatorio;
    }

    public TipoCampoTexto getTipoCampoTexto() {
        return tipoCampoTexto;
    }

    public String getLabelCampo() {
        return labelCampo;
    }
}
