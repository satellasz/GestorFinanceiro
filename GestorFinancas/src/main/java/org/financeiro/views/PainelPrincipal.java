package org.financeiro.views;

import org.financeiro.controllers.CategoriasController;
import org.financeiro.controllers.PerfilController;
import org.financeiro.controllers.ResumoController;
import org.financeiro.controllers.TransacoesController;
import org.financeiro.enums.TipoPainelMenu;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PainelPrincipal extends JPanel {
    private final JPanel painelConteudo;
    private final List<PainelMenu> paineisMenu = new ArrayList<>();

    public PainelPrincipal() {
        this.setLayout(new BorderLayout());

        PainelMenu painelTranscoes = new PainelMenu(TipoPainelMenu.TRANSACOES, Color.decode("#779cf2"), new TransacoesController(), 0, 0);
        PainelMenu painelCategorias = new PainelMenu(TipoPainelMenu.CATEGORIAS, Color.decode("#f2bf77"), new CategoriasController(), 1, 0);
        PainelMenu painelResumo = new PainelMenu(TipoPainelMenu.RESUMO, Color.decode("#6ff28e"), new ResumoController(), 1, 0);
        PainelMenu painelPerfil = new PainelMenu(TipoPainelMenu.PERFIL, Color.LIGHT_GRAY, new PerfilController(), 1, 0);

        JPanel painelTransicao = new JPanel();
        painelTransicao.setLayout(new GridLayout(4, 1, 0, 0));
        painelTransicao.add(painelTranscoes);
        painelTransicao.add(painelCategorias);
        painelTransicao.add(painelResumo);
        painelTransicao.add(painelPerfil);

        for (Component p : painelTransicao.getComponents()) {
            paineisMenu.add((PainelMenu) p);
        }

        painelConteudo = new JPanel();
        painelConteudo.setLayout(new BorderLayout());

        this.add(painelTransicao, BorderLayout.WEST);
        this.add(painelConteudo, BorderLayout.CENTER);
    }

    public JPanel getPainelConteudo() {
        return painelConteudo;
    }

    public List<PainelMenu> getPaineisMenu() {
        return paineisMenu;
    }
}
