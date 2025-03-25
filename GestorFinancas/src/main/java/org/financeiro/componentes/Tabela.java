package org.financeiro.componentes;

import org.financeiro.componentes.subcomponentes.ColunaRenderer;
import org.financeiro.services.ImageService;
import org.financeiro.services.ImageServiceImpl;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Tabela {
    private final JPanel painel;

    public Tabela(AbstractTableModel model) {
        this.painel = new JPanel();
        this.painel.setLayout(new BorderLayout());
        JPanel panelPaginacao = new JPanel(new GridLayout(1, 2));
        panelPaginacao.setPreferredSize(new Dimension(0, 25));

        ImageService imageService = new ImageServiceImpl();

        JTable tabela = getTabela(model);

        JScrollPane scrollPane = new JScrollPane(tabela);

        this.painel.add(scrollPane, BorderLayout.CENTER);
        this.painel.add(panelPaginacao, BorderLayout.SOUTH);
    }

    public JPanel getPainel() {
        return painel;
    }

    private JTable getTabela(AbstractTableModel model) {
        JTable tabela = getJTable(model);

        TableColumnModel columnModel = tabela.getColumnModel();

        getColumnComAcao(columnModel, model.getColumnCount() - 2, new ColunaRenderer(Color.YELLOW, "/icons/edit.png"));
        getColumnComAcao(columnModel, model.getColumnCount() - 1, new ColunaRenderer(Color.RED, "/icons/delete.png"));

        tabela.setRowHeight(25);
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabela.getTableHeader().setResizingAllowed(false);
        tabela.getTableHeader().setReorderingAllowed(false);

        return tabela;
    }

    private JTable getJTable(AbstractTableModel model) {
        JTable tabela = new JTable(model);

        tabela.addMouseListener(new MouseAdapter() {
            // TODO tirar o listener da tabela, criar um Editor e colocar o listener na coluna (coluna precisa estar editável)
            @Override
            public void mousePressed(MouseEvent e) {
                JTable target = (JTable) e.getSource();
                int row = target.getSelectedRow();

                for (int column = 0; column < target.getColumnCount(); column++) {
                    // TODO colocar o ID (nome que está sendo único) e passar pro controller pra editar/deletar
                    Object data = target.getValueAt(row, column);
                    System.out.println("Coluna " + column + ": " + data);
                }
            }
        });

        return tabela;
    }

    private void getColumnComAcao(TableColumnModel columnModel, int index, ColunaRenderer colunaRenderer) {
        TableColumn columnEditar = columnModel.getColumn(index);

        columnEditar.setCellRenderer(colunaRenderer);
        columnEditar.setPreferredWidth(50);
        columnEditar.setMaxWidth(50);
    }
}
