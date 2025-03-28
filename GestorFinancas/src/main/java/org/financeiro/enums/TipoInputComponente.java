package org.financeiro.enums;

public enum TipoInputComponente {
    NOME_CATEGORIA ("NOME_CATEGORIA"),
    DESCRICAO_CATEGORIA ("DESCRICAO_CATEGORIA"),
    VALOR_TRANSACAO ("VALOR_TRANSACAO"),
    TRANSACAO_CATEGORIA ("TRANSACAO_CATEGORIA"),
    DATA_TRANSACAO ("DATA_TRANSACAO"),
    CLASSFICACAO_TRANSACAO ("CLASSFICACAO_TRANSACAO"),
    LOGIN_USER ("LOGIN_USER"),
    LOGIN_SENHA ("LOGIN_SENHA"),
    USUARIO_NOME ("USUARIO_NOME"),
    USUARIO_SENHA ("USUARIO_SENHA"),
    USUARIO_CONFIRMAR_SENHA ("USUARIO_CONFIRMAR_SENHA"),
    USUARIO_EMAIL ("USUARIO_EMAIL");

    private final String id;

    TipoInputComponente(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
