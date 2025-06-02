package org.financeiro.controllers;

import org.financeiro.componentes.Formulario;
import org.financeiro.dtos.CategoriaDto;
import org.financeiro.dtos.FiltroDto;
import org.financeiro.dtos.TransacaoDto;
import org.financeiro.enums.TipoInputComponente;
import org.financeiro.enums.TipoPainelMenu;
import org.financeiro.exceptions.CampoInvalidoException;
import org.financeiro.exceptions.CampoObrigatorioException;
import org.financeiro.exceptions.DadoNaoEncontradoException;
import org.financeiro.utils.Utils;
import org.financeiro.views.paineisconteudo.PainelAdicionarTransacao;
import org.financeiro.views.paineisconteudo.PainelListaTransacoes;

import javax.swing.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class TransacoesController extends AbstractController {

    @Override
    public void get() {
        try {
            painelService.setPainelConteudo(new PainelListaTransacoes(this.usuarioService.buscarUsuarioLogado(), this.transacaoService.listarTransacoes(), this.categoriaService.listarCategorias()));
        } catch (DadoNaoEncontradoException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), NAO_ENCONTRADO, JOptionPane.ERROR_MESSAGE);
        }
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

            CategoriaDto categoria = null;

            if (!Objects.equals(categoriaNome, TODAS)) {
                categoria = this.categoriaService.buscarCategoriaDto(categoriaNome);
            }

            FiltroDto filtroDto = new FiltroDto(dataInicioParsed, dataFinalParsed, categoria, classificacao);

            List<TransacaoDto> transacaoListFiltradas = this.transacaoService.getTransacoesFiltradas(filtroDto);

            painelService.setPainelConteudo(new PainelListaTransacoes(this.usuarioService.buscarUsuarioLogado(), transacaoListFiltradas, this.categoriaService.listarCategorias(), filtroDto));
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
    public void delete(long id) {
        try {
            this.transacaoService.excluirTransacao(id);
            get();
        } catch (DadoNaoEncontradoException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), NAO_ENCONTRADO, JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void patch(long id) {
        try {
            TransacaoDto transacao = this.transacaoService.buscarTransacao(id);

            painelService.setPainelConteudo(new PainelAdicionarTransacao(this.usuarioService.buscarUsuarioLogado(), transacao, transacao.categoriaDto(), this.categoriaService.listarCategorias()));
            painelService.setBorderPainelTransicao(painelService.getPainelMenu(TipoPainelMenu.CATEGORIAS));
        } catch (DadoNaoEncontradoException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), NAO_ENCONTRADO, JOptionPane.ERROR_MESSAGE);
        }
    }
}
