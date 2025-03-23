package org.financeiro.services;

import org.financeiro.enums.TipoPainelTransicao;
import org.financeiro.views.PainelTransicao;
import org.financeiro.views.paineisconteudo.PainelCentral;

public interface PainelService {
    void limparPainelConteudo();
    void setPainelConteudo(PainelCentral painelCentral);
    void setBorderPainelTransicao(PainelTransicao painelTransicao);
    PainelTransicao getPainelTransicao(TipoPainelTransicao tipoPainelTransicao);
}
