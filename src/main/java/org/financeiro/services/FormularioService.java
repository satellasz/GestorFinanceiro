package org.financeiro.services;

import org.financeiro.componentes.Formulario;
import org.financeiro.enums.TipoInputComponente;

public interface FormularioService {
    String getInputComponente(TipoInputComponente id, Formulario formulario);
}
