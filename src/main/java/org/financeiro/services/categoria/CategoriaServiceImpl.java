package org.financeiro.services.categoria;

import org.financeiro.dtos.CategoriaDto;
import org.financeiro.dtos.TransacaoDto;
import org.financeiro.dtos.UsuarioDto;
import org.financeiro.exceptions.DadoNaoEncontradoException;
import org.financeiro.exceptions.IntegridadeException;
import org.financeiro.models.Categoria;
import org.financeiro.repositories.categoria.CategoriaRepository;
import org.financeiro.repositories.categoria.CategoriaRepositoryImpl;
import org.financeiro.services.usuario.UsuarioService;
import org.financeiro.services.usuario.UsuarioServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class CategoriaServiceImpl implements CategoriaService {
    private final CategoriaRepository categoriaRepository = new CategoriaRepositoryImpl();
    private final UsuarioService usuarioService = new UsuarioServiceImpl();

    @Override
    public void cadastrarCategoria(CategoriaDto categoria) throws IntegridadeException {
        Categoria categoriaSalva = this.categoriaRepository.buscarCategoria(categoria.nome().trim());

        if (categoriaSalva != null) {
            throw new IntegridadeException("Há uma Categoria previamente salva com este nome");
        }

        Categoria categoriaNova = new Categoria(categoria.id(), categoria.nome(), categoria.descricao(), categoria.usuarioDto().id());

        categoriaRepository.cadastrarCategoria(categoriaNova);
    }

    @Override
    public List<CategoriaDto> listarCategorias() throws DadoNaoEncontradoException {
        List<CategoriaDto> categorias = new ArrayList<>();

        for (Categoria categoria : this.categoriaRepository.listarCategorias()) {
            UsuarioDto usuarioDto = this.usuarioService.buscarUsuario(categoria.getIdUsuario());
            CategoriaDto categoriaDto = new CategoriaDto(categoria.getId(), categoria.getNome(), categoria.getDescricao(), usuarioDto);
            categorias.add(categoriaDto);
        }

        return categorias;
    }

    @Override
    public void excluirCategoria(int id, List<TransacaoDto> transacaoDtos) throws DadoNaoEncontradoException, IntegridadeException {
        if (id <= 0) {
            throw new DadoNaoEncontradoException("Categoria não foi encontrada para remoção");
        }

        boolean categoriaContemTransacao = transacaoDtos.stream().anyMatch(x -> x.categoriaDto().id() == id);

        if (categoriaContemTransacao) {
            throw new IntegridadeException("Categoria não pode ser excluída pois há transações cadastradas com a mesma");
        }

        boolean sucesso = categoriaRepository.excluirCategoria(id);

        if (!sucesso) {
            throw new DadoNaoEncontradoException("Não foi possível remover a categoria");
        }
    }

    @Override
    public void alterarCategoria(CategoriaDto categoria) throws DadoNaoEncontradoException {
        if (categoria.id() <= 0) {
            throw new DadoNaoEncontradoException("Categoria não foi encontrada para edição");
        }

        Categoria categoriaEncontrada = this.categoriaRepository.buscarCategoria(categoria.id());

        if (categoriaEncontrada == null) {
            throw new DadoNaoEncontradoException("Categoria não foi encontrada para edição");
        }

        Categoria categoriaAlterada = new Categoria(categoria.id(), categoria.nome(), categoria.descricao(), categoria.usuarioDto().id());

        this.categoriaRepository.alterarCategoria(categoriaAlterada);
    }

    @Override
    public CategoriaDto buscarCategoria(String nome) throws DadoNaoEncontradoException {
        Categoria categoria = this.categoriaRepository.buscarCategoria(nome);

        if (categoria == null) {
            throw new DadoNaoEncontradoException("Categoria com o nome " + nome + " não foi encontrada");
        }

        UsuarioDto usuarioDto = this.usuarioService.buscarUsuario(categoria.getIdUsuario());
        return new CategoriaDto(categoria.getId(), categoria.getNome(), categoria.getDescricao(), usuarioDto);
    }

    @Override
    public CategoriaDto buscarCategoria(int id) throws DadoNaoEncontradoException {
        Categoria categoria = this.categoriaRepository.buscarCategoria(id);

        if (categoria == null) {
            throw new DadoNaoEncontradoException("Categoria não foi encontrada");
        }

        UsuarioDto usuarioDto = this.usuarioService.buscarUsuario(categoria.getIdUsuario());
        return new CategoriaDto(categoria.getId(), categoria.getNome(), categoria.getDescricao(), usuarioDto);
    }

    @Override
    public int getIdProximaCategoria() throws DadoNaoEncontradoException {
        List<CategoriaDto> categorias = this.listarCategorias();

        if (categorias.isEmpty()) {
            return 1;
        }

        CategoriaDto categoria = categorias.getLast();

        return categoria.id() + 1;
    }
}
