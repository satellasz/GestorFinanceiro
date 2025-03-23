package org.financeiro.controllers;

import org.financeiro.componentes.Formulario;
import org.financeiro.services.PainelService;
import org.financeiro.services.PainelServiceImpl;

public abstract class Controller {
    protected final PainelService painelService = new PainelServiceImpl();
    public abstract void get();
    public abstract void post(Formulario formulario);

}
