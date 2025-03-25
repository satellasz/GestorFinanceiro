package org.financeiro.viewmodels;

import org.financeiro.models.Categoria;
import org.financeiro.repositories.categoria.CategoriaRepositoryImpl;
import org.financeiro.services.categoria.CategoriaService;
import org.financeiro.services.categoria.CategoriaServiceImpl;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class CategoriaModelTabela extends AbstractTableModel {
    private final transient List<Categoria> categoriaList;

    public CategoriaModelTabela() {
        CategoriaService categoriaService = new CategoriaServiceImpl(new CategoriaRepositoryImpl());
        this.categoriaList = categoriaService.listarCategorias();
    }

    private final String[] colunmas = {
            "Nome", "Descrição", "Editar", "Excluir"
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
        Categoria categoria = this.categoriaList.get(rowIndex);

        return switch (columnIndex) {
            case 0 -> categoria.getNome();
            case 1 -> categoria.getDescricao();
            case 2 -> "Editar";
            case 3 -> "Excluir";
            default -> null;
        };
    }

    @Override
    public String getColumnName(int column) {
        return colunmas[column];
    }

}
