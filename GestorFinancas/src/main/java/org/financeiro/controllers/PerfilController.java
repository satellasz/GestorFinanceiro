package org.financeiro.controllers;

import org.financeiro.componentes.Formulario;
import org.financeiro.enums.TipoPainelTransicao;
import org.financeiro.views.paineisconteudo.PainelPerfil;

public class PerfilController extends Controller {
    public PerfilController() {
        super();
    }

    @Override
    public void get() {
        painelService.setPainelConteudo(new PainelPerfil());
        painelService.setBorderPainelTransicao(painelService.getPainelTransicao(TipoPainelTransicao.PERFIL));
    }

    @Override
    public void post(Formulario formulario) {
        get();
    }
}
