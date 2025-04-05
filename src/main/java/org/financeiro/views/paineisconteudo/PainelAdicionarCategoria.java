package org.financeiro.views.paineisconteudo;

import org.financeiro.componentes.CampoTexto;
import org.financeiro.componentes.CampoTextoArea;
import org.financeiro.dtos.CategoriaDto;
import org.financeiro.enums.TipoCampoTexto;
import org.financeiro.enums.TipoInputComponente;

import javax.swing.*;
import java.awt.*;

public class PainelAdicionarCategoria extends AbstractPainelCentral {
    private transient CategoriaDto categoria = null;

    public PainelAdicionarCategoria(CategoriaDto categoria) {
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
            campoTextoNome.setInput(categoria.nome());
            campoTextoDescricao.setInput(categoria.descricao());
            this.formulario.setIdObjeto(categoria.id());
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
