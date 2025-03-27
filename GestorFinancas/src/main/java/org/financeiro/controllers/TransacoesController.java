package org.financeiro.controllers;

import org.financeiro.componentes.Formulario;
import org.financeiro.enums.TipoPainelMenu;
import org.financeiro.exceptions.DadoNaoEncontradoException;
import org.financeiro.models.Categoria;
import org.financeiro.models.Transacao;
import org.financeiro.views.paineisconteudo.PainelAdicionarCategoria;
import org.financeiro.views.paineisconteudo.PainelAdicionarTransacao;
import org.financeiro.views.paineisconteudo.PainelListaTransacoes;

import javax.swing.*;

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

    @Override
    public void delete(int id) {
        try {
            this.transacaoService.excluirTransacao(id);
            get();
        } catch (DadoNaoEncontradoException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Não encontrado", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void patch(int id) {
        try {
            Transacao transacao = this.transacaoService.buscarTransacao(id);

            Categoria categoria = this.categoriaService.buscarCategoria(transacao.getIdCategoria());

            painelService.setPainelConteudo(new PainelAdicionarTransacao(transacao, categoria));
            painelService.setBorderPainelTransicao(painelService.getPainelTransicao(TipoPainelMenu.CATEGORIAS));
        } catch (DadoNaoEncontradoException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Não encontrado", JOptionPane.ERROR_MESSAGE);
        }
    }
}
