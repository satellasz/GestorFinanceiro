package org.financeiro.listeners;

import org.financeiro.controllers.AbstractController;
import org.financeiro.services.PainelService;
import org.financeiro.services.PainelServiceImpl;
import org.financeiro.views.PainelMenu;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MenuActionListener implements MouseListener {
    private final AbstractController abstractController;
    private final PainelMenu painelMenu;
    private final PainelService painelService = new PainelServiceImpl();

    public MenuActionListener(AbstractController abstractController, PainelMenu painelMenu) {
        this.abstractController = abstractController;
        this.painelMenu = painelMenu;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // Não será implementado
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (this.painelService.isPainelMenuAtualDiferente(painelMenu)) {
            if (this.painelService.isProcessoEmAndamento()) {
                int opcao = JOptionPane.showConfirmDialog(null, "Todas as alterações serão perdidas", "Deseja realmente sair?", JOptionPane.YES_NO_OPTION);

                if (opcao == JOptionPane.YES_OPTION) {
                    this.abstractController.get();
                    this.painelService.setPainelMenuAtual(painelMenu);
                    this.painelService.setProcessoEmAndamento(false);
                }
            } else {
                this.abstractController.get();
                this.painelService.setPainelMenuAtual(painelMenu);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Não será implementado
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Não será implementado
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Não será implementado
    }
}
