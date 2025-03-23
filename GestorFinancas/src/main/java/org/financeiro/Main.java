package org.financeiro;

import org.financeiro.models.Categoria;
import org.financeiro.models.Usuario;
import org.financeiro.repositories.categoria.CategoriaRepositoryImpl;
import org.financeiro.repositories.transacao.TransacaoRepositoryImpl;
import org.financeiro.repositories.usuario.UsuarioRepositoryImpl;
import org.financeiro.services.categoria.CategoriaService;
import org.financeiro.services.categoria.CategoriaServiceImpl;
import org.financeiro.services.transacao.TransacaoService;
import org.financeiro.services.transacao.TransacaoServiceImpl;
import org.financeiro.services.usuario.UsuarioService;
import org.financeiro.services.usuario.UsuarioServiceImpl;
import org.financeiro.singletons.PainelSingleton;

public class Main {
    private static final CategoriaService categoriaService = new CategoriaServiceImpl(new CategoriaRepositoryImpl());
    private TransacaoService transacaoService = new TransacaoServiceImpl(new TransacaoRepositoryImpl());
    private UsuarioService usuarioService = new UsuarioServiceImpl(new UsuarioRepositoryImpl());

    public static void main(String[] args) {
        Usuario usuario = new Usuario(1, "Carlos", "sdadasdas", "1234");
        Categoria categoria1 = new Categoria(1, "adssadsda", "muito foda ta", usuario);

        categoriaService.cadastrarCategoria(categoria1);
        categoriaService.excluirCategoria(categoria1.getId());

        PainelSingleton.getInstance();
    }
}