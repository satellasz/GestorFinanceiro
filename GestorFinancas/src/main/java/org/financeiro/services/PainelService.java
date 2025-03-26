package org.financeiro.services;

import org.financeiro.enums.TipoPainelMenu;
import org.financeiro.views.PainelMenu;
import org.financeiro.views.paineisconteudo.AbstractPainelCentral;

public interface PainelService {
    void limparPainelConteudo();
    void setPainelConteudo(AbstractPainelCentral abstractPainelCentral);
    void setBorderPainelTransicao(PainelMenu painelMenu);
    PainelMenu getPainelTransicao(TipoPainelMenu tipoPainelMenu);
}
