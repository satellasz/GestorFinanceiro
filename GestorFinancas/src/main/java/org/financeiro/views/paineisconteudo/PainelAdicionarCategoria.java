package org.financeiro.views.paineisconteudo;

import org.financeiro.componentes.Botao;
import org.financeiro.componentes.CampoTexto;
import org.financeiro.componentes.Formulario;
import org.financeiro.controllers.CategoriaAdicionarController;
import org.financeiro.enums.TipoCampoTexto;
import org.financeiro.listeners.PostActionListener;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class PainelAdicionarCategoria extends PainelCentral {
    @Override
    public void onLoad() {
        this.add(getPainelAdicionarCategoria(), BorderLayout.CENTER);
    }

    private JPanel getPainelAdicionarCategoria() {
        JPanel painel = new JPanel();
        painel.setLayout(null);
        painel.setBorder(new LineBorder(Color.black, 2));

        Formulario formulario = new Formulario(150, 50, 800, 500);
        CampoTexto campoTextoNome = new CampoTexto(1, "Nome", TipoCampoTexto.TEXTO, "Nome da categoria é obrigatório", true);
        CampoTexto campoTextoDescricao = new CampoTexto(1, "Descricao", TipoCampoTexto.TEXTO);

        formulario.addComponente(campoTextoNome);
        formulario.addComponente(campoTextoDescricao);

        Botao botaoSalvar = new Botao(new PostActionListener(new CategoriaAdicionarController(), formulario));
        botaoSalvar.setText("Salvar");
        botaoSalvar.setBounds(500, 500, 100, 50);

        painel.add(botaoSalvar);
        painel.add(formulario.getPanel());
        return painel;
    }
}
