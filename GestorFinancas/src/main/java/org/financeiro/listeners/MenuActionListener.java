package org.financeiro.listeners;

import org.financeiro.controllers.AbstractController;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MenuActionListener implements MouseListener {
    private final AbstractController abstractController;

    public MenuActionListener(AbstractController abstractController) {
        this.abstractController = abstractController;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // Não será implementado
    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.abstractController.get();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Não será implementado
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO colocar algo (cor ou borda) para realçar
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO voltar para o estilo original
    }
}
