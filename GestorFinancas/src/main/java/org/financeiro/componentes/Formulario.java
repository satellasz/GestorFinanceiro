package org.financeiro.componentes;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Formulario {
    private final List<AbstractInputComponente> componentes;
    private final JPanel jPanel;
    private int idObjeto;

    public Formulario(int dimensionX, int dimensionY, int width, int height) {
        this.componentes = new ArrayList<>();
        this.jPanel = new JPanel();
        this.jPanel.setBounds(dimensionX, dimensionY, width, height);
    }

    public void addComponente(AbstractInputComponente abstractInputComponenteComponent) {
        componentes.add(abstractInputComponenteComponent);
        this.jPanel.add(abstractInputComponenteComponent.getPanel());
    }

    public List<AbstractInputComponente> getComponentes() {
        return componentes;
    }

    public JPanel getPanel() {
        return jPanel;
    }

    public int getIdObjeto() {
        return idObjeto;
    }

    public void setIdObjeto(int idObjeto) {
        this.idObjeto = idObjeto;
    }
}
