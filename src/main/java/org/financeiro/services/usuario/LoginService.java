package org.financeiro.services.usuario;

import org.financeiro.dtos.UsuarioDto;

public interface LoginService {
    void login(UsuarioDto usuario);
    void logout();
}
