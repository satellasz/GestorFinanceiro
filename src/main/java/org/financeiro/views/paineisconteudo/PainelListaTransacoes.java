package org.financeiro.views.paineisconteudo;

import org.financeiro.componentes.*;
import org.financeiro.dtos.CategoriaDto;
import org.financeiro.dtos.FiltroDto;
import org.financeiro.dtos.TransacaoDto;
import org.financeiro.enums.ClassificacaoTransacao;
import org.financeiro.enums.TipoInputComponente;
import org.financeiro.listeners.GetActionListener;
import org.financeiro.listeners.PostActionListener;
import org.financeiro.utils.Utils;
import org.financeiro.viewmodels.TransacaoModelTabela;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Objects;

import static org.financeiro.controllers.AbstractController.TODAS;

public class PainelListaTransacoes extends AbstractPainelCentral {
    private final transient List<TransacaoDto> transacaoList;
    private final transient List<CategoriaDto> categorias;
    private transient FiltroDto filtroDto;

    public PainelListaTransacoes(List<TransacaoDto> transacaoList, List<CategoriaDto> categorias) {
        this.transacaoList = transacaoList;
        this.categorias = categorias;
    }

    public PainelListaTransacoes(List<TransacaoDto> transacaoList, List<CategoriaDto> categorias, FiltroDto filtroDto) {
        this.transacaoList = transacaoList;
        this.categorias = categorias;
        this.filtroDto = filtroDto;
    }

    @Override
    public void onLoad() {
        this.painelService.setProcessoEmAndamento(false);
        this.add(getPainelFooter("Lista de transações"), BorderLayout.SOUTH);
        this.add(getPainelCima(), BorderLayout.NORTH);
        this.add(getPainelBaixo(), BorderLayout.CENTER);
        customizarPainelCima();
        customizarPainelBaixo();
    }

    @Override
    protected void customizarPainelCima() {
        JPanel jPanel1 = new JPanel();
        jPanel1.setLayout(null);

        Formulario formulario = getFormulario();

        Botao botaoFiltrar = new Botao(new PostActionListener(this.transacoesController, formulario));
        botaoFiltrar.setBounds(285, 175, 125, 50);
        botaoFiltrar.setText("Filtrar");

        Botao botaoResetarFiltro = new Botao(new GetActionListener(this.transacoesController));
        botaoResetarFiltro.setBounds(40, 175, 125, 50);
        botaoResetarFiltro.setText("Resetar filtro");

        jPanel1.add(formulario.getPanel());
        jPanel1.add(botaoFiltrar);
        jPanel1.add(botaoResetarFiltro);

        this.painelCima.setLayout(new GridLayout(1, 2));
        this.painelCima.add(jPanel1);
        this.painelCima.add(getPainelAdicionar(this.transacaoAdicionarController));
    }

    private Formulario getFormulario() {
        CampoData campoDataInicio = new CampoData(TipoInputComponente.DATA_INICIO, "Data início", false);
        CampoData campoDataFim = new CampoData(TipoInputComponente.DATA_FINAL, "Data fim", false);
        ComboBox comboBoxCategoria = new ComboBox(TipoInputComponente.TRANSACAO_CATEGORIA, "Categoria", false);
        ComboBox comboBoxClassificacao = new ComboBox(TipoInputComponente.CLASSFICACAO_TRANSACAO, "Classificação", false);

        comboBoxCategoria.addValorComboBox(TODAS);

        for (CategoriaDto categoriaEncontrada : categorias) {
            comboBoxCategoria.addValorComboBox(categoriaEncontrada.nome());
        }

        comboBoxClassificacao.addValorComboBox(TODAS);

        for (ClassificacaoTransacao classificacaoTransacao : ClassificacaoTransacao.values()) {
            comboBoxClassificacao.addValorComboBox(classificacaoTransacao.getNome());
        }

        if (filtroDto != null) {
            campoDataInicio.setInput(Utils.getData(filtroDto.dataInicio()));
            campoDataFim.setInput(Utils.getData(filtroDto.dataFim()));
            comboBoxCategoria.setInput(filtroDto.categoria() != null ? filtroDto.categoria().nome() : TODAS);
            if (!Objects.equals(filtroDto.classificacao(), TODAS)) {
                comboBoxClassificacao.setInput(filtroDto.classificacao());
            }
        }

        Formulario formulario = new Formulario(25, 0, 400, 200);
        formulario.addComponente(campoDataInicio);
        formulario.addComponente(campoDataFim);
        formulario.addComponente(comboBoxCategoria);
        formulario.addComponente(comboBoxClassificacao);
        return formulario;
    }

    @Override
    protected void customizarPainelBaixo() {
        JPanel jPanel1 = new JPanel();
        jPanel1.setLayout(new BorderLayout());
        jPanel1.setBounds(40, 10, 1000, 400);

        Tabela tabela = new Tabela(new TransacaoModelTabela(transacaoList), this.transacoesController);

        jPanel1.add(tabela.getPainel());

        this.painelBaixo.setLayout(null);
        this.painelBaixo.add(jPanel1);
    }
}
