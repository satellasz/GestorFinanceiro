package org.financeiro.componentes;

import org.financeiro.componentes.subcomponentes.ColunaEditor;
import org.financeiro.componentes.subcomponentes.ColunaRenderer;
import org.financeiro.controllers.AbstractController;
import org.financeiro.enums.PathResources;
import org.financeiro.enums.TipoColunaTabela;
import org.financeiro.singletons.ImageSingleton;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;

public class Tabela {
    private final JPanel painel;
    private final AbstractController controller;

    public Tabela(AbstractTableModel model, AbstractController abstractController) {
        this.controller = abstractController;

        this.painel = new JPanel();
        this.painel.setLayout(new BorderLayout());
        JPanel panelPaginacao = new JPanel(new GridLayout(1, 2));
        panelPaginacao.setPreferredSize(new Dimension(0, 25));

        JTable tabela = getTabela(model);

        JScrollPane scrollPane = new JScrollPane(tabela);

        this.painel.add(scrollPane, BorderLayout.CENTER);
        this.painel.add(panelPaginacao, BorderLayout.SOUTH);
    }

    private JTable getTabela(AbstractTableModel model) {
        JTable tabela = new JTable(model);

        TableColumnModel columnModel = tabela.getColumnModel();

        int colunaEditar = model.getColumnCount() - 2;
        int colunaExcluir = model.getColumnCount() - 1;

        getColumnComAcao(columnModel, colunaEditar, new ColunaRenderer(Color.YELLOW, ImageSingleton.getInstance().getImage(PathResources.ICON_EDIT.getNome())), new ColunaEditor(TipoColunaTabela.EDITAR, controller));
        getColumnComAcao(columnModel, colunaExcluir, new ColunaRenderer(Color.RED, ImageSingleton.getInstance().getImage(PathResources.ICON_DELETE.getNome())), new ColunaEditor(TipoColunaTabela.EXCLUIR, controller));

        TableColumn colunaIds = columnModel.getColumn(0);
        colunaIds.setMinWidth(0);
        colunaIds.setMaxWidth(0);
        colunaIds.setWidth(0);
        colunaIds.setPreferredWidth(0);
        colunaIds.setResizable(false);

        tabela.setRowHeight(25);
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabela.getTableHeader().setResizingAllowed(false);
        tabela.getTableHeader().setReorderingAllowed(false);

        return tabela;
    }

    private void getColumnComAcao(TableColumnModel columnModel, int index, ColunaRenderer colunaRenderer, ColunaEditor colunaEditor) {
        TableColumn column = columnModel.getColumn(index);

        column.setCellRenderer(colunaRenderer);
        column.setCellEditor(colunaEditor);
        column.setPreferredWidth(50);
        column.setMaxWidth(50);
    }

    public JPanel getPainel() {
        return painel;
    }
}
