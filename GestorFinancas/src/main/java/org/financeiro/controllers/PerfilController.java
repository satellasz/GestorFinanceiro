package org.financeiro.controllers;

import org.financeiro.componentes.Formulario;
import org.financeiro.enums.TipoPainelMenu;
import org.financeiro.views.paineisconteudo.PainelPerfil;

public class PerfilController extends AbstractController {
    @Override
    public void get() {
        painelService.setPainelConteudo(new PainelPerfil());
        painelService.setBorderPainelTransicao(painelService.getPainelMenu(TipoPainelMenu.PERFIL));
    }

    @Override
    public void post(Formulario formulario) {
        get();
    }
}
