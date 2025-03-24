package org.financeiro.componentes;

import org.financeiro.enums.TipoCampoTexto;

import javax.swing.*;
import java.awt.*;

public class CampoTexto extends AbstractInputComponente {
    private JTextField textField;

    public CampoTexto(String nome, TipoCampoTexto tipoCampoTexto, boolean isObrigatorio) {
        this.nome = nome;
        this.tipoCampoTexto = tipoCampoTexto;
        this.isObrigatorio = isObrigatorio;

        setLayoutPadrao(ALTURA_PADRAO_TEXT_FIELD);

        addLabelCampo();

        addComponentePrincipal();

        addLabelObrigatorio();
    }

    @Override
    public String getInput() {
        return textField.getText();
    }

    @Override
    public void addComponentePrincipal() {
        this.textField = new JTextField();
        this.textField.setName(nome);
        this.textField.setPreferredSize(new Dimension(LARGURA_PADRAO_PAINEL, ALTURA_PADRAO_TEXT_FIELD));

        this.getPanel().add(this.textField, BorderLayout.CENTER);
    }
}
