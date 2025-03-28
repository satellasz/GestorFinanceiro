package org.financeiro.enums;

import static org.financeiro.enums.TipoPainelMenu.*;

public enum PathResources {
    ICON_DELETE("Delete", "/icons/delete.png"),
    ICON_EDIT("Edit", "/icons/edit.png"),
    ICON_PAINEL_TRANSACOES(TRANSACOES.getNome(), "/icons/transacoes.png"),
    ICON_CATEGORIAS(CATEGORIAS.getNome(), "/icons/category.png"),
    ICON_RESUMO(RESUMO.getNome(), "/icons/resumo.png"),
    ICON_PERFIL(PERFIL.getNome(), ""),
    BACKGROUND_LOGIN ("Login", "/imagens/background_login.jpg");

    private final String path;
    private final String nome;

    PathResources(final String nome, final String path) {
        this.nome = nome;
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public String getNome() {
        return nome;
    }
}
