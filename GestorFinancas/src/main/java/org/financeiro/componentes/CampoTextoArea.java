package org.financeiro.componentes;

import org.financeiro.enums.TipoCampoTexto;
import org.financeiro.enums.TipoInputComponente;

import javax.swing.*;
import java.awt.*;

public class CampoTextoArea extends AbstractInputComponente {
    private JTextArea textArea;

    public CampoTextoArea(TipoInputComponente tipoInputComponente, String label, TipoCampoTexto tipoCampoTexto, boolean isObrigatorio) {
        super(tipoInputComponente, label, tipoCampoTexto, isObrigatorio);

        setLayoutPadrao(ALTURA_PADRAO_TEXT_AREA);

        addLabelCampo(Color.BLACK);

        addComponentePrincipal();

        addLabelObrigatorio();
    }

    public CampoTextoArea(Color colorLabel, TipoInputComponente tipoInputComponente, String label, TipoCampoTexto tipoCampoTexto, boolean isObrigatorio) {
        super(tipoInputComponente, label, tipoCampoTexto, isObrigatorio);

        setLayoutPadrao(ALTURA_PADRAO_TEXT_AREA);

        addLabelCampo(colorLabel);

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
        this.textArea.setName(tipoInputComponente.getId());
        this.textArea.setLineWrap(true);

        this.getPanel().add(this.textArea, BorderLayout.CENTER);
    }

    @Override
    public void setInput(String input) {
        this.textArea.setText(input);
    }
}
