package org.financeiro.services;

import org.financeiro.enums.TipoPainelTransicao;
import org.financeiro.singletons.SwingSingleton;
import org.financeiro.views.PainelMenu;
import org.financeiro.views.paineisconteudo.AbstractPainelCentral;

import javax.swing.border.LineBorder;
import java.awt.*;

public class PainelServiceImpl implements PainelService {
    @Override
    public void limparPainelConteudo() {
        SwingSingleton.getInstance().getPainelConteudo().removeAll();
    }

    @Override
    public void setPainelConteudo(AbstractPainelCentral abstractPainelCentral) {
        limparPainelConteudo();
        SwingSingleton.getInstance().getPainelConteudo().add(abstractPainelCentral);
        SwingSingleton.getInstance().getPainelConteudo().updateUI();
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
    public PainelMenu getPainelTransicao(TipoPainelTransicao tipoPainelTransicao) {
        return SwingSingleton.getInstance().getPaineisTransicao().stream().filter(x -> x.getTipoPainelTransicao() == tipoPainelTransicao).findFirst().orElse(null);
    }
}
