package org.financeiro.componentes.subcomponentes;

import org.financeiro.controllers.AbstractController;
import org.financeiro.enums.TipoColunaTabela;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ColunaEditor extends AbstractCellEditor implements TableCellEditor {
    private final JPanel painel;
    private final TipoColunaTabela nomeColuna;
    private JTable tabela;
    private int rowIndex;

    public ColunaEditor(TipoColunaTabela nomeColuna, AbstractController abstractController) {
        this.nomeColuna = nomeColuna;

        this.painel = new JPanel();

        painel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                fireEditingStopped();
                if (tabela != null) {
                    Object valorPrimeiraColuna = tabela.getValueAt(rowIndex, 0);

                    if (valorPrimeiraColuna instanceof Integer id) {
                        if (nomeColuna == TipoColunaTabela.EDITAR) {
                            abstractController.patch(id);
                        } else {
                            abstractController.delete(id);
                        }
                    }
                }
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        this.tabela = table;
        this.rowIndex = row;
        return painel;
    }

    @Override
    public Object getCellEditorValue() {
        return nomeColuna.getNome();
    }
}
