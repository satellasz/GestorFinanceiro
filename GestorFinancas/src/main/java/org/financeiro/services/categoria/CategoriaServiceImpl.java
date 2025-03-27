package org.financeiro.services.categoria;

import org.financeiro.exceptions.DadoNaoEncontradoException;
import org.financeiro.exceptions.IntegridadeException;
import org.financeiro.models.Categoria;
import org.financeiro.repositories.categoria.CategoriaRepository;
import org.financeiro.repositories.categoria.CategoriaRepositoryImpl;
import org.financeiro.services.transacao.TransacaoService;
import org.financeiro.services.transacao.TransacaoServiceImpl;

import java.util.List;

public class CategoriaServiceImpl implements CategoriaService {
    private final CategoriaRepository categoriaRepository = new CategoriaRepositoryImpl();
    private final TransacaoService transacaoService = new TransacaoServiceImpl();

    @Override
    public void cadastrarCategoria(Categoria categoria) {
        Categoria categoriaNova = new Categoria(getIdUltimaCategoria() + 1, categoria.getNome(), categoria.getDescricao(), categoria.getUsuario());

        categoriaRepository.cadastrarCategoria(categoriaNova);
    }

    @Override
    public List<Categoria> listarCategorias() {
        return categoriaRepository.listarCategorias();
    }

    @Override
    public void excluirCategoria(int id) throws DadoNaoEncontradoException, IntegridadeException {
        if (id <= 0) {
            throw new DadoNaoEncontradoException("Categoria não foi encontrada para remoção");
        }

        if (this.transacaoService.transacaoContemCategoria(id)) {
            throw new IntegridadeException("Categoria está cadastrada em alguma transação");
        }

        boolean sucesso = categoriaRepository.excluirCategoria(id);

        if (!sucesso) {
            throw new DadoNaoEncontradoException("Não foi possível remover a categoria");
        }
    }

    @Override
    public void alterarCategoria(Categoria categoria) throws DadoNaoEncontradoException {
        if (categoria.getId() <= 0) {
            throw new DadoNaoEncontradoException("Categoria não foi encontrada para edição");
        }

        categoriaRepository.alterarCategoria(categoria);
    }

    @Override
    public Categoria buscarCategoria(String nome) throws DadoNaoEncontradoException {
        Categoria categoria = categoriaRepository.buscarCategoria(nome);

        if (categoria == null) {
            throw new DadoNaoEncontradoException("Categoria não foi encontrada");
        }

        return categoria;
    }

    @Override
    public Categoria buscarCategoria(int id) throws DadoNaoEncontradoException {
        Categoria categoria = categoriaRepository.buscarCategoria(id);

        if (categoria == null) {
            throw new DadoNaoEncontradoException("Categoria não foi encontrada");
        }

        return categoria;
    }

    @Override
    public int getIdUltimaCategoria() {
        List<Categoria> categorias = listarCategorias();

        if (categorias.isEmpty()) {
            return 0;
        }

        Categoria categoria = categoriaRepository.listarCategorias().getLast();

        return categoria.getId();
    }
}
