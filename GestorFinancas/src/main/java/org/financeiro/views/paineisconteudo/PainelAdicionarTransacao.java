package org.financeiro.views.paineisconteudo;

import org.financeiro.componentes.Botao;
import org.financeiro.componentes.CampoTexto;
import org.financeiro.componentes.CampoTextoArea;
import org.financeiro.componentes.ComboBox;
import org.financeiro.controllers.CategoriaAdicionarController;
import org.financeiro.controllers.TransacaoAdicionarController;
import org.financeiro.enums.TipoCampoTexto;
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

        CampoTexto campoTextoNome = new CampoTexto("Nome", TipoCampoTexto.TEXTO, true);
        CampoTextoArea campoTextoDescricao = new CampoTextoArea("Descricao", TipoCampoTexto.TEXTO, false);
        ComboBox comboBoxCategoria = new ComboBox("Categoria", true);

        for (Categoria categoria : this.categoriaService.listarCategorias()) {
            comboBoxCategoria.addValorComboBox(categoria.getNome());
        }

        this.formulario.addComponente(campoTextoNome);
        this.formulario.addComponente(campoTextoDescricao);
        this.formulario.addComponente(comboBoxCategoria);

        Botao botaoSalvar = new Botao(new PostActionListener(new TransacaoAdicionarController(), this.formulario));
        botaoSalvar.setText("Salvar");
        botaoSalvar.setBounds(500, 500, 100, 50);

        painel.add(botaoSalvar);
        painel.add(formulario.getPanel());

        return painel;
    }
}
