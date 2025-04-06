package org.financeiro.controllers;

import org.financeiro.componentes.Formulario;
import org.financeiro.dtos.CategoriaDto;
import org.financeiro.dtos.FiltroDto;
import org.financeiro.dtos.ResumoFinanceiroDto;
import org.financeiro.dtos.TransacaoDto;
import org.financeiro.enums.TipoInputComponente;
import org.financeiro.enums.TipoPainelMenu;
import org.financeiro.exceptions.CampoInvalidoException;
import org.financeiro.exceptions.CampoObrigatorioException;
import org.financeiro.exceptions.DadoNaoEncontradoException;
import org.financeiro.utils.Utils;
import org.financeiro.views.paineisconteudo.PainelResumoFinanceiro;

import javax.swing.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class ResumoFinanceiroController extends AbstractController {
    @Override
    public void get() {
        try {
            painelService.setPainelConteudo(new PainelResumoFinanceiro(this.resumoFinanceiroService.getResumoFinanceiro(this.transacaoService.listarTransacoes()), this.categoriaService.listarCategorias()));
        } catch (DadoNaoEncontradoException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), NAO_ENCONTRADO, JOptionPane.ERROR_MESSAGE);
        }
        painelService.setBorderPainelTransicao(painelService.getPainelMenu(TipoPainelMenu.RESUMO));
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

            CategoriaDto categoria = null;

            if (!Objects.equals(categoriaNome, TODAS)) {
                categoria = this.categoriaService.buscarCategoria(categoriaNome);
            }

            FiltroDto filtroDto = new FiltroDto(dataInicioParsed, dataFinalParsed, categoria, classificacao);

            List<TransacaoDto> transacaoListFiltradas = this.transacaoService.getTransacoesFiltradas(filtroDto);

            ResumoFinanceiroDto resumoFinanceiroDto = this.resumoFinanceiroService.getResumoFinanceiro(transacaoListFiltradas);

            painelService.setPainelConteudo(new PainelResumoFinanceiro(resumoFinanceiroDto, filtroDto, this.categoriaService.listarCategorias()));
            painelService.setBorderPainelTransicao(painelService.getPainelMenu(TipoPainelMenu.RESUMO));
        } catch (CampoObrigatorioException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Campo obrigatório", JOptionPane.ERROR_MESSAGE);
        } catch (CampoInvalidoException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Campo inválido", JOptionPane.ERROR_MESSAGE);
        } catch (DadoNaoEncontradoException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), NAO_ENCONTRADO, JOptionPane.ERROR_MESSAGE);
        }
    }
}
