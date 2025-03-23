package org.financeiro.singletons;

import org.financeiro.views.FramePrincipal;
import org.financeiro.views.PainelTransicao;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class PainelSingleton {
    private static PainelSingleton instance = null;
    private final FramePrincipal framePrincipal;
    private final JPanel painelConteudo;
    private final List<PainelTransicao> paineisTransicao;

    private PainelSingleton() {
        framePrincipal = new FramePrincipal();
        framePrincipal.inicializar();
        this.paineisTransicao = framePrincipal.getPaineisTransicao();

        painelConteudo = framePrincipal.getPainelConteudo();
    }

    public static synchronized PainelSingleton getInstance() {
        if (instance == null) {
            instance = new PainelSingleton();
        }

        return instance;
    }

    public JPanel getPainelConteudo() {
        return painelConteudo;
    }

    public List<PainelTransicao> getPaineisTransicao() {
        return paineisTransicao;
    }
}
