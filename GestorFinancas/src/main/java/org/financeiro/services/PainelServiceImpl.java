package org.financeiro.services;

import org.financeiro.enums.TipoPainelTransicao;
import org.financeiro.singletons.PainelSingleton;
import org.financeiro.views.PainelTransicao;
import org.financeiro.views.paineisconteudo.PainelCentral;

import javax.swing.border.LineBorder;
import java.awt.*;

public class PainelServiceImpl implements PainelService {
    @Override
    public void limparPainelConteudo() {
        PainelSingleton.getInstance().getPainelConteudo().removeAll();
    }

    @Override
    public void setPainelConteudo(PainelCentral painelCentral) {
        limparPainelConteudo();
        PainelSingleton.getInstance().getPainelConteudo().add(painelCentral);
        PainelSingleton.getInstance().getPainelConteudo().updateUI();
    }

    @Override
    public void setBorderPainelTransicao(PainelTransicao painelTransicao) {
        for (PainelTransicao painel : PainelSingleton.getInstance().getPaineisTransicao()) {
            if (painel == painelTransicao) {
                painel.setBorder(new LineBorder(Color.DARK_GRAY, 2, true));
            } else {
                painel.setBorderNormal();
            }
        }
    }

    @Override
    public PainelTransicao getPainelTransicao(TipoPainelTransicao tipoPainelTransicao) {
        return PainelSingleton.getInstance().getPaineisTransicao().stream().filter(x -> x.getTipoPainelTransicao() == tipoPainelTransicao).findFirst().orElse(null);
    }
}
