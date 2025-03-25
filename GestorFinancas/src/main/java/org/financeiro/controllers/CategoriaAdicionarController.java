package org.financeiro.controllers;

import org.financeiro.componentes.AbstractInputComponente;
import org.financeiro.componentes.Formulario;
import org.financeiro.enums.TipoPainelTransicao;
import org.financeiro.exceptions.CampoObrigatorioException;
import org.financeiro.models.Categoria;
import org.financeiro.views.paineisconteudo.PainelAdicionarCategoria;
import org.financeiro.views.paineisconteudo.PainelListaCategorias;

import javax.swing.*;
import java.util.Objects;

public class CategoriaAdicionarController extends AbstractController {
    @Override
    public void get() {
        painelService.setPainelConteudo(new PainelAdicionarCategoria());
        painelService.setBorderPainelTransicao(painelService.getPainelTransicao(TipoPainelTransicao.CATEGORIAS));
    }

    @Override
    public void post(Formulario formulario) {
        try {
            // TODO criar um método ou Enum para retornar um modelo a partir dos nomes (que está servindo como ID) dos componentes
            this.validate(formulario);
            Categoria categoria = new Categoria();
            AbstractInputComponente nome = formulario.getComponentes().stream().filter(x -> x.getNome().equals("Nome")).findFirst().orElse(null);
            AbstractInputComponente descricao = formulario.getComponentes().stream().filter(x -> x.getNome().equals("Descricao")).findFirst().orElse(null);
            if (nome != null) {
                categoria.setNome(nome.getInput());
            }
            if (descricao != null) {
                categoria.setDescricao(descricao.getInput());
            }

            this.categoriaService.cadastrarCategoria(categoria);

            painelService.setPainelConteudo(new PainelListaCategorias());
            painelService.setBorderPainelTransicao(painelService.getPainelTransicao(TipoPainelTransicao.CATEGORIAS));
        } catch (CampoObrigatorioException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Campo obrigatório", JOptionPane.ERROR_MESSAGE);
        }
    }
}
