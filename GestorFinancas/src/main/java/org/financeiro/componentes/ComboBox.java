package org.financeiro.componentes;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ComboBox extends AbstractInputComponente {
    private JComboBox<String> jComboBox;

    public ComboBox(String nome, boolean isObrigatorio) {
        this.isObrigatorio = isObrigatorio;
        this.nome = nome;

        setLayoutPadrao(ALTURA_PADRAO_TEXT_FIELD);

        addLabelCampo();

        addComponentePrincipal();

        addLabelObrigatorio();

    }

    @Override
    public String getInput() {
        if (this.jComboBox.getItemCount() <= 0) {
            return "";
        }
        if (this.jComboBox.getSelectedItem() == null) {
            return "";
        }
        return this.jComboBox.getSelectedItem().toString();
    }

    @Override
    public void addComponentePrincipal() {
        this.jComboBox = new JComboBox<>();
        this.jComboBox.setPreferredSize(new Dimension(LARGURA_PADRAO_PAINEL, ALTURA_PADRAO_TEXT_FIELD));

        this.jComboBox.setName(nome);
        this.jComboBox.setEditable(false);

        this.getPanel().add(this.jComboBox, BorderLayout.CENTER);
    }

    public void addValorComboBox(String valor) {
        this.jComboBox.addItem(valor);
    }
}
