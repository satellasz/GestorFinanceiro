package org.financeiro.views.paineisconteudo;

import javax.swing.*;
import java.awt.*;

public class PainelListaTransacoes extends PainelCentral {
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
        jPanel1.setLayout(null);
        jPanel2.setBackground(Color.PINK);

        this.painelCima.setLayout(new GridLayout(1, 2));
        this.painelCima.add(jPanel1);
        this.painelCima.add(jPanel2);
    }
}
