package org.financeiro.listeners;

import org.financeiro.controllers.Controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TransicaoActionListener implements MouseListener {
    private final Controller controller;

    public TransicaoActionListener(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // Não será implementado
    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.controller.get();
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
