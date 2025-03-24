package org.financeiro.views.paineisconteudo;

import org.financeiro.componentes.*;
import org.financeiro.controllers.CategoriaAdicionarController;
import org.financeiro.enums.TipoCampoTexto;
import org.financeiro.listeners.PostActionListener;
import org.financeiro.models.Categoria;
import org.financeiro.repositories.categoria.CategoriaRepositoryImpl;
import org.financeiro.services.categoria.CategoriaService;
import org.financeiro.services.categoria.CategoriaServiceImpl;

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

        CampoTexto campoTextoNome = new CampoTexto("Nome", TipoCampoTexto.TEXTO, true);
        CampoTextoArea campoTextoDescricao = new CampoTextoArea("Descricao", TipoCampoTexto.TEXTO, false);

        this.formulario.addComponente(campoTextoNome);
        this.formulario.addComponente(campoTextoDescricao);

        Botao botaoSalvar = new Botao(new PostActionListener(new CategoriaAdicionarController(), this.formulario));
        botaoSalvar.setText("Salvar");
        botaoSalvar.setBounds(500, 500, 100, 50);

        painel.add(botaoSalvar);
        painel.add(formulario.getPanel());
        return painel;
    }
}
