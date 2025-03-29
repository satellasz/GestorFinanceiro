package org.financeiro;

import org.financeiro.exceptions.CadastroContaException;
import org.financeiro.models.Usuario;
import org.financeiro.services.ImageService;
import org.financeiro.services.ImageServiceImpl;
import org.financeiro.services.usuario.UsuarioService;
import org.financeiro.services.usuario.UsuarioServiceImpl;
import org.financeiro.singletons.SwingSingleton;

public class Main {
    protected static final ImageService imageService = new ImageServiceImpl();
    public static void main(String[] args) throws CadastroContaException {
        Usuario usuario = new Usuario("a", "a");

        UsuarioService usuarioService= new UsuarioServiceImpl();

        usuarioService.cadastrarUsuario(usuario);
        imageService.loadImagens();
        SwingSingleton.getInstance();
    }
}