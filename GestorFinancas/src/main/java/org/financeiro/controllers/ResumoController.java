package org.financeiro.controllers;

import org.financeiro.componentes.Formulario;
import org.financeiro.enums.TipoPainelMenu;
import org.financeiro.views.paineisconteudo.PainelResumoFinanceiro;

public class ResumoController extends AbstractController {
    @Override
    public void get() {
        painelService.setPainelConteudo(new PainelResumoFinanceiro());
        painelService.setBorderPainelTransicao(painelService.getPainelMenu(TipoPainelMenu.RESUMO));
    }

    @Override
    public void post(Formulario formulario) {
        get();
    }
}
