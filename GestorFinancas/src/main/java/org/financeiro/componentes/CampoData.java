package org.financeiro.componentes;

import org.financeiro.enums.TipoInputComponente;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class CampoData extends AbstractInputComponente {
    private  JSpinner dateSpinner;
    public CampoData(TipoInputComponente tipoInputComponente, String labelCampo, boolean isObrigatorio) {
        super(tipoInputComponente, labelCampo, isObrigatorio);

        setLayoutPadrao(ALTURA_PADRAO_TEXT_FIELD);

        addLabelCampo();

        addComponentePrincipal();

        addLabelObrigatorio();
    }

    @Override
    public String getInput() {
        return dateSpinner.getValue().toString();
    }

    @Override
    public void addComponentePrincipal() {
        SpinnerDateModel dateModel = new SpinnerDateModel();
        this.dateSpinner = new JSpinner(dateModel);
        this.dateSpinner.setName(tipoInputComponente.getId());
        this.dateSpinner.setValue(new Date());

        JSpinner.DateEditor editor = new JSpinner.DateEditor(this.dateSpinner, "dd/MM/yyyy");
        this.dateSpinner.setEditor(editor);

        this.dateSpinner.setPreferredSize(new Dimension(LARGURA_PADRAO_PAINEL, ALTURA_PADRAO_TEXT_FIELD));

        this.getPanel().add(this.dateSpinner, BorderLayout.CENTER);
    }
}
