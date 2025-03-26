package org.financeiro.controllers;

import org.financeiro.componentes.Formulario;
import org.financeiro.enums.TipoPainelMenu;
import org.financeiro.views.paineisconteudo.PainelListaTransacoes;

public class TransacoesController extends AbstractController {

    @Override
    public void get() {
        painelService.setPainelConteudo(new PainelListaTransacoes());
        painelService.setBorderPainelTransicao(painelService.getPainelTransicao(TipoPainelMenu.TRANSACOES));
    }

    @Override
    public void post(Formulario formulario) {
        get();
    }
}
