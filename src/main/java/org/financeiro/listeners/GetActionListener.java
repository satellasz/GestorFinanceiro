package org.financeiro.listeners;

import org.financeiro.controllers.AbstractController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GetActionListener implements ActionListener {
    private final AbstractController abstractController;

    public GetActionListener(AbstractController abstractController) {
        this.abstractController = abstractController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.abstractController.get();
    }
}
