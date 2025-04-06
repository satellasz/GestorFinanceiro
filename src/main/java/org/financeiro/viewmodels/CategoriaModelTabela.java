package org.financeiro.viewmodels;

import org.financeiro.dtos.CategoriaDto;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class CategoriaModelTabela extends AbstractTableModel {
    private final transient List<CategoriaDto> categoriaList;

    public CategoriaModelTabela(List<CategoriaDto> categoriaList) {
        this.categoriaList = categoriaList;
    }

    private final String[] colunmas = {
            "Id", "Nome", "Descrição", "Editar", "Excluir"
    };

    @Override
    public int getRowCount() {
        return this.categoriaList.size();
    }

    @Override
    public int getColumnCount() {
        return this.colunmas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        CategoriaDto categoria = this.categoriaList.get(rowIndex);

        return switch (columnIndex) {
            case 0 -> categoria.id();
            case 1 -> categoria.nome();
            case 2 -> categoria.descricao();
            case 3 -> "Editar";
            case 4 -> "Excluir";
            default -> null;
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
