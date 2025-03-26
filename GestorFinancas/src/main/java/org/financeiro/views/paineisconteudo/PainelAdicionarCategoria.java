package org.financeiro.views.paineisconteudo;

import org.financeiro.componentes.Botao;
import org.financeiro.componentes.CampoTexto;
import org.financeiro.componentes.CampoTextoArea;
import org.financeiro.enums.TipoCampoTexto;
import org.financeiro.enums.TipoInputComponente;
import org.financeiro.listeners.PostActionListener;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class PainelAdicionarCategoria extends AbstractPainelCentral {
    @Override
    public void onLoad() {
        this.add(getPainelAdicionarCategoria(), BorderLayout.CENTER);
    }

    private JPanel getPainelAdicionarCategoria() {
        JPanel painel = new JPanel();
        painel.setLayout(null);
        painel.setBorder(new LineBorder(Color.black, 2));

        CampoTexto campoTextoNome = new CampoTexto(TipoInputComponente.NOME_CATEGORIA, "Nome", TipoCampoTexto.TEXTO, true);
        CampoTextoArea campoTextoDescricao = new CampoTextoArea(TipoInputComponente.DESCRICAO_CATEGORIA, "Descrição", TipoCampoTexto.TEXTO, false);

        this.formulario.addComponente(campoTextoNome);
        this.formulario.addComponente(campoTextoDescricao);

        Botao botaoSalvar = new Botao(new PostActionListener(this.categoriaAdicionarController, this.formulario));
        botaoSalvar.setText("Salvar");
        botaoSalvar.setBounds(500, 500, 100, 50);

        painel.add(botaoSalvar);
        painel.add(formulario.getPanel());
        return painel;
    }
}
