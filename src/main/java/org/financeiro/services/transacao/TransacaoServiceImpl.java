package org.financeiro.services.transacao;

import org.financeiro.dtos.CategoriaDto;
import org.financeiro.dtos.FiltroDto;
import org.financeiro.dtos.TransacaoDto;
import org.financeiro.dtos.UsuarioDto;
import org.financeiro.exceptions.CampoInvalidoException;
import org.financeiro.exceptions.DadoNaoEncontradoException;
import org.financeiro.models.Transacao;
import org.financeiro.repositories.transacao.TransacaoRepository;
import org.financeiro.repositories.transacao.TransacaoRepositoryImpl;
import org.financeiro.services.categoria.CategoriaService;
import org.financeiro.services.categoria.CategoriaServiceImpl;
import org.financeiro.services.usuario.UsuarioService;
import org.financeiro.services.usuario.UsuarioServiceImpl;
import org.financeiro.utils.Utils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.financeiro.controllers.AbstractController.TODAS;

public class TransacaoServiceImpl implements TransacaoService {
    private final TransacaoRepository transacaoRepository = new TransacaoRepositoryImpl();
    private final CategoriaService categoriaService = new CategoriaServiceImpl();
    private final UsuarioService usuarioService = new UsuarioServiceImpl();

    @Override
    public void cadastrarTransacao(TransacaoDto transacao) throws CampoInvalidoException {
        if (transacao.dataTransacao().isAfter(LocalDate.now())) {
            throw new CampoInvalidoException("Data não pode ser maior que a data atual");
        }

        if (transacao.valor() <= 0) {
            throw new CampoInvalidoException("Valor da transação não pode ser inferior ou igual a 0");
        }

        Transacao transacaoNova = new Transacao(transacao.id(), transacao.valor(), transacao.descricao(), transacao.categoriaDto().id(), transacao.classificacao(), transacao.usuarioDto().id(), transacao.dataTransacao());

        this.transacaoRepository.cadastrarTransacao(transacaoNova);
    }

    @Override
    public List<TransacaoDto> listarTransacoes() throws DadoNaoEncontradoException {
        List<TransacaoDto> transacaoDtos = new ArrayList<>();

        List<Transacao> transacoesSalvas = this.transacaoRepository.listarTransacoes();

        for (Transacao transacao : transacoesSalvas) {
            CategoriaDto categoriaDto = this.categoriaService.buscarCategoria(transacao.getIdCategoria());
            UsuarioDto usuarioDto = this.usuarioService.buscarUsuario(transacao.getIdUsuario());
            TransacaoDto transacaoDto = new TransacaoDto(transacao.getId(), transacao.getDescricao(), transacao.getValor(), categoriaDto, transacao.getClassificacao(), usuarioDto, transacao.getDataTransacao());
            transacaoDtos.add(transacaoDto);
        }

        return transacaoDtos;
    }

    @Override
    public void excluirTransacao(int id) throws DadoNaoEncontradoException {
        if (id <= 0) {
            throw new DadoNaoEncontradoException("Transação não foi encontrada para remoção");
        }

        boolean sucesso = this.transacaoRepository.excluirTransacao(id);

        if (!sucesso) {
            throw new DadoNaoEncontradoException("Não foi possível remover a transação");
        }
    }

    @Override
    public void alterarTransacao(TransacaoDto transacao) throws DadoNaoEncontradoException, CampoInvalidoException {
        if (transacao.id() <= 0) {
            throw new DadoNaoEncontradoException("Transação não foi encontrada para edição");
        }

        if (transacao.dataTransacao().isAfter(LocalDate.now())) {
            throw new CampoInvalidoException("Data não pode ser maior que a data atual");
        }

        Transacao transacaoEncontrada = this.transacaoRepository.buscarTransacao(transacao.id());

        if (transacaoEncontrada == null) {
            throw new DadoNaoEncontradoException("Transação não foi encontrada para edição");
        }

        Transacao transacaoAlterada = new Transacao(transacao.id(), transacao.valor(), transacao.descricao() , transacao.categoriaDto().id(), transacao.classificacao(), transacao.usuarioDto().id(), transacao.dataTransacao());

        this.transacaoRepository.alterarTransacao(transacaoAlterada);
    }

    @Override
    public TransacaoDto buscarTransacao(int id) throws DadoNaoEncontradoException {
        Transacao transacao = this.transacaoRepository.buscarTransacao(id);

        if (transacao == null) {
            throw new DadoNaoEncontradoException("Transação não foi encontrada");
        }

        UsuarioDto usuarioDto = this.usuarioService.buscarUsuario(transacao.getIdUsuario());
        CategoriaDto categoriaDto = this.categoriaService.buscarCategoria(transacao.getIdCategoria());
        return new TransacaoDto(transacao.getId(), transacao.getDescricao(), transacao.getValor(), categoriaDto, transacao.getClassificacao(), usuarioDto, transacao.getDataTransacao());
    }

    @Override
    public int getIdProximaTransacao() throws DadoNaoEncontradoException {
        List<TransacaoDto> transacoes = this.listarTransacoes();

        if (transacoes.isEmpty()) {
            return 1;
        }

        TransacaoDto transacao = transacoes.getLast();

        return transacao.id() + 1;
    }

    @Override
    public List<TransacaoDto> getTransacoesFiltradas(FiltroDto filtroDto) throws DadoNaoEncontradoException {
        List<TransacaoDto> transacoes = this.listarTransacoes();

        transacoes = transacoes.stream().filter(x ->
                (filtroDto.dataInicio().isBefore(x.dataTransacao())
                        || filtroDto.dataInicio().isEqual(x.dataTransacao()))
                        && (filtroDto.dataFim().isAfter(x.dataTransacao())
                        || filtroDto.dataFim().isEqual(x.dataTransacao()))).toList();

        if (filtroDto.categoria() != null) {
            transacoes = transacoes.stream().filter(x -> x.categoriaDto().id() == filtroDto.categoria().id()).toList();
        }

        if (!Utils.isStringVazia(filtroDto.classificacao()) && !Objects.equals(filtroDto.classificacao(), TODAS)) {
            transacoes = transacoes.stream().filter(x -> x.classificacao().getNome().equals(filtroDto.classificacao())).toList();
        }

        return transacoes;
    }
}
