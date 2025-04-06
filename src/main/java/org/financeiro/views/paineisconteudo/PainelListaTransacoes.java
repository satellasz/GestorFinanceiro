package org.financeiro.views.paineisconteudo;

import org.financeiro.componentes.Tabela;
import org.financeiro.dtos.CategoriaDto;
import org.financeiro.dtos.FiltroDto;
import org.financeiro.dtos.TransacaoDto;
import org.financeiro.viewmodels.TransacaoModelTabela;

import javax.swing.*;
import java.awt.*;
import java.util.List;

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
        this.painelCima.setLayout(new GridLayout(1, 2));
        this.painelCima.add(getPainelComFormularioFiltro(categorias, filtroDto, true, this.transacoesController));
        this.painelCima.add(getPainelAdicionar(this.transacaoAdicionarController));
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
