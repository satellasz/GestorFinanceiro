package org.financeiro.services;

import org.financeiro.componentes.AbstractInputComponente;
import org.financeiro.componentes.Formulario;
import org.financeiro.enums.TipoInputComponente;

public class FormularioServiceImpl implements FormularioService {
    @Override
    public String getInputComponente(TipoInputComponente id, Formulario formulario) {
        AbstractInputComponente inputComponente = formulario.getComponentes().stream().filter(x -> x.getNome().equals(id)).findFirst().orElse(null);
        return inputComponente != null ? inputComponente.getInput() : "";
    }
}
