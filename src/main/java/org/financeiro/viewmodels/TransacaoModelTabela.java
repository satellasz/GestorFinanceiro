package org.financeiro.viewmodels;

import org.financeiro.dtos.TransacaoDto;
import org.financeiro.utils.Utils;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TransacaoModelTabela extends AbstractTableModel {
    private final transient List<TransacaoDto> transacaoList;

    public TransacaoModelTabela(List<TransacaoDto> transacaoList) {
        this.transacaoList = transacaoList;
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
        TransacaoDto transacao = this.transacaoList.get(rowIndex);

        return switch (columnIndex) {
            case 0 -> transacao.id();
            case 1 -> transacao.valor();
            case 2 -> transacao.descricao();
            case 3 -> transacao.categoriaDto().nome();
            case 4 -> transacao.classificacao().getNome();
            case 5 -> Utils.getData(transacao.dataTransacao());
            case 6 -> "Editar";
            case 7 -> "Excluir";
            default -> "";
        };
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
