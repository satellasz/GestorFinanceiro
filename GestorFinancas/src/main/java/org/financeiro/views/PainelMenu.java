package org.financeiro.views;

import org.financeiro.controllers.AbstractController;
import org.financeiro.enums.TipoPainelMenu;
import org.financeiro.listeners.MenuActionListener;
import org.financeiro.singletons.ImageSingleton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PainelMenu extends JPanel {
    private final int borderTop;
    private final int borderBottom;
    private final TipoPainelMenu tipoPainelMenu;
    private final transient BufferedImage image;

    public PainelMenu(TipoPainelMenu tipoPainelMenu, Color cor, AbstractController abstractController, int borderTop, int borderBottom) {
        JLabel jLabel = new JLabel(tipoPainelMenu.getNome().toUpperCase());
        jLabel.setBorder(new EmptyBorder(10, 50, 10, 50));
        this.add(jLabel);
        this.setPreferredSize(new Dimension(192, 180));
        this.setBackground(cor);
        setBorderNormal();

        this.addMouseListener(new MenuActionListener(abstractController, this));
        this.borderTop = borderTop;
        this.borderBottom = borderBottom;
        this.tipoPainelMenu = tipoPainelMenu;
        this.image = ImageSingleton.getInstance().getImage(tipoPainelMenu.getNome());
    }

    public void setBorderNormal() {
        this.setBorder(BorderFactory.createMatteBorder(borderTop, 0, borderBottom, 1, Color.BLACK));
    }

    public TipoPainelMenu getTipoPainelMenu() {
        return tipoPainelMenu;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, 70, 75, 50, 50, this);
        }
    }
}
