package org.financeiro.views.paineisconteudo;

import org.financeiro.componentes.CampoData;
import org.financeiro.componentes.CampoTexto;
import org.financeiro.componentes.CampoTextoArea;
import org.financeiro.componentes.ComboBox;
import org.financeiro.dtos.CategoriaDto;
import org.financeiro.dtos.TransacaoDto;
import org.financeiro.dtos.UsuarioDto;
import org.financeiro.enums.ClassificacaoTransacao;
import org.financeiro.enums.TipoCampoTexto;
import org.financeiro.enums.TipoInputComponente;
import org.financeiro.utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PainelAdicionarTransacao extends AbstractPainelCentral {
    private transient TransacaoDto transacao = null;
    private transient CategoriaDto categoria = null;
    private final transient List<CategoriaDto> categorias;

    public PainelAdicionarTransacao(UsuarioDto usuarioDto, TransacaoDto transacao, CategoriaDto categoria, List<CategoriaDto> categorias) {
        super(usuarioDto);
        this.transacao = transacao;
        this.categoria = categoria;
        this.categorias = categorias;
    }

    public PainelAdicionarTransacao(UsuarioDto usuarioDto, List<CategoriaDto> categorias) {
        super(usuarioDto);
        this.categorias = categorias;
    }

    @Override
    public void onLoad() {
        this.add(getPainelAdicionarTransacao(), BorderLayout.CENTER);
        this.painelService.setProcessoEmAndamento(true);

        String label = "Adicionando transação";

        if (this.transacao != null) {
            label = "Editando transação";
        }

        this.add(getPainelFooter(label), BorderLayout.SOUTH);
    }

    private JPanel getPainelAdicionarTransacao() {
        JPanel painel = new JPanel();
        painel.setLayout(null);

        CampoTexto campoTextoValor = new CampoTexto(TipoInputComponente.VALOR_TRANSACAO, "Valor da transação", TipoCampoTexto.NUMERO, true);
        ComboBox comboBoxCategoria = new ComboBox(TipoInputComponente.TRANSACAO_CATEGORIA, "Categoria", true);
        ComboBox comboBoxClassificacao = new ComboBox(TipoInputComponente.CLASSFICACAO_TRANSACAO, "Classificação", true);
        CampoTextoArea campoTextoDescricao = new CampoTextoArea(TipoInputComponente.DESCRICAO_CATEGORIA, "Descrição", TipoCampoTexto.TEXTO, true);
        CampoData campoData = new CampoData(TipoInputComponente.DATA_TRANSACAO, "Data", true);

        for (CategoriaDto categoriaEncontrada : categorias) {
            comboBoxCategoria.addValorComboBox(categoriaEncontrada.nome());
        }

        for (ClassificacaoTransacao classificacaoTransacao : ClassificacaoTransacao.values()) {
            comboBoxClassificacao.addValorComboBox(classificacaoTransacao.getNome());
        }

        if (this.transacao != null) {
            campoTextoValor.setInput(String.valueOf(this.transacao.valor()));
            comboBoxCategoria.setInput(this.categoria.nome());
            comboBoxClassificacao.setInput(this.transacao.classificacao().getNome());
            campoTextoDescricao.setInput(this.transacao.descricao());
            campoData.setInput(Utils.getData(transacao.dataTransacao()));
            this.formulario.setIdObjeto(transacao.id());
        }

        this.formulario.addComponente(campoTextoValor);
        this.formulario.addComponente(comboBoxCategoria);
        this.formulario.addComponente(comboBoxClassificacao);
        this.formulario.addComponente(campoTextoDescricao);
        this.formulario.addComponente(campoData);

        painel.add(this.getBotaoCancelar(this.transacoesController));
        painel.add(this.getBotaoSalvar(this.transacaoAdicionarController));
        painel.add(formulario.getPanel());

        return painel;
    }
}
