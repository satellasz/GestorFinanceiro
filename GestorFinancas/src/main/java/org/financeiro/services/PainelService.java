package org.financeiro.services;

import org.financeiro.enums.TipoPainelMenu;
import org.financeiro.views.PainelMenu;
import org.financeiro.views.paineisconteudo.AbstractPainelCentral;

import javax.swing.*;

public interface PainelService {
    void limparPainelConteudo();
    void limparContainer();
    void setContainerConteudo(JPanel painel);
    void setPainelConteudo(AbstractPainelCentral abstractPainelCentral);
    void setBorderPainelTransicao(PainelMenu painelMenu);
    PainelMenu getPainelMenu(TipoPainelMenu tipoPainelMenu);
    PainelMenu getPainelMenuAtual();
    void setPainelMenuAtual(PainelMenu painelMenu);
    boolean isPainelMenuAtualDiferente(PainelMenu painelMenu);
    void setProcessoEmAndamento(boolean processoEmAndamento);
    boolean isProcessoEmAndamento();
}
