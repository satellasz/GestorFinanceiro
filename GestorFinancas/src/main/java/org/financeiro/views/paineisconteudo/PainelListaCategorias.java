package org.financeiro.views.paineisconteudo;

import org.financeiro.componentes.Botao;
import org.financeiro.controllers.PerfilController;
import org.financeiro.listeners.GetActionListener;

import javax.swing.*;
import java.awt.*;

public class PainelListaCategorias extends PainelCentral {
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
        jPanel1.setBackground(Color.RED);

        JPanel jPanel2 = new JPanel();
        jPanel1.setLayout(null);
        jPanel2.setBackground(Color.GRAY);

        this.painelCima.setLayout(new GridLayout(1, 2));
        this.painelCima.add(jPanel1);
        this.painelCima.add(jPanel2);
    }
}
