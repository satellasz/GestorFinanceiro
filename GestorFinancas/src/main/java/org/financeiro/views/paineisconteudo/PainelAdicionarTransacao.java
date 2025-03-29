package org.financeiro.views.paineisconteudo;

import org.financeiro.componentes.*;
import org.financeiro.enums.ClassificacaoTransacao;
import org.financeiro.enums.TipoCampoTexto;
import org.financeiro.enums.TipoInputComponente;
import org.financeiro.listeners.PostActionListener;
import org.financeiro.models.Categoria;
import org.financeiro.models.Transacao;
import org.financeiro.utils.Utils;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class PainelAdicionarTransacao extends AbstractPainelCentral {
    private transient Transacao transacao = null;
    private transient Categoria categoria = null;

    public PainelAdicionarTransacao(Transacao transacao, Categoria categoria) {
        this.transacao = transacao;
        this.categoria = categoria;
    }

    public PainelAdicionarTransacao() {
    }

    @Override
    public void onLoad() {
        this.add(getPainelAdicionarTransacao(), BorderLayout.CENTER);
    }

    private JPanel getPainelAdicionarTransacao() {
        JPanel painel = new JPanel();
        painel.setLayout(null);
        painel.setBorder(new LineBorder(Color.black, 2));

        CampoTexto campoTextoValor = new CampoTexto(TipoInputComponente.VALOR_TRANSACAO, "Valor da transação", TipoCampoTexto.NUMERO, true);
        ComboBox comboBoxCategoria = new ComboBox(TipoInputComponente.TRANSACAO_CATEGORIA, "Categoria", true);
        ComboBox comboBoxClassificacao = new ComboBox(TipoInputComponente.CLASSFICACAO_TRANSACAO, "Classificação", true);
        CampoTextoArea campoTextoDescricao = new CampoTextoArea(TipoInputComponente.DESCRICAO_CATEGORIA, "Descrição", TipoCampoTexto.TEXTO, false);
        CampoData campoData = new CampoData(TipoInputComponente.DATA_TRANSACAO, "Data", true);

        for (Categoria categoriaEncontrada : this.categoriaService.listarCategorias()) {
            comboBoxCategoria.addValorComboBox(categoriaEncontrada.getNome());
        }

        for (ClassificacaoTransacao classificacaoTransacao : ClassificacaoTransacao.values()) {
            comboBoxClassificacao.addValorComboBox(classificacaoTransacao.getNome());
        }

        if (this.transacao != null) {
            campoTextoValor.setInput(String.valueOf(this.transacao.getValor()));
            comboBoxCategoria.setInput(this.categoria.getNome());
            comboBoxClassificacao.setInput(this.transacao.getClassificacao().getNome());
            campoTextoDescricao.setInput(this.transacao.getDescricao());
            campoData.setInput(Utils.getData(transacao.getDataTransacao()));
            this.formulario.setIdObjeto(transacao.getId());
        }

        this.formulario.addComponente(campoTextoValor);
        this.formulario.addComponente(comboBoxCategoria);
        this.formulario.addComponente(comboBoxClassificacao);
        this.formulario.addComponente(campoTextoDescricao);
        this.formulario.addComponente(campoData);

        Botao botaoSalvar = new Botao(new PostActionListener(this.transacaoAdicionarController, this.formulario));
        botaoSalvar.setText("Salvar");
        botaoSalvar.setBounds(500, 500, 100, 50);

        painel.add(botaoSalvar);
        painel.add(formulario.getPanel());

        return painel;
    }
}
