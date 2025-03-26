package org.financeiro.controllers;

import org.financeiro.componentes.Formulario;
import org.financeiro.enums.TipoInputComponente;
import org.financeiro.enums.TipoPainelMenu;
import org.financeiro.exceptions.CampoObrigatorioException;
import org.financeiro.models.Categoria;
import org.financeiro.views.paineisconteudo.PainelAdicionarCategoria;
import org.financeiro.views.paineisconteudo.PainelListaCategorias;

import javax.swing.*;

public class CategoriaAdicionarController extends AbstractController {
    @Override
    public void get() {
        painelService.setPainelConteudo(new PainelAdicionarCategoria());
        painelService.setBorderPainelTransicao(painelService.getPainelTransicao(TipoPainelMenu.CATEGORIAS));
    }

    @Override
    public void post(Formulario formulario) {
        try {
            this.validate(formulario);

            String nome = this.formularioService.getInputComponente(TipoInputComponente.NOME_CATEGORIA, formulario);
            String descricao = this.formularioService.getInputComponente(TipoInputComponente.DESCRICAO_CATEGORIA, formulario);

            Categoria categoria = new Categoria(nome, descricao, null);

            this.categoriaService.cadastrarCategoria(categoria);

            painelService.setPainelConteudo(new PainelListaCategorias());
            painelService.setBorderPainelTransicao(painelService.getPainelTransicao(TipoPainelMenu.CATEGORIAS));
        } catch (CampoObrigatorioException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Campo obrigatório", JOptionPane.ERROR_MESSAGE);
        }
    }
}
