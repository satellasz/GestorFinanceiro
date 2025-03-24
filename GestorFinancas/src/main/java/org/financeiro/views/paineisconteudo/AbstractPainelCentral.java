package org.financeiro.views.paineisconteudo;

import org.financeiro.componentes.Formulario;
import org.financeiro.repositories.categoria.CategoriaRepositoryImpl;
import org.financeiro.services.categoria.CategoriaService;
import org.financeiro.services.categoria.CategoriaServiceImpl;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public abstract class AbstractPainelCentral extends JPanel {
    protected static final int ALTURA_PAINEL_CIMA = 250;
    protected static final int ALTURA_PAINEL_BAIXO = 473;
    protected JPanel painelCima;
    protected JPanel painelBaixo;
    protected final transient CategoriaService categoriaService = new CategoriaServiceImpl(new CategoriaRepositoryImpl());
    protected final Formulario formulario = new Formulario(150, 50, 800, 500);

    protected AbstractPainelCentral() {
        this.setLayout(new BorderLayout());
        onLoad();
    }

    protected abstract void onLoad();

    protected JPanel getPainelCima() {
        JPanel painel = new JPanel();
        painel.setPreferredSize(new Dimension(0, ALTURA_PAINEL_CIMA));
        painel.setLayout(null);
        painel.setBorder(new LineBorder(Color.black, 2));
        this.painelCima = painel;

        return painel;
    }

    protected JPanel getPainelBaixo() {
        JPanel painel = new JPanel();
        painel.setPreferredSize(new Dimension(0, ALTURA_PAINEL_BAIXO));
        painel.setLayout(null);
        painel.setBorder(new LineBorder(Color.black, 2));
        this.painelBaixo = painel;

        return painel;
    }

    protected void customizarPainelCima() {

    }

    protected void customizarPainelBaixo() {

    }
}
