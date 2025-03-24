package org.financeiro.componentes;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Formulario {
    private final List<Input> componentes;
    private final JPanel jPanel;

    public Formulario(int dimensionX, int dimensionY, int width, int height) {
        this.componentes = new ArrayList<>();
        this.jPanel = new JPanel();
        this.jPanel.setBounds(dimensionX, dimensionY, width, height);
    }

    public void addComponente(Input componente) {
        componentes.add(componente);
        this.jPanel.add((Component) componente);
    }

    public List<Input> getComponentes() {
        return componentes;
    }

    public JPanel getPanel() {
        return jPanel;
    }
}
