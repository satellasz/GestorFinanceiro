package org.financeiro.componentes;

import org.financeiro.enums.TipoCampoTexto;

import javax.swing.*;
import java.awt.*;

public class CampoTextoArea extends AbstractInputComponente {
    private JTextArea textArea;

    public CampoTextoArea(String nome, TipoCampoTexto tipoCampoTexto, boolean isObrigatorio) {
        this.nome = nome;
        this.tipoCampoTexto = tipoCampoTexto;
        this.isObrigatorio = isObrigatorio;

        setLayoutPadrao(ALTURA_PADRAO_TEXT_AREA);

        addLabelCampo();

        addComponentePrincipal();

        addLabelObrigatorio();
    }

    @Override
    public String getInput() {
        return this.textArea.getText();
    }

    @Override
    public void addComponentePrincipal() {
        this.textArea = new JTextArea();
        this.textArea.setPreferredSize(new Dimension(LARGURA_PADRAO_PAINEL, ALTURA_PADRAO_TEXT_AREA));
        this.textArea.setName(nome);

        this.getPanel().add(this.textArea, BorderLayout.CENTER);
    }
}
