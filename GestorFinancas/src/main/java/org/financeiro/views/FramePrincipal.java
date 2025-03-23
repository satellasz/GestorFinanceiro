package org.financeiro.views;

import org.financeiro.controllers.CategoriasController;
import org.financeiro.controllers.PerfilController;
import org.financeiro.controllers.ResumoController;
import org.financeiro.controllers.TransacoesController;
import org.financeiro.enums.TipoPainelTransicao;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class FramePrincipal extends JFrame {
    private final Container conteinerConteudo = getContentPane();
    private JPanel painelConteudo;
    private final List<PainelTransicao> paineisTransicao = new ArrayList<>();

    public void inicializar() {
        PainelTransicao painelTranscoes = new PainelTransicao(TipoPainelTransicao.TRANSACOES, Color.decode("#779cf2"), new TransacoesController(), 0, 0);
        PainelTransicao painelCategorias = new PainelTransicao(TipoPainelTransicao.CATEGORIAS, Color.decode("#f2bf77"), new CategoriasController(), 1, 0);
        PainelTransicao painelResumo = new PainelTransicao(TipoPainelTransicao.RESUMO, Color.decode("#6ff28e"), new ResumoController(), 1, 0);
        PainelTransicao painelPerfil = new PainelTransicao(TipoPainelTransicao.PERFIL, Color.LIGHT_GRAY, new PerfilController(), 1, 0);

        JPanel painelTransicao = new JPanel();
        painelTransicao.setLayout(new GridLayout(4, 1, 0, 0));
        painelTransicao.add(painelTranscoes);
        painelTransicao.add(painelCategorias);
        painelTransicao.add(painelResumo);
        painelTransicao.add(painelPerfil);

        for (Component p : painelTransicao.getComponents()) {
            paineisTransicao.add((PainelTransicao) p);
        }

        painelConteudo = new JPanel();
        painelConteudo.setLayout(new BorderLayout());

        conteinerConteudo.add(painelTransicao, BorderLayout.WEST);
        conteinerConteudo.add(painelConteudo, BorderLayout.CENTER);

        this.setVisible(true);
        this.setResizable(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(new Dimension(1290, 760));

        this.setLocationRelativeTo(getOwner());
    }

    public JPanel getPainelConteudo() {
        return painelConteudo;
    }

    public List<PainelTransicao> getPaineisTransicao() {
        return paineisTransicao;
    }
}
