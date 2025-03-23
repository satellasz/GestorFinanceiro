package org.financeiro.listeners;

import org.financeiro.controllers.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GetActionListener implements ActionListener {
    private final Controller controller;

    public GetActionListener(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.controller.get();
    }
}
