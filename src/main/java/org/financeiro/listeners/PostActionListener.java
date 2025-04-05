package org.financeiro.listeners;

import org.financeiro.componentes.Formulario;
import org.financeiro.controllers.AbstractController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PostActionListener implements ActionListener {
    private final AbstractController abstractController;
    private final Formulario formulario;

    public PostActionListener(AbstractController abstractController, Formulario formulario) {
        this.abstractController = abstractController;
        this.formulario = formulario;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.abstractController.post(this.formulario);
    }
}
