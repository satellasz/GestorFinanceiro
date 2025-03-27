package org.financeiro.singletons;

import org.financeiro.views.FramePrincipal;
import org.financeiro.views.PainelMenu;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SwingSingleton {
    private static SwingSingleton instance = null;
    private final FramePrincipal framePrincipal;

    private SwingSingleton() {
        framePrincipal = new FramePrincipal();
        SwingUtilities.invokeLater(framePrincipal::inicializar);
    }

    public static synchronized SwingSingleton getInstance() {
        if (instance == null) {
            instance = new SwingSingleton();
        }

        return instance;
    }

    public JPanel getPainelConteudo() {
        return framePrincipal.getPainelPrincipal().getPainelConteudo();
    }

    public List<PainelMenu> getPaineisTransicao() {
        return framePrincipal.getPainelPrincipal().getPaineisTransicao();
    }

    public Container getContainerConteudo() {
        return framePrincipal.getConteinerConteudo();
    }

    public JPanel getPainelPrincipal() {
        return framePrincipal.getPainelPrincipal();
    }
}
