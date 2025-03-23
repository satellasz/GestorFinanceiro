package org.financeiro.controllers;

import org.financeiro.componentes.Formulario;
import org.financeiro.enums.TipoPainelTransicao;
import org.financeiro.views.paineisconteudo.PainelListaTransacoes;

public class TransacoesController extends Controller {

    @Override
    public void get() {
        painelService.setPainelConteudo(new PainelListaTransacoes());
        painelService.setBorderPainelTransicao(painelService.getPainelTransicao(TipoPainelTransicao.TRANSACOES));
    }

    @Override
    public void post(Formulario formulario) {
        get();
    }
}
