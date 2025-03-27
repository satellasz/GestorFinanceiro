package org.financeiro.viewmodels;

import org.financeiro.exceptions.DadoNaoEncontradoException;
import org.financeiro.models.Categoria;
import org.financeiro.models.Transacao;
import org.financeiro.services.categoria.CategoriaService;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TransacaoModelTabela extends AbstractTableModel {
    private final transient List<Transacao> transacaoList;
    private final transient CategoriaService categoriaService;

    public TransacaoModelTabela(List<Transacao> transacaoList, CategoriaService categoriaService) {
        this.transacaoList = transacaoList;
        this.categoriaService = categoriaService;
    }

    private final String[] colunmas = {
            "Id", "Valor", "Descrição", "Categoria", "Classificação", "Data", "Editar", "Excluir"
    };

    @Override
    public int getRowCount() {
        return this.transacaoList.size();
    }

    @Override
    public int getColumnCount() {
        return this.colunmas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            Transacao transacao = this.transacaoList.get(rowIndex);
            Categoria categoria = this.categoriaService.buscarCategoria(transacao.getIdCategoria());

            return switch (columnIndex) {
                case 0 -> transacao.getId();
                case 1 -> transacao.getValor();
                case 2 -> transacao.getDescricao();
                case 3 -> categoria != null ? categoria.getNome() : "";
                case 4 -> transacao.getClassificacao().getNome();
                case 5 -> transacao.getDataTransacao();
                case 6 -> "Editar";
                case 7 -> "Excluir";
                default -> "";
            };
        } catch (DadoNaoEncontradoException e) {
            return "";
        }
    }

    @Override
    public String getColumnName(int column) {
        return colunmas[column];
    }


    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex >= getColumnCount() - 2;
    }
}
