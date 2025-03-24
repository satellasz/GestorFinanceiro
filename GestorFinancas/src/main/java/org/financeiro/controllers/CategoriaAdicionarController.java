package org.financeiro.controllers;

import org.financeiro.componentes.Formulario;
import org.financeiro.enums.TipoPainelTransicao;
import org.financeiro.views.paineisconteudo.PainelAdicionarCategoria;
import org.financeiro.views.paineisconteudo.PainelListaCategorias;

public class CategoriaAdicionarController extends Controller {
    @Override
    public void get() {
        painelService.setPainelConteudo(new PainelAdicionarCategoria());
        painelService.setBorderPainelTransicao(painelService.getPainelTransicao(TipoPainelTransicao.CATEGORIAS));
    }

    @Override
    public void post(Formulario formulario) {
        for (var component : formulario.getComponentes()) {
            System.out.println(component.getInput());
        }
        painelService.setPainelConteudo(new PainelListaCategorias());
        painelService.setBorderPainelTransicao(painelService.getPainelTransicao(TipoPainelTransicao.CATEGORIAS));
    }
}
