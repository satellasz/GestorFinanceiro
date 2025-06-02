package org.financeiro.services.categoria;

import org.financeiro.dtos.CategoriaDto;
import org.financeiro.dtos.TransacaoDto;
import org.financeiro.dtos.UsuarioDto;
import org.financeiro.exceptions.DadoNaoEncontradoException;
import org.financeiro.exceptions.IntegridadeException;
import org.financeiro.models.Categoria;
import org.financeiro.models.Usuario;
import org.financeiro.daos.categoria.CategoriaDAO;
import org.financeiro.daos.categoria.CategoriaDAOBancoImpl;
import org.financeiro.services.usuario.UsuarioService;
import org.financeiro.services.usuario.UsuarioServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class CategoriaServiceImpl implements CategoriaService {
    private final CategoriaDAO categoriaDAO = new CategoriaDAOBancoImpl();
    private final UsuarioService usuarioService = new UsuarioServiceImpl();

    @Override
    public void cadastrarCategoria(String nome,
                                   String descricao,
                                   UsuarioDto usuarioDto) throws IntegridadeException, DadoNaoEncontradoException {
        Categoria categoriaSalva = this.categoriaDAO.buscarCategoria(nome.trim());

        if (categoriaSalva != null) {
            throw new IntegridadeException("Há uma Categoria previamente salva com este nome");
        }

        Usuario usuarioCategoria = this.usuarioService.buscarUsuario(usuarioDto.id());

        Categoria categoriaNova = new Categoria(nome, descricao, usuarioCategoria);

        this.categoriaDAO.cadastrarCategoria(categoriaNova);
    }

    @Override
    public List<CategoriaDto> listarCategorias() throws DadoNaoEncontradoException {
        List<CategoriaDto> categorias = new ArrayList<>();

        for (Categoria categoria : this.categoriaDAO.listarCategorias()) {
            UsuarioDto usuarioDto = this.usuarioService.buscarUsuarioDto(categoria.getUsuario().getId());
            CategoriaDto categoriaDto = new CategoriaDto(categoria.getId(), categoria.getNome(),
                    categoria.getDescricao(), usuarioDto);
            categorias.add(categoriaDto);
        }

        return categorias;
    }

    @Override
    public void excluirCategoria(long id,
                                 List<TransacaoDto> transacaoDtos) throws DadoNaoEncontradoException, IntegridadeException {
        if (id <= 0) {
            throw new DadoNaoEncontradoException("Categoria não foi encontrada para remoção");
        }

        boolean categoriaContemTransacao = transacaoDtos.stream().anyMatch(x -> x.categoriaDto().id() == id);

        if (categoriaContemTransacao) {
            throw new IntegridadeException(
                    "Categoria não pode ser excluída pois há transações cadastradas com a mesma");
        }

        boolean sucesso = categoriaDAO.excluirCategoria(id);

        if (!sucesso) {
            throw new DadoNaoEncontradoException("Não foi possível remover a categoria");
        }
    }

    @Override
    public void alterarCategoria(CategoriaDto categoria) throws DadoNaoEncontradoException {
        if (categoria.id() <= 0) {
            throw new DadoNaoEncontradoException("Categoria não foi encontrada para edição");
        }

        Categoria categoriaEncontrada = this.categoriaDAO.buscarCategoria(categoria.id());

        if (categoriaEncontrada == null) {
            throw new DadoNaoEncontradoException("Categoria não foi encontrada para edição");
        }

        Categoria categoriaNova = new Categoria(categoria.id(), categoria.nome(), categoria.descricao(),
                categoriaEncontrada.getUsuario());

        this.categoriaDAO.alterarCategoria(categoriaNova);
    }

    @Override
    public CategoriaDto buscarCategoriaDto(String nome) throws DadoNaoEncontradoException {
        Categoria categoria = this.categoriaDAO.buscarCategoria(nome);

        if (categoria == null) {
            throw new DadoNaoEncontradoException("Categoria com o nome " + nome + " não foi encontrada");
        }

        UsuarioDto usuarioDto = this.usuarioService.buscarUsuarioDto(categoria.getUsuario().getId());
        return new CategoriaDto(categoria.getId(), categoria.getNome(), categoria.getDescricao(), usuarioDto);
    }

    @Override
    public CategoriaDto buscarCategoriaDto(long id) throws DadoNaoEncontradoException {
        Categoria categoria = this.categoriaDAO.buscarCategoria(id);

        if (categoria == null) {
            throw new DadoNaoEncontradoException("Categoria não foi encontrada");
        }

        UsuarioDto usuarioDto = this.usuarioService.buscarUsuarioDto(categoria.getUsuario().getId());
        return new CategoriaDto(categoria.getId(), categoria.getNome(), categoria.getDescricao(), usuarioDto);
    }

    @Override
    public Categoria buscarCategoria(long id) throws DadoNaoEncontradoException {
        Categoria categoria = this.categoriaDAO.buscarCategoria(id);

        if (categoria == null) {
            throw new DadoNaoEncontradoException("Categoria não foi encontrada");
        }

        return categoria;
    }
}
