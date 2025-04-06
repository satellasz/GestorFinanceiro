package org.financeiro.singletons;

import org.financeiro.models.Categoria;

import java.util.ArrayList;
import java.util.List;

public class CategoriaSingleton {
    private static CategoriaSingleton instance = null;
    private final List<Categoria> categorias;

    private CategoriaSingleton() {
        this.categorias = new ArrayList<>();
    }

    public static synchronized CategoriaSingleton getInstance() {
        if (instance == null) {
            instance = new CategoriaSingleton();
        }

        return instance;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }
}
