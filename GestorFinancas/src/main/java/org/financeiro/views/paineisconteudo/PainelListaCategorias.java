package org.financeiro.views.paineisconteudo;

import org.financeiro.componentes.Tabela;
import org.financeiro.viewmodels.CategoriaModelTabela;

import javax.swing.*;
import java.awt.*;

public class PainelListaCategorias extends AbstractPainelCentral {
    @Override
    public void onLoad() {
        this.add(getPainelCima(), BorderLayout.NORTH);
        this.add(getPainelBaixo(), BorderLayout.SOUTH);
        customizarPainelCima();
        customizarPainelBaixo();
    }

    @Override
    protected void customizarPainelCima() {
        JPanel jPanel1 = new JPanel();
        jPanel1.setLayout(null);

        this.painelCima.setLayout(new GridLayout(1, 2));
        this.painelCima.add(jPanel1);
        this.painelCima.add(getPainelAdicionar(this.categoriaAdicionarController));
    }

    @Override
    protected void customizarPainelBaixo() {
        JPanel jPanel1 = new JPanel();
        jPanel1.setLayout(new BorderLayout());
        jPanel1.setBounds(40, 10, 1000, 400);

        Tabela tabela = new Tabela(new CategoriaModelTabela(this.categoriaService.listarCategorias()), this.categoriasController);

        jPanel1.add(tabela.getPainel());

        this.painelBaixo.setLayout(null);
        this.painelBaixo.add(jPanel1);
    }
}
