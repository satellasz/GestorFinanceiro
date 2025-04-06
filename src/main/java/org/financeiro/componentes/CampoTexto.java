package org.financeiro.componentes;

import org.financeiro.enums.TipoCampoTexto;
import org.financeiro.enums.TipoInputComponente;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CampoTexto extends AbstractInputComponente {
    private JTextField textField;

    public CampoTexto(TipoInputComponente tipoInputComponente, String label, TipoCampoTexto tipoCampoTexto, boolean isObrigatorio) {
        super(tipoInputComponente, label, tipoCampoTexto, isObrigatorio);

        setLayoutPadrao(ALTURA_PADRAO_TEXT_FIELD);

        addLabelCampo(Color.BLACK);

        addComponentePrincipal();

        addLabelObrigatorio();
    }

    public CampoTexto(Color colorLabel, TipoInputComponente tipoInputComponente, String label, TipoCampoTexto tipoCampoTexto, boolean isObrigatorio) {
        super(tipoInputComponente, label, tipoCampoTexto, isObrigatorio);

        setLayoutPadrao(ALTURA_PADRAO_TEXT_FIELD);

        addLabelCampo(colorLabel);

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
        this.textField.setBorder(new EmptyBorder(0, 5, 0, 0));
        this.textField.setFont(this.textField.getFont().deriveFont(15f));

        this.getPanel().add(this.textField, BorderLayout.CENTER);
    }

    @Override
    public void setInput(String input) {
        this.textField.setText(input);
    }
}
