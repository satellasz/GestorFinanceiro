package org.financeiro.views.paineisconteudo;

import org.financeiro.componentes.Botao;
import org.financeiro.componentes.Formulario;
import org.financeiro.controllers.*;
import org.financeiro.listeners.GetActionListener;
import org.financeiro.services.categoria.CategoriaService;
import org.financeiro.services.categoria.CategoriaServiceImpl;

import javax.swing.*;
import java.awt.*;

public abstract class AbstractPainelCentral extends JPanel {
    protected final transient TransacaoAdicionarController transacaoAdicionarController = new TransacaoAdicionarController();
    protected final transient TransacoesController transacoesController = new TransacoesController();
    protected final transient CategoriasController categoriasController = new CategoriasController();
    protected final transient CategoriaAdicionarController categoriaAdicionarController = new CategoriaAdicionarController();
    protected final transient ResumoController resumoController = new ResumoController();
    protected final transient PerfilController perfilController = new PerfilController();
    protected static final int ALTURA_PAINEL_CIMA = 250;
    protected static final int ALTURA_PAINEL_BAIXO = 473;
    protected JPanel painelCima;
    protected JPanel painelBaixo;
    protected final transient CategoriaService categoriaService = new CategoriaServiceImpl();
    protected final transient Formulario formulario = new Formulario(150, 50, 800, 500);

    protected AbstractPainelCentral() {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.WHITE);
    }

    public abstract void onLoad();

    protected JPanel getPainelCima() {
        JPanel painel = new JPanel();
        painel.setPreferredSize(new Dimension(0, ALTURA_PAINEL_CIMA));
        painel.setLayout(null);
        this.painelCima = painel;

        return painel;
    }

    protected JPanel getPainelBaixo() {
        JPanel painel = new JPanel();
        painel.setPreferredSize(new Dimension(0, ALTURA_PAINEL_BAIXO));
        painel.setLayout(null);
        this.painelBaixo = painel;

        return painel;
    }

    protected JPanel getPainelAdicionar(AbstractController controller) {
        JPanel painelAdicionar = new JPanel();
        painelAdicionar.setLayout(null);
        Botao botaoAdcionar = new Botao(new GetActionListener(controller));
        botaoAdcionar.setBounds(375, 175, 125, 50);
        botaoAdcionar.setText("Adicionar");
        painelAdicionar.add(botaoAdcionar);

        return painelAdicionar;
    }

    protected void customizarPainelCima() {

    }

    protected void customizarPainelBaixo() {

    }
}
