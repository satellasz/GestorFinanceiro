package org.financeiro.singletons;

import org.financeiro.models.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioSingleton {
    private static UsuarioSingleton instance = null;
    private final List<Usuario> usuarios;
    private Usuario usuarioLogado;

    private UsuarioSingleton() {
        this.usuarios = new ArrayList<>();
        this.usuarioLogado = null;
    }

    public static synchronized UsuarioSingleton getInstance() {
        if (instance == null) {
            instance = new UsuarioSingleton();
        }

        return instance;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }
}
