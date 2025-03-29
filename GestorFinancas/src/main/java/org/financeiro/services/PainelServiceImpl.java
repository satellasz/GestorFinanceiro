package org.financeiro.services;

import org.financeiro.enums.TipoPainelMenu;
import org.financeiro.singletons.SwingSingleton;
import org.financeiro.views.PainelMenu;
import org.financeiro.views.paineisconteudo.AbstractPainelCentral;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class PainelServiceImpl implements PainelService {
    @Override
    public void limparPainelConteudo() {
        SwingSingleton.getInstance().getPainelConteudo().removeAll();

        System.gc();
    }

    @Override
    public void limparContainer() {
        SwingSingleton.getInstance().getContainerConteudo().removeAll();
    }

    @Override
    public void setContainerConteudo(JPanel painel) {
        SwingUtilities.invokeLater(() -> {
            limparContainer();
            SwingSingleton.getInstance().getContainerConteudo().add(painel);
            SwingSingleton.getInstance().getContainerConteudo().revalidate();
            SwingSingleton.getInstance().getContainerConteudo().repaint();
        });
    }

    @Override
    public void setPainelConteudo(AbstractPainelCentral abstractPainelCentral) {
        SwingUtilities.invokeLater(() -> {
            limparPainelConteudo();
            SwingSingleton.getInstance().getPainelConteudo().add(abstractPainelCentral);
            SwingSingleton.getInstance().getPainelConteudo().revalidate();
            SwingSingleton.getInstance().getPainelConteudo().repaint();
            abstractPainelCentral.onLoad();
        });
    }

    @Override
    public void setBorderPainelTransicao(PainelMenu painelMenu) {
        for (PainelMenu painel : SwingSingleton.getInstance().getPaineisMenu()) {
            if (painel == painelMenu) {
                painel.setBorder(new LineBorder(Color.DARK_GRAY, 2, true));
            } else {
                painel.setBorderNormal();
            }
        }
    }

    @Override
    public PainelMenu getPainelMenu(TipoPainelMenu tipoPainelMenu) {
        return SwingSingleton.getInstance().getPaineisMenu().stream().filter(x -> x.getTipoPainelMenu() == tipoPainelMenu).findFirst().orElse(null);
    }

    @Override
    public PainelMenu getPainelMenuAtual() {
        return SwingSingleton.getInstance().getPainelMenuAtual();
    }

    @Override
    public void setPainelMenuAtual(PainelMenu painelMenu) {
        SwingSingleton.getInstance().setPainelMenuAtual(painelMenu);
    }

    @Override
    public boolean isPainelMenuAtualDiferente(PainelMenu painelMenu) {
        PainelMenu painelMenuAtual = getPainelMenuAtual();

        if (painelMenuAtual == null) return true;

        return painelMenuAtual.getTipoPainelMenu() != painelMenu.getTipoPainelMenu();
    }
}
