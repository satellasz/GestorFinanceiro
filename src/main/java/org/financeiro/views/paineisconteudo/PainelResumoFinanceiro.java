package org.financeiro.views.paineisconteudo;

import java.awt.*;

public class PainelResumoFinanceiro extends AbstractPainelCentral {
    @Override
    public void onLoad() {
        this.painelService.setProcessoEmAndamento(false);
        this.add(getPainelCima(), BorderLayout.NORTH);
        this.add(getPainelBaixo(), BorderLayout.SOUTH);
    }
}
