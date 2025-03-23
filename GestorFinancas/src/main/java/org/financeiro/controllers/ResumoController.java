package org.financeiro.controllers;

import org.financeiro.componentes.Formulario;
import org.financeiro.enums.TipoPainelTransicao;
import org.financeiro.views.paineisconteudo.PainelResumoFinanceiro;

public class ResumoController extends Controller {
    public ResumoController() {
        super();
    }

    @Override
    public void get() {
        painelService.setPainelConteudo(new PainelResumoFinanceiro());
        painelService.setBorderPainelTransicao(painelService.getPainelTransicao(TipoPainelTransicao.RESUMO));
    }

    @Override
    public void post(Formulario formulario) {
        get();
    }
}
