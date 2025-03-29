package org.financeiro.services.transacao;

import org.financeiro.dtos.FiltroDto;
import org.financeiro.exceptions.CampoInvalidoException;
import org.financeiro.exceptions.DadoNaoEncontradoException;
import org.financeiro.models.Transacao;
import org.financeiro.repositories.transacao.TransacaoRepository;
import org.financeiro.repositories.transacao.TransacaoRepositoryImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static org.financeiro.controllers.AbstractController.TODAS;

public class TransacaoServiceImpl implements TransacaoService {
    private final TransacaoRepository transacaoRepository = new TransacaoRepositoryImpl();

    @Override
    public void cadastrarTransacao(Transacao transacao) throws CampoInvalidoException {
        if (transacao.getDataTransacao().isAfter(LocalDate.now())) {
            throw new CampoInvalidoException("Data não pode ser maior que a data atual");
        }
        Transacao transacaoNova = new Transacao(getIdUltimaTransacao() + 1, transacao.getValor(), transacao.getDescricao(), transacao.getIdCategoria(), transacao.getClassificacao(), null, transacao.getDataTransacao());

        this.transacaoRepository.cadastrarTransacao(transacaoNova);
    }

    @Override
    public List<Transacao> listarTransacoes() {
        return this.transacaoRepository.listarTransacoes();
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
    public void alterarTransacao(Transacao transacao) throws DadoNaoEncontradoException, CampoInvalidoException {
        if (transacao.getId() <= 0) {
            throw new DadoNaoEncontradoException("Transação não foi encontrada para edição");
        }

        if (transacao.getDataTransacao().isAfter(LocalDate.now())) {
            throw new CampoInvalidoException("Data não pode ser maior que a data atual");
        }

        this.transacaoRepository.alterarTransacao(transacao);
    }

    @Override
    public Transacao buscarTransacao(int id) {
        return this.transacaoRepository.buscarTransacao(id);
    }

    @Override
    public int getIdUltimaTransacao() {
        List<Transacao> transacoes = this.listarTransacoes();

        if (transacoes.isEmpty()) {
            return 0;
        }

        Transacao transacao = transacoes.getLast();

        return transacao.getId();
    }

    @Override
    public boolean transacaoContemCategoria(int idCategoria) {
        return this.transacaoRepository.listarTransacoes().stream().anyMatch(x -> x.getIdCategoria() == idCategoria);
    }

    @Override
    public List<Transacao> getTransacoesFiltradas(FiltroDto filtroDto) {
        List<Transacao> transacoes = this.listarTransacoes();

        transacoes = transacoes.stream().filter(x ->
                (filtroDto.dataInicio().isBefore(x.getDataTransacao())
                        || filtroDto.dataInicio().isEqual(x.getDataTransacao()))
                        && (filtroDto.dataFim().isAfter(x.getDataTransacao())
                        || filtroDto.dataFim().isEqual(x.getDataTransacao()))).toList();

        if (filtroDto.categoria() != null) {
            transacoes = transacoes.stream().filter(x -> x.getIdCategoria() == filtroDto.categoria().getId()).toList();
        }

        if (!Objects.equals(filtroDto.classificacao(), TODAS)) {
            transacoes = transacoes.stream().filter(x -> x.getClassificacao().getNome().equals(filtroDto.classificacao())).toList();
        }

        return transacoes;
    }
}
