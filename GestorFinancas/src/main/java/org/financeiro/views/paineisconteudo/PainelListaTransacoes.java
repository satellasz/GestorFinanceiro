package org.financeiro.views.paineisconteudo;

import org.financeiro.componentes.Botao;
import org.financeiro.listeners.GetActionListener;

import javax.swing.*;
import java.awt.*;

public class PainelListaTransacoes extends AbstractPainelCentral {
    @Override
    public void onLoad() {
        this.add(getPainelCima(), BorderLayout.NORTH);
        this.add(getPainelBaixo(), BorderLayout.SOUTH);
        customizarPainelCima();
    }

    @Override
    protected void customizarPainelCima() {
        JPanel jPanel1 = new JPanel();
        jPanel1.setLayout(null);
        jPanel1.setBackground(Color.RED);

        JPanel jPanel2 = new JPanel();
        jPanel2.setLayout(null);
        jPanel2.setBackground(Color.GRAY);
        Botao botaoAdcionar = new Botao(new GetActionListener(this.transacaoAdicionarController));
        botaoAdcionar.setBounds(400, 175, 125, 50);
        botaoAdcionar.setText("Adicionar");
        jPanel2.add(botaoAdcionar);

        this.painelCima.setLayout(new GridLayout(1, 2));
        this.painelCima.add(jPanel1);
        this.painelCima.add(jPanel2);
    }
}
