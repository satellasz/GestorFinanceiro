package org.financeiro.componentes;

import org.financeiro.enums.TipoInputComponente;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        Date date = (Date) dateSpinner.getValue();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(date);
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

    @Override
    public void setInput(String input) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date = sdf.parse(input);
            dateSpinner.setValue(date);
        } catch (ParseException e) {
            System.out.println("Erro ao converter a data: " + e.getMessage());
        }
    }
}
