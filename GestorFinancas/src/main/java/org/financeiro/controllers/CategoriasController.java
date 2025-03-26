package org.financeiro.controllers;

import org.financeiro.componentes.Formulario;
import org.financeiro.enums.TipoPainelMenu;
import org.financeiro.views.paineisconteudo.PainelListaCategorias;

public class CategoriasController extends AbstractController {

    @Override
    public void get() {
        painelService.setPainelConteudo(new PainelListaCategorias());
        painelService.setBorderPainelTransicao(painelService.getPainelTransicao(TipoPainelMenu.CATEGORIAS));
    }

    @Override
    public void post(Formulario formulario) {
        get();
    }

    @Override
    public void delete(int id) {
        this.categoriaService.excluirCategoria(id);
        get();
    }
}
