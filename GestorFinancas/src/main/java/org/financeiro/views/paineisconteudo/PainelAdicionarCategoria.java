package org.financeiro.views.paineisconteudo;

import org.financeiro.componentes.CampoTexto;
import org.financeiro.componentes.CampoTextoArea;
import org.financeiro.enums.TipoCampoTexto;
import org.financeiro.enums.TipoInputComponente;
import org.financeiro.models.Categoria;

import javax.swing.*;
import java.awt.*;

public class PainelAdicionarCategoria extends AbstractPainelCentral {
    private transient Categoria categoria = null;

    public PainelAdicionarCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public PainelAdicionarCategoria() {
    }

    @Override
    public void onLoad() {
        this.add(getPainelAdicionarCategoria(), BorderLayout.CENTER);
        this.painelService.setProcessoEmAndamento(true);

        String label = "Adicionando categoria";

        if (this.categoria != null) {
            label = "Editando categoria";
        }

        this.add(getPainelFooter(label), BorderLayout.SOUTH);
    }

    private JPanel getPainelAdicionarCategoria() {
        JPanel painel = new JPanel();
        painel.setLayout(null);

        CampoTexto campoTextoNome = new CampoTexto(TipoInputComponente.NOME_CATEGORIA, "Nome", TipoCampoTexto.TEXTO, true);
        CampoTextoArea campoTextoDescricao = new CampoTextoArea(TipoInputComponente.DESCRICAO_CATEGORIA, "Descrição", TipoCampoTexto.TEXTO, false);

        if (categoria != null) {
            campoTextoNome.setInput(categoria.getNome());
            campoTextoDescricao.setInput(categoria.getDescricao());
            this.formulario.setIdObjeto(categoria.getId());
        }

        this.formulario.addComponente(campoTextoNome);
        this.formulario.addComponente(campoTextoDescricao);
        this.formulario.setHeight(400);

        painel.add(this.getBotaoCancelar(this.categoriasController));
        painel.add(this.getBotaoSalvar(this.categoriaAdicionarController));
        painel.add(this.formulario.getPanel());
        return painel;
    }
}
