package org.financeiro.listeners;

import org.financeiro.componentes.Formulario;
import org.financeiro.controllers.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PostActionListener implements ActionListener {
    private final Controller controller;
    private final Formulario formulario;

    public PostActionListener(Controller controller, Formulario formulario) {
        this.controller = controller;
        this.formulario = formulario;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.controller.post(this.formulario);
    }
}
