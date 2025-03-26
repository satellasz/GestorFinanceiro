package org.financeiro.enums;

public enum TipoInputComponente {
    NOME_CATEGORIA ("NOME_CATEGORIA"),
    DESCRICAO_CATEGORIA ("DESCRICAO_CATEGORIA"),
    VALOR_TRANSACAO ("VALOR_TRANSACAO"),
    TRANSACAO_CATEGORIA ("TRANSACAO_CATEGORIA"),
    DATA_TRANSACAO ("DATA_TRANSACAO"),
    CLASSFICACAO_TRANSACAO ("CLASSFICACAO_TRANSACAO");

    private final String id;

    TipoInputComponente(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
