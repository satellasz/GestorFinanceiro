package org.financeiro.controllers;

import org.financeiro.componentes.Formulario;
import org.financeiro.enums.TipoPainelTransicao;
import org.financeiro.views.paineisconteudo.PainelListaCategorias;

public class CategoriasController extends AbstractController {

    @Override
    public void get() {
        painelService.setPainelConteudo(new PainelListaCategorias());
        painelService.setBorderPainelTransicao(painelService.getPainelTransicao(TipoPainelTransicao.CATEGORIAS));
    }

    @Override
    public void post(Formulario formulario) {
        get();
    }
}
