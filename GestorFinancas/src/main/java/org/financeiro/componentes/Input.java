package org.financeiro.componentes;

public interface Input {
    // TODO trocar essa interface para uma classe abstract que extenda de JComponent
    String getInput();
    boolean isObrigatorio();
    String getMensagemErro();
}
