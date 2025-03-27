package org.financeiro.controllers;

import org.financeiro.componentes.Formulario;
import org.financeiro.singletons.SwingSingleton;

public class LoginController extends AbstractController {
    @Override
    public void get() {
        painelService.setContainerConteudo(SwingSingleton.getInstance().getPainelPrincipal());

        new TransacoesController().get();
    }

    @Override
    public void post(Formulario formulario) {

    }
}
