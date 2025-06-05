package org.financeiro.views.paineisconteudo;

import org.financeiro.dtos.CategoriaDto;
import org.financeiro.dtos.FiltroDto;
import org.financeiro.dtos.ResumoFinanceiroDto;
import org.financeiro.dtos.UsuarioDto;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PainelResumoFinanceiro extends AbstractPainelCentral {
    private transient FiltroDto filtroDto;
    private final transient ResumoFinanceiroDto resumoFinanceiroDto;
    private final transient List<CategoriaDto> categorias;

    public PainelResumoFinanceiro(UsuarioDto usuarioDto, ResumoFinanceiroDto resumoFinanceiroDto, FiltroDto filtroDto, List<CategoriaDto> categorias) {
        super(usuarioDto);
        this.filtroDto = filtroDto;
        this.resumoFinanceiroDto = resumoFinanceiroDto;
        this.categorias = categorias;
    }

    public PainelResumoFinanceiro(UsuarioDto usuarioDto, ResumoFinanceiroDto resumoFinanceiroDto, List<CategoriaDto> categorias) {
        super(usuarioDto);
        this.resumoFinanceiroDto = resumoFinanceiroDto;
        this.categorias = categorias;
    }

    @Override
    public void onLoad() {
        this.painelService.setProcessoEmAndamento(false);
        this.add(getPainelCima(), BorderLayout.NORTH);
        this.add(getPainelBaixo(), BorderLayout.SOUTH);
        customizarPainelCima();
        customizarPainelBaixo();
    }

    @Override
    protected void customizarPainelCima() {
        this.painelCima.setLayout(new GridLayout(1, 2));
        this.painelCima.add(getPainelComFormularioFiltro(categorias, filtroDto, false, this.resumoFinanceiroController));
        this.painelCima.add(getPainelSaldo());
    }

    private JPanel getPainelSaldo() {
        JPanel painelSaldo = new JPanel(null);

        JLabel lblSaldo = new JLabel("Saldo R$");
        lblSaldo.setFont(lblSaldo.getFont().deriveFont(20f));
        lblSaldo.setBounds(0, 50, 500, 25);

        JLabel informacao = new JLabel(String.valueOf(this.resumoFinanceiroDto.saldoTotal()));
        informacao.setFont(informacao.getFont().deriveFont(30f));
        informacao.setBorder(getBorders());
        informacao.setBounds(0, 90, 500, 100);

        painelSaldo.add(lblSaldo);
        painelSaldo.add(informacao);

        return painelSaldo;
    }

    @Override
    protected void customizarPainelBaixo() {
        JPanel painelAgrupador = new JPanel();

        painelAgrupador.add(getPainelInfo("Receita R$", String.valueOf(this.resumoFinanceiroDto.receita())));
        painelAgrupador.add(getPainelInfo("Despesa R$", String.valueOf(this.resumoFinanceiroDto.despesa())));

        this.painelBaixo.setLayout(new GridLayout(2, 1));
        this.painelBaixo.add(painelAgrupador);
    }
}
