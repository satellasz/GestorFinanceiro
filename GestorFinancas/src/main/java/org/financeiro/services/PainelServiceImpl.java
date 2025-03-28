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
            abstractPainelCentral.onLoad();
            SwingSingleton.getInstance().getPainelConteudo().revalidate();
            SwingSingleton.getInstance().getPainelConteudo().repaint();
        });
    }

    @Override
    public void setBorderPainelTransicao(PainelMenu painelMenu) {
        for (PainelMenu painel : SwingSingleton.getInstance().getPaineisTransicao()) {
            if (painel == painelMenu) {
                painel.setBorder(new LineBorder(Color.DARK_GRAY, 2, true));
            } else {
                painel.setBorderNormal();
            }
        }
    }

    @Override
    public PainelMenu getPainelTransicao(TipoPainelMenu tipoPainelMenu) {
        return SwingSingleton.getInstance().getPaineisTransicao().stream().filter(x -> x.getTipoPainelTransicao() == tipoPainelMenu).findFirst().orElse(null);
    }
}
