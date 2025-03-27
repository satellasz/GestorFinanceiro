package org.financeiro.controllers;

import org.financeiro.componentes.Formulario;
import org.financeiro.enums.ClassificacaoTransacao;
import org.financeiro.enums.TipoInputComponente;
import org.financeiro.enums.TipoPainelMenu;
import org.financeiro.exceptions.CampoInvalidoException;
import org.financeiro.exceptions.CampoObrigatorioException;
import org.financeiro.exceptions.DadoNaoEncontradoException;
import org.financeiro.models.Categoria;
import org.financeiro.models.Transacao;
import org.financeiro.utils.Utils;
import org.financeiro.views.paineisconteudo.PainelAdicionarTransacao;
import org.financeiro.views.paineisconteudo.PainelListaTransacoes;

import javax.swing.*;
import java.time.LocalDate;

public class TransacaoAdicionarController extends AbstractController {
    @Override
    public void get() {
        painelService.setPainelConteudo(new PainelAdicionarTransacao());
        painelService.setBorderPainelTransicao(painelService.getPainelTransicao(TipoPainelMenu.TRANSACOES));
    }

    @Override
    public void post(Formulario formulario) {
        try {
            this.validateCamposObrigatorios(formulario);
            this.validateCamposNumericos(formulario);

            double valor = Double.parseDouble(this.formularioService.getInputComponente(TipoInputComponente.VALOR_TRANSACAO, formulario));
            String descricao = this.formularioService.getInputComponente(TipoInputComponente.DESCRICAO_CATEGORIA, formulario);
            String categoriaNome = this.formularioService.getInputComponente(TipoInputComponente.TRANSACAO_CATEGORIA, formulario);
            String classificacao = this.formularioService.getInputComponente(TipoInputComponente.CLASSFICACAO_TRANSACAO, formulario);
            String dataTransacao = this.formularioService.getInputComponente(TipoInputComponente.DATA_TRANSACAO, formulario);

            Categoria categoria = this.categoriaService.buscarCategoria(categoriaNome);

            if (formulario.getIdObjeto() <= 0) {
                Transacao transacao = new Transacao(valor, descricao, categoria.getId(), ClassificacaoTransacao.buscarClassificacao(classificacao), null, dataTransacao);

                this.transacaoService.cadastrarTransacao(transacao);
            } else {
                Transacao transacao = new Transacao(formulario.getIdObjeto(), valor, descricao, categoria.getId(), ClassificacaoTransacao.buscarClassificacao(classificacao), null, dataTransacao);

                this.transacaoService.alterarTransacao(transacao);
            }

            painelService.setPainelConteudo(new PainelListaTransacoes());
            painelService.setBorderPainelTransicao(painelService.getPainelTransicao(TipoPainelMenu.TRANSACOES));
        } catch (CampoObrigatorioException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Campo obrigatório", JOptionPane.ERROR_MESSAGE);
        } catch (CampoInvalidoException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Campo inválido", JOptionPane.ERROR_MESSAGE);
        } catch (DadoNaoEncontradoException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Não encontrado", JOptionPane.ERROR_MESSAGE);
        }
    }
}
