package org.financeiro.services.usuario;

import org.financeiro.dtos.UsuarioDto;

public class LoginServiceImpl implements LoginService {
    private final UsuarioService usuarioService = new UsuarioServiceImpl();
    @Override
    public void login(UsuarioDto usuario) {
        this.usuarioService.setUsuarioLogado(usuario);
    }

    @Override
    public void logout() {
        this.usuarioService.setUsuarioLogado(null);
    }
}
