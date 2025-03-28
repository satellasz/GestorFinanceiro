package org.financeiro.views.paineisconteudo;

import org.financeiro.componentes.Formulario;
import org.financeiro.controllers.*;
import org.financeiro.services.ImageService;
import org.financeiro.services.ImageServiceImpl;
import org.financeiro.services.categoria.CategoriaService;
import org.financeiro.services.categoria.CategoriaServiceImpl;
import org.financeiro.services.transacao.TransacaoService;
import org.financeiro.services.transacao.TransacaoServiceImpl;

import javax.swing.*;
import javax.swing.border.LineBorder;
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
    protected final transient TransacaoService transacaoService = new TransacaoServiceImpl();
    protected final transient ImageService imageService = new ImageServiceImpl();
    protected final transient Formulario formulario = new Formulario( 150, 50, 800, 500);

    protected AbstractPainelCentral() {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.WHITE);
    }

    public abstract void onLoad();

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
