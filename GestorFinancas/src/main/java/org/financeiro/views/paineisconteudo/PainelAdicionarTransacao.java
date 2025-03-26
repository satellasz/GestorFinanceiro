package org.financeiro.views.paineisconteudo;

import org.financeiro.componentes.*;
import org.financeiro.enums.ClassificacaoTransacao;
import org.financeiro.enums.TipoCampoTexto;
import org.financeiro.enums.TipoInputComponente;
import org.financeiro.listeners.PostActionListener;
import org.financeiro.models.Categoria;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class PainelAdicionarTransacao extends AbstractPainelCentral {
    @Override
    public void onLoad() {
        this.add(getPainelAdicionarTransacao(), BorderLayout.CENTER);
    }

    private JPanel getPainelAdicionarTransacao() {
        JPanel painel = new JPanel();
        painel.setLayout(null);
        painel.setBorder(new LineBorder(Color.black, 2));

        CampoTexto campoTextoValor = new CampoTexto(TipoInputComponente.VALOR_TRANSACAO, "Valor da transação", TipoCampoTexto.NUMERO, true);
        ComboBox comboBoxCategoria = new ComboBox(TipoInputComponente.TRANSACAO_CATEGORIA,"Categoria", true);
        ComboBox comboBoxClassificacao = new ComboBox(TipoInputComponente.CLASSFICACAO_TRANSACAO,"Classificação", true);
        CampoTextoArea campoTextoDescricao = new CampoTextoArea(TipoInputComponente.DESCRICAO_CATEGORIA, "Descrição", TipoCampoTexto.TEXTO, false);
        CampoData campoData = new CampoData(TipoInputComponente.DATA_TRANSACAO, "Data", true);

        for (Categoria categoria : this.categoriaService.listarCategorias()) {
            comboBoxCategoria.addValorComboBox(categoria.getNome());
        }

        for (ClassificacaoTransacao classificacaoTransacao : ClassificacaoTransacao.values()) {
            comboBoxClassificacao.addValorComboBox(classificacaoTransacao.getNome());
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
