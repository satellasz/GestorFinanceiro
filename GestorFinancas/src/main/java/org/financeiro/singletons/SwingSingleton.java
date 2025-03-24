package org.financeiro.singletons;

import org.financeiro.views.FramePrincipal;
import org.financeiro.views.PainelMenu;

import javax.swing.*;
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
        return framePrincipal.getPainelConteudo();
    }

    public List<PainelMenu> getPaineisTransicao() {
        return framePrincipal.getPaineisTransicao();
    }
}
