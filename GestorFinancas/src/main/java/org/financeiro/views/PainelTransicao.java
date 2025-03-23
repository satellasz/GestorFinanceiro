package org.financeiro.views;

import org.financeiro.controllers.Controller;
import org.financeiro.enums.TipoPainelTransicao;
import org.financeiro.listeners.TransicaoActionListener;
import org.financeiro.services.ImageService;
import org.financeiro.services.ImageServiceImpl;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PainelTransicao extends JPanel {
    private final int borderTop;
    private final int borderBottom;
    private final TipoPainelTransicao tipoPainelTransicao;
    private final transient BufferedImage image;

    public PainelTransicao(TipoPainelTransicao tipoPainelTransicao, Color cor, Controller controller, int borderTop, int borderBottom) {
        JLabel jLabel = new JLabel(tipoPainelTransicao.getNome().toUpperCase());
        jLabel.setBorder(new EmptyBorder(10, 50, 10, 50));
        this.add(jLabel);
        this.setPreferredSize(new Dimension(192, 180));
        this.setBackground(cor);
        setBorderNormal();

        this.addMouseListener(new TransicaoActionListener(controller));
        this.borderTop = borderTop;
        this.borderBottom = borderBottom;
        this.tipoPainelTransicao = tipoPainelTransicao;
        ImageService imageService = new ImageServiceImpl();
        this.image = imageService.getImage(tipoPainelTransicao.getPathImagem());
    }

    public void setBorderNormal() {
        this.setBorder(BorderFactory.createMatteBorder(borderTop, 0, borderBottom, 1, Color.BLACK));
    }

    public TipoPainelTransicao getTipoPainelTransicao() {
        return tipoPainelTransicao;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, 70, 75, 50, 50, this);
        }
    }
}
