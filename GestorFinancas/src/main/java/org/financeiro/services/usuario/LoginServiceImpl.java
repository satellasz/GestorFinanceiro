package org.financeiro.services.usuario;

import org.financeiro.models.Usuario;
import org.financeiro.repositories.usuario.UsuarioRepository;
import org.financeiro.repositories.usuario.UsuarioRepositoryImpl;

public class LoginServiceImpl implements LoginService {
    private final UsuarioRepository usuarioRepository = new UsuarioRepositoryImpl();
    @Override
    public void login(Usuario usuario) {
        this.usuarioRepository.setUsuarioLogado(usuario);
    }

    @Override
    public void logout() {
        this.usuarioRepository.setUsuarioLogado(null);
    }
}
