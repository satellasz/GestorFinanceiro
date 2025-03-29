package org.financeiro.componentes;

import org.financeiro.enums.TipoInputComponente;
import org.financeiro.exceptions.CampoInvalidoException;
import org.financeiro.utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class CampoData extends AbstractInputComponente {
    private JSpinner dateSpinner;

    public CampoData(TipoInputComponente tipoInputComponente, String labelCampo, boolean isObrigatorio) {
        super(tipoInputComponente, labelCampo, isObrigatorio);

        setLayoutPadrao(ALTURA_PADRAO_TEXT_FIELD);

        addLabelCampo(Color.BLACK);

        addComponentePrincipal();

        addLabelObrigatorio();
    }

    public CampoData(Color colorLabel, TipoInputComponente tipoInputComponente, String labelCampo, boolean isObrigatorio) {
        super(tipoInputComponente, labelCampo, isObrigatorio);

        setLayoutPadrao(ALTURA_PADRAO_TEXT_FIELD);

        addLabelCampo(colorLabel);

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
        try {
            LocalDate localDate = Utils.getData(input);
            Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            this.dateSpinner.setValue(date);
        } catch (CampoInvalidoException e) {
            this.dateSpinner.setValue(null);
        }
    }
}
