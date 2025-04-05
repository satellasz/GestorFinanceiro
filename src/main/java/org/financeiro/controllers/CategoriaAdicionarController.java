package org.financeiro.controllers;

import org.financeiro.componentes.Formulario;
import org.financeiro.dtos.CategoriaDto;
import org.financeiro.dtos.UsuarioDto;
import org.financeiro.enums.TipoInputComponente;
import org.financeiro.enums.TipoPainelMenu;
import org.financeiro.exceptions.CampoInvalidoException;
import org.financeiro.exceptions.CampoObrigatorioException;
import org.financeiro.exceptions.DadoNaoEncontradoException;
import org.financeiro.views.paineisconteudo.PainelAdicionarCategoria;
import org.financeiro.views.paineisconteudo.PainelListaCategorias;

import javax.swing.*;

public class CategoriaAdicionarController extends AbstractController {
    @Override
    public void get() {
        painelService.setPainelConteudo(new PainelAdicionarCategoria());
        painelService.setBorderPainelTransicao(painelService.getPainelMenu(TipoPainelMenu.CATEGORIAS));
    }

    @Override
    public void post(Formulario formulario) {
        try {
            this.validateCampos(formulario);

            String nome = this.formularioService.getInputComponente(TipoInputComponente.NOME_CATEGORIA, formulario);
            String descricao = this.formularioService.getInputComponente(TipoInputComponente.DESCRICAO_CATEGORIA, formulario);

            UsuarioDto usuarioLogado = this.usuarioService.buscarUsuarioLogado();

            if (formulario.getIdObjeto() <= 0) {
                CategoriaDto categoria = new CategoriaDto(this.categoriaService.getIdProximaCategoria(), nome, descricao, usuarioLogado);

                this.categoriaService.cadastrarCategoria(categoria);
            } else {
                CategoriaDto categoria = new CategoriaDto(formulario.getIdObjeto(), nome, descricao, usuarioLogado);

                this.categoriaService.alterarCategoria(categoria);
            }

            painelService.setPainelConteudo(new PainelListaCategorias(this.categoriaService.listarCategorias()));
            painelService.setBorderPainelTransicao(painelService.getPainelMenu(TipoPainelMenu.CATEGORIAS));
        } catch (CampoObrigatorioException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Campo obrigatório", JOptionPane.ERROR_MESSAGE);
        } catch (DadoNaoEncontradoException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Não encontrado", JOptionPane.ERROR_MESSAGE);
        } catch (CampoInvalidoException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Campo inválido", JOptionPane.ERROR_MESSAGE);
        }
    }
}
