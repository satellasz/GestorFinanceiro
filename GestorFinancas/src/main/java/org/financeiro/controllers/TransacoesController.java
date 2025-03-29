package org.financeiro.controllers;

import org.financeiro.componentes.Formulario;
import org.financeiro.dtos.FiltroDto;
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
import java.util.List;
import java.util.Objects;

public class TransacoesController extends AbstractController {

    public static final String NAO_ENCONTRADO = "Não encontrado";

    @Override
    public void get() {
        painelService.setPainelConteudo(new PainelListaTransacoes(this.transacaoService.listarTransacoes()));
        painelService.setBorderPainelTransicao(painelService.getPainelMenu(TipoPainelMenu.TRANSACOES));
    }

    @Override
    public void post(Formulario formulario) {
        try {
            this.validateCampos(formulario);

            String dataInicio = this.formularioService.getInputComponente(TipoInputComponente.DATA_INICIO, formulario);
            String dataFinal = this.formularioService.getInputComponente(TipoInputComponente.DATA_FINAL, formulario);
            String categoriaNome = this.formularioService.getInputComponente(TipoInputComponente.TRANSACAO_CATEGORIA, formulario);
            String classificacao = this.formularioService.getInputComponente(TipoInputComponente.CLASSFICACAO_TRANSACAO, formulario);

            LocalDate dataInicioParsed = Utils.getData(dataInicio);
            LocalDate dataFinalParsed = Utils.getData(dataFinal);

            Categoria categoria = null;

            if (!Objects.equals(categoriaNome, TODAS)) {
                categoria = this.categoriaService.buscarCategoria(categoriaNome);
            }

            FiltroDto filtroDto = new FiltroDto(dataInicioParsed, dataFinalParsed, categoria, classificacao);

            List<Transacao> transacaoListFiltradas = this.transacaoService.getTransacoesFiltradas(filtroDto);

            painelService.setPainelConteudo(new PainelListaTransacoes(transacaoListFiltradas, filtroDto));
            painelService.setBorderPainelTransicao(painelService.getPainelMenu(TipoPainelMenu.TRANSACOES));
        } catch (CampoObrigatorioException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Campo obrigatório", JOptionPane.ERROR_MESSAGE);
        } catch (CampoInvalidoException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Campo inválido", JOptionPane.ERROR_MESSAGE);
        } catch (DadoNaoEncontradoException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), NAO_ENCONTRADO, JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void delete(int id) {
        try {
            this.transacaoService.excluirTransacao(id);
            get();
        } catch (DadoNaoEncontradoException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), NAO_ENCONTRADO, JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void patch(int id) {
        try {
            Transacao transacao = this.transacaoService.buscarTransacao(id);

            Categoria categoria = this.categoriaService.buscarCategoria(transacao.getIdCategoria());

            painelService.setPainelConteudo(new PainelAdicionarTransacao(transacao, categoria));
            painelService.setBorderPainelTransicao(painelService.getPainelMenu(TipoPainelMenu.CATEGORIAS));
        } catch (DadoNaoEncontradoException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), NAO_ENCONTRADO, JOptionPane.ERROR_MESSAGE);
        }
    }
}
