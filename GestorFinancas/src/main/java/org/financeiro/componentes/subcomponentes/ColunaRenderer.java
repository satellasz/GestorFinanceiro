package org.financeiro.componentes.subcomponentes;

import org.financeiro.services.ImageService;
import org.financeiro.services.ImageServiceImpl;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ColunaRenderer extends JPanel implements TableCellRenderer {
    private final transient BufferedImage image;

    public ColunaRenderer(Color color, String pathImagem) {
        this.setBackground(color);
        ImageService imageService = new ImageServiceImpl();
        this.image = imageService.getImage(pathImagem);
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
