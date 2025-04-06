package org.financeiro.controllers;

import org.financeiro.componentes.Formulario;
import org.financeiro.dtos.CategoriaDto;
import org.financeiro.dtos.TransacaoDto;
import org.financeiro.dtos.UsuarioDto;
import org.financeiro.enums.ClassificacaoTransacao;
import org.financeiro.enums.TipoInputComponente;
import org.financeiro.enums.TipoPainelMenu;
import org.financeiro.exceptions.CampoInvalidoException;
import org.financeiro.exceptions.CampoObrigatorioException;
import org.financeiro.exceptions.DadoNaoEncontradoException;
import org.financeiro.utils.Utils;
import org.financeiro.views.paineisconteudo.PainelAdicionarTransacao;

import javax.swing.*;

public class TransacaoAdicionarController extends AbstractController {
    @Override
    public void get() {
        try {
            painelService.setPainelConteudo(new PainelAdicionarTransacao(this.categoriaService.listarCategorias()));
        } catch (DadoNaoEncontradoException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), NAO_ENCONTRADO, JOptionPane.ERROR_MESSAGE);
        }
        painelService.setBorderPainelTransicao(painelService.getPainelMenu(TipoPainelMenu.TRANSACOES));
    }

    @Override
    public void post(Formulario formulario) {
        try {
            this.validateCampos(formulario);

            double valor = Double.parseDouble(this.formularioService.getInputComponente(TipoInputComponente.VALOR_TRANSACAO, formulario));
            String descricao = this.formularioService.getInputComponente(TipoInputComponente.DESCRICAO_CATEGORIA, formulario);
            String categoriaNome = this.formularioService.getInputComponente(TipoInputComponente.TRANSACAO_CATEGORIA, formulario);
            String classificacao = this.formularioService.getInputComponente(TipoInputComponente.CLASSFICACAO_TRANSACAO, formulario);
            String dataTransacao = this.formularioService.getInputComponente(TipoInputComponente.DATA_TRANSACAO, formulario);

            CategoriaDto categoria = this.categoriaService.buscarCategoria(categoriaNome);
            UsuarioDto usuarioLogado = this.usuarioService.buscarUsuarioLogado();

            if (formulario.getIdObjeto() <= 0) {
                TransacaoDto transacao = new TransacaoDto(this.transacaoService.getIdProximaTransacao(), descricao, valor , categoria, ClassificacaoTransacao.buscarClassificacao(classificacao), usuarioLogado, Utils.getData(dataTransacao));

                this.transacaoService.cadastrarTransacao(transacao);
            } else {
                TransacaoDto transacao = new TransacaoDto(formulario.getIdObjeto(), descricao, valor, categoria, ClassificacaoTransacao.buscarClassificacao(classificacao), usuarioLogado, Utils.getData(dataTransacao));

                this.transacaoService.alterarTransacao(transacao);
            }

            new TransacoesController().get();
        } catch (CampoObrigatorioException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Campo obrigatório", JOptionPane.ERROR_MESSAGE);
        } catch (CampoInvalidoException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Campo inválido", JOptionPane.ERROR_MESSAGE);
        } catch (DadoNaoEncontradoException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Não encontrado", JOptionPane.ERROR_MESSAGE);
        }
    }
}
