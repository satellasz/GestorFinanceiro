package org.financeiro.views.paineisconteudo;

import org.financeiro.componentes.Botao;
import org.financeiro.componentes.CampoData;
import org.financeiro.componentes.ComboBox;
import org.financeiro.componentes.Formulario;
import org.financeiro.controllers.*;
import org.financeiro.dtos.CategoriaDto;
import org.financeiro.dtos.FiltroDto;
import org.financeiro.enums.ClassificacaoTransacao;
import org.financeiro.enums.TipoInputComponente;
import org.financeiro.listeners.GetActionListener;
import org.financeiro.listeners.PostActionListener;
import org.financeiro.services.PainelService;
import org.financeiro.services.PainelServiceImpl;
import org.financeiro.services.usuario.UsuarioService;
import org.financeiro.services.usuario.UsuarioServiceImpl;
import org.financeiro.utils.Utils;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;
import java.util.Objects;

import static org.financeiro.controllers.AbstractController.TODAS;

public abstract class AbstractPainelCentral extends JPanel {
    private static final int WIDTH_PADRAO_BOTAO_ACAO = 100;
    private static final int HEIGHT_PADRAO_BOTAO_ACAO = 50;
    protected final transient TransacaoAdicionarController transacaoAdicionarController = new TransacaoAdicionarController();
    protected final transient TransacoesController transacoesController = new TransacoesController();
    protected final transient CategoriasController categoriasController = new CategoriasController();
    protected final transient CategoriaAdicionarController categoriaAdicionarController = new CategoriaAdicionarController();
    protected final transient ResumoFinanceiroController resumoFinanceiroController = new ResumoFinanceiroController();
    protected static final int ALTURA_PAINEL_CIMA = 250;
    protected static final int ALTURA_PAINEL_BAIXO = 473;
    private static final int ALTURA_FOOTER = 50;
    protected JPanel painelCima;
    protected JPanel painelBaixo;
    protected final transient Formulario formulario = new Formulario(325, 50, 400, 500);
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

    protected Border getBorders() {
        Border innerBorder = BorderFactory.createLineBorder(Color.BLACK, 1, true);

        Border outerBorder = BorderFactory.createEmptyBorder(5, 15, 20, 0);

        return BorderFactory.createCompoundBorder(outerBorder, innerBorder);
    }

    protected JPanel getPainelInfo(String label, String info) {
        JPanel painelInfo = new JPanel(new BorderLayout(25, 5));

        JLabel labelDaInfo = new JLabel(label);

        JLabel informacao = new JLabel(info);
        informacao.setFont(informacao.getFont().deriveFont(30f));
        informacao.setBorder(getBorders());
        informacao.setBackground(Color.WHITE);
        informacao.setPreferredSize(new Dimension(500, 75));

        painelInfo.add(labelDaInfo, BorderLayout.NORTH);
        painelInfo.add(informacao, BorderLayout.CENTER);

        return painelInfo;
    }

    protected Formulario getFormularioFiltro(List<CategoriaDto> categorias, FiltroDto filtroDto, boolean utilizaClassificao) {
        CampoData campoDataInicio = new CampoData(TipoInputComponente.DATA_INICIO, "Data início", false);
        CampoData campoDataFim = new CampoData(TipoInputComponente.DATA_FINAL, "Data fim", false);
        ComboBox comboBoxCategoria = new ComboBox(TipoInputComponente.TRANSACAO_CATEGORIA, "Categoria", false);
        ComboBox comboBoxClassificacao = new ComboBox(TipoInputComponente.CLASSFICACAO_TRANSACAO, "Classificação", false);

        comboBoxCategoria.addValorComboBox(TODAS);

        for (CategoriaDto categoriaEncontrada : categorias) {
            comboBoxCategoria.addValorComboBox(categoriaEncontrada.nome());
        }

        comboBoxClassificacao.addValorComboBox(TODAS);

        for (ClassificacaoTransacao classificacaoTransacao : ClassificacaoTransacao.values()) {
            comboBoxClassificacao.addValorComboBox(classificacaoTransacao.getNome());
        }

        if (filtroDto != null) {
            campoDataInicio.setInput(Utils.getData(filtroDto.dataInicio()));
            campoDataFim.setInput(Utils.getData(filtroDto.dataFim()));
            comboBoxCategoria.setInput(filtroDto.categoria() != null ? filtroDto.categoria().nome() : TODAS);
            if (!Objects.equals(filtroDto.classificacao(), TODAS)) {
                comboBoxClassificacao.setInput(filtroDto.classificacao());
            }
        }

        Formulario formularioFiltro = new Formulario(25, 0, 400, 200);
        formularioFiltro.addComponente(campoDataInicio);
        formularioFiltro.addComponente(campoDataFim);
        formularioFiltro.addComponente(comboBoxCategoria);

        if (utilizaClassificao) {
            formularioFiltro.addComponente(comboBoxClassificacao);
        }

        return formularioFiltro;
    }

    protected JPanel getPainelComFormularioFiltro(List<CategoriaDto> categorias, FiltroDto filtroDto, boolean utilizaClassificao) {
        JPanel painelComFormularioFiltro = new JPanel();
        painelComFormularioFiltro.setLayout(null);

        Formulario formulario = getFormularioFiltro(categorias, filtroDto, utilizaClassificao);

        Botao botaoFiltrar = new Botao(new PostActionListener(this.resumoFinanceiroController, formulario));
        botaoFiltrar.setBounds(285, 175, 125, 50);
        botaoFiltrar.setText("Filtrar");

        Botao botaoResetarFiltro = new Botao(new GetActionListener(this.resumoFinanceiroController));
        botaoResetarFiltro.setBounds(40, 175, 125, 50);
        botaoResetarFiltro.setText("Resetar filtro");

        painelComFormularioFiltro.add(formulario.getPanel());
        painelComFormularioFiltro.add(botaoFiltrar);
        painelComFormularioFiltro.add(botaoResetarFiltro);

        return painelComFormularioFiltro;
    }
}
