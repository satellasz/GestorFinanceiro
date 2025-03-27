package org.financeiro.componentes;

import org.financeiro.enums.TipoCampoTexto;
import org.financeiro.enums.TipoInputComponente;

import javax.swing.*;
import java.awt.*;

public class CampoTexto extends AbstractInputComponente {
    private JTextField textField;

    public CampoTexto(TipoInputComponente tipoInputComponente, String label, TipoCampoTexto tipoCampoTexto, boolean isObrigatorio) {
        super(tipoInputComponente, label, tipoCampoTexto, isObrigatorio);

        setLayoutPadrao(ALTURA_PADRAO_TEXT_FIELD);

        addLabelCampo();

        addComponentePrincipal();

        addLabelObrigatorio();
    }

    @Override
    public String getInput() {
        return this.textField.getText();
    }

    @Override
    public void addComponentePrincipal() {
        this.textField = new JTextField();
        this.textField.setName(tipoInputComponente.getId());
        this.textField.setPreferredSize(new Dimension(LARGURA_PADRAO_PAINEL, ALTURA_PADRAO_TEXT_FIELD));

        this.getPanel().add(this.textField, BorderLayout.CENTER);
    }

    @Override
    public void setInput(String input) {
        this.textField.setText(input);
    }
}
