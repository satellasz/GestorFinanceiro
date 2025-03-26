package org.financeiro.controllers;

import org.financeiro.componentes.Formulario;
import org.financeiro.enums.TipoPainelMenu;
import org.financeiro.exceptions.CampoObrigatorioException;
import org.financeiro.views.paineisconteudo.PainelAdicionarTransacao;

import javax.swing.*;

public class TransacaoAdicionarController extends AbstractController {
    @Override
    public void get() {
        painelService.setPainelConteudo(new PainelAdicionarTransacao());
        painelService.setBorderPainelTransicao(painelService.getPainelTransicao(TipoPainelMenu.TRANSACOES));
    }

    @Override
    public void post(Formulario formulario) {
        try {
            this.validate(formulario);

            painelService.setPainelConteudo(new PainelAdicionarTransacao());
            painelService.setBorderPainelTransicao(painelService.getPainelTransicao(TipoPainelMenu.TRANSACOES));
        } catch (CampoObrigatorioException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Campo obrigatório", JOptionPane.ERROR_MESSAGE);
        }
    }
}
