package org.financeiro.componentes;

import org.financeiro.enums.TipoInputComponente;

import javax.swing.*;
import java.awt.*;

public class ComboBox extends AbstractInputComponente {
    private JComboBox<String> jComboBox;

    public ComboBox(TipoInputComponente tipoInputComponente, String label, boolean isObrigatorio) {
        super(tipoInputComponente, label, isObrigatorio);

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
        this.jComboBox.setBackground(Color.WHITE);

        this.jComboBox.setName(tipoInputComponente.getId());
        this.jComboBox.setEditable(false);

        this.getPanel().add(this.jComboBox, BorderLayout.CENTER);
    }

    public void addValorComboBox(String valor) {
        this.jComboBox.addItem(valor);
    }
}
