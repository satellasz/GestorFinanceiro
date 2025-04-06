package org.financeiro.componentes.subcomponentes;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ColunaRenderer extends JPanel implements TableCellRenderer {
    private final transient BufferedImage image;

    public ColunaRenderer(Color color, BufferedImage image) {
        this.setBackground(color);
        this.image = image;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        return this;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, 12, 0, 25, 25, this);
        }
    }
}
