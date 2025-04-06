package org.financeiro.views.paineisconteudo;

import org.financeiro.componentes.Botao;
import org.financeiro.componentes.Formulario;
import org.financeiro.controllers.*;
import org.financeiro.listeners.GetActionListener;
import org.financeiro.listeners.PostActionListener;
import org.financeiro.services.PainelService;
import org.financeiro.services.PainelServiceImpl;
import org.financeiro.services.usuario.UsuarioService;
import org.financeiro.services.usuario.UsuarioServiceImpl;
import org.financeiro.utils.Utils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public abstract class AbstractPainelCentral extends JPanel {
    private static final int WIDTH_PADRAO_BOTAO_ACAO = 100;
    private static final int HEIGHT_PADRAO_BOTAO_ACAO = 50;
    protected final transient TransacaoAdicionarController transacaoAdicionarController = new TransacaoAdicionarController();
    protected final transient TransacoesController transacoesController = new TransacoesController();
    protected final transient CategoriasController categoriasController = new CategoriasController();
    protected final transient CategoriaAdicionarController categoriaAdicionarController = new CategoriaAdicionarController();
    protected static final int ALTURA_PAINEL_CIMA = 250;
    protected static final int ALTURA_PAINEL_BAIXO = 473;
    private static final int ALTURA_FOOTER = 50;
    protected JPanel painelCima;
    protected JPanel painelBaixo;
    protected final transient Formulario formulario = new Formulario( 325, 50, 400, 500);
    private final transient UsuarioService usuarioService = new UsuarioServiceImpl();
    protected final transient PainelService painelService = new PainelServiceImpl();

    protected AbstractPainelCentral() {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.WHITE);
    }

    public abstract void onLoad();

    protected JPanel getPainelFooter(String label) {
        JPanel painel = new JPanel();
        painel.setPreferredSize(new Dimension(0, ALTURA_FOOTER));
        painel.setLayout(new BorderLayout());

        JLabel labelPainel = new JLabel(label);
        labelPainel.setFont(labelPainel.getFont().deriveFont(25f));
        labelPainel.setBorder(new EmptyBorder(5, 30, 5, 5));

        painel.add(labelPainel, BorderLayout.LINE_START);
        painel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));

        StringBuilder informacoes = new StringBuilder();
        informacoes.append("Usuário logado: ");
        informacoes.append(this.usuarioService.buscarUsuarioLogado().nome());
        informacoes.append(" - ");
        informacoes.append(Utils.getDataAtual());

        JLabel labelInformacoes = new JLabel(informacoes.toString());
        labelInformacoes.setFont(labelInformacoes.getFont().deriveFont(15f));
        labelInformacoes.setBorder(new EmptyBorder(5, 5, 5, 30));
        painel.add(labelInformacoes, BorderLayout.LINE_END);

        return painel;
    }

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

    protected JButton getBotaoSalvar(AbstractController controller) {
        Botao botaoSalvar = new Botao(new PostActionListener(controller, this.formulario));
        botaoSalvar.setText("Salvar");
        botaoSalvar.setBounds(625, this.formulario.getHeight() + HEIGHT_PADRAO_BOTAO_ACAO, WIDTH_PADRAO_BOTAO_ACAO, HEIGHT_PADRAO_BOTAO_ACAO);

        return botaoSalvar;
    }

    protected JButton getBotaoCancelar(AbstractController controller) {
        Botao botaoCancelar = new Botao(new GetActionListener(controller));
        botaoCancelar.setText("Cancelar");
        botaoCancelar.setBounds(325, this.formulario.getHeight() + HEIGHT_PADRAO_BOTAO_ACAO, WIDTH_PADRAO_BOTAO_ACAO, HEIGHT_PADRAO_BOTAO_ACAO);

        return botaoCancelar;
    }

    protected void customizarPainelCima() {

    }

    protected void customizarPainelBaixo() {

    }
}
