package org.financeiro.services.usuario;

import org.financeiro.models.Usuario;

public interface LoginService {
    void login(Usuario usuario);
    void logout();
}
