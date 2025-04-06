package org.financeiro.componentes;

import javax.swing.*;
import java.awt.event.ActionListener;

public class Botao extends JButton {
    public Botao(ActionListener a) {
        this.addActionListener(a);
    }
}
