package org.financeiro.controllers;

import org.financeiro.componentes.Formulario;
import org.financeiro.enums.TipoPainelMenu;
import org.financeiro.exceptions.DadoNaoEncontradoException;
import org.financeiro.exceptions.IntegridadeException;
import org.financeiro.models.Categoria;
import org.financeiro.views.paineisconteudo.PainelAdicionarCategoria;
import org.financeiro.views.paineisconteudo.PainelListaCategorias;

import javax.swing.*;

public class CategoriasController extends AbstractController {

    @Override
    public void get() {
        painelService.setPainelConteudo(new PainelListaCategorias(this.categoriaService.listarCategorias()));
        painelService.setBorderPainelTransicao(painelService.getPainelMenu(TipoPainelMenu.CATEGORIAS));
    }

    @Override
    public void post(Formulario formulario) {
        get();
    }

    @Override
    public void delete(int id) {
        try {
            this.categoriaService.excluirCategoria(id);
            get();
        } catch (DadoNaoEncontradoException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Não encontrado", JOptionPane.ERROR_MESSAGE);
        } catch (IntegridadeException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ocorreu um erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void patch(int id) {
        try {
            Categoria categoria = this.categoriaService.buscarCategoria(id);

            painelService.setPainelConteudo(new PainelAdicionarCategoria(categoria));
            painelService.setBorderPainelTransicao(painelService.getPainelMenu(TipoPainelMenu.CATEGORIAS));
        } catch (DadoNaoEncontradoException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Não encontrado", JOptionPane.ERROR_MESSAGE);
        }
    }
}
