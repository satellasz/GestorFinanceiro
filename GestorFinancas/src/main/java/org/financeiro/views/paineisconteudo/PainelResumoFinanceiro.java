package org.financeiro.views.paineisconteudo;

import java.awt.*;

public class PainelResumoFinanceiro extends AbstractPainelCentral {
    @Override
    public void onLoad() {
        this.add(getPainelCima(), BorderLayout.NORTH);
        this.add(getPainelBaixo(), BorderLayout.SOUTH);
    }
}
