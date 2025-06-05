package org.financeiro.services.transacao;

import org.financeiro.daos.transacao.TransacaoDAO;
import org.financeiro.daos.transacao.TransacaoDAOBancoImpl;
import org.financeiro.dtos.CategoriaDto;
import org.financeiro.dtos.FiltroDto;
import org.financeiro.dtos.TransacaoDto;
import org.financeiro.dtos.UsuarioDto;
import org.financeiro.enums.ClassificacaoTransacao;
import org.financeiro.exceptions.CampoInvalidoException;
import org.financeiro.exceptions.DadoNaoEncontradoException;
import org.financeiro.models.Categoria;
import org.financeiro.models.Transacao;
import org.financeiro.models.Usuario;
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
    private final TransacaoDAO transacaoDAO = new TransacaoDAOBancoImpl();
    private final CategoriaService categoriaService = new CategoriaServiceImpl();
    private final UsuarioService usuarioService = new UsuarioServiceImpl();

    @Override
    public void cadastrarTransacao(String descricao,
                                   double valor,
                                   CategoriaDto categoriaDto,
                                   ClassificacaoTransacao classificacao,
                                   UsuarioDto usuarioDto,
                                   LocalDate dataTransacao) throws CampoInvalidoException, DadoNaoEncontradoException {
        if (dataTransacao.isAfter(LocalDate.now())) {
            throw new CampoInvalidoException("Data não pode ser maior que a data atual");
        }

        if (valor <= 0) {
            throw new CampoInvalidoException("Valor da transação não pode ser inferior ou igual a 0");
        }

        Usuario usuarioTransacao = this.usuarioService.buscarUsuario(usuarioDto.id());

        Categoria categoriaTransacao = this.categoriaService.buscarCategoria(categoriaDto.id());

        Transacao transacaoNova = new Transacao(valor, descricao, categoriaTransacao,
               classificacao, usuarioTransacao, dataTransacao);

        this.transacaoDAO.cadastrarTransacao(transacaoNova);
    }

    @Override
    public List<TransacaoDto> listarTransacoes() throws DadoNaoEncontradoException {
        List<TransacaoDto> transacaoDtos = new ArrayList<>();

        List<Transacao> transacoesSalvas = this.transacaoDAO.listarTransacoes();

        for (Transacao transacao : transacoesSalvas) {
            CategoriaDto categoriaDto = this.categoriaService.buscarCategoriaDto(transacao.getCategoria().getId());
            UsuarioDto usuarioDto = this.usuarioService.buscarUsuarioDto(transacao.getUsuario().getId());
            TransacaoDto transacaoDto = new TransacaoDto(transacao.getId(), transacao.getDescricao(),
                    transacao.getValor(), categoriaDto, transacao.getClassificacao(), usuarioDto,
                    transacao.getDataTransacao());
            transacaoDtos.add(transacaoDto);
        }

        return transacaoDtos;
    }

    @Override
    public void excluirTransacao(long id) throws DadoNaoEncontradoException {
        if (id <= 0) {
            throw new DadoNaoEncontradoException("Transação não foi encontrada para remoção");
        }

        boolean sucesso = this.transacaoDAO.excluirTransacao(id);

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

        Transacao transacaoEncontrada = this.transacaoDAO.buscarTransacao(transacao.id());

        if (transacaoEncontrada == null) {
            throw new DadoNaoEncontradoException("Transação não foi encontrada para edição");
        }

        Categoria categoriaTransacao = this.categoriaService.buscarCategoria(transacao.categoriaDto().id());

        Transacao transacaoAlterada = new Transacao(transacao.id(), transacao.valor(), transacao.descricao(), categoriaTransacao,
                transacao.classificacao(), transacaoEncontrada.getUsuario(), transacao.dataTransacao());

        this.transacaoDAO.alterarTransacao(transacaoAlterada);
    }

    @Override
    public TransacaoDto buscarTransacao(long id) throws DadoNaoEncontradoException {
        Transacao transacao = this.transacaoDAO.buscarTransacao(id);

        if (transacao == null) {
            throw new DadoNaoEncontradoException("Transação não foi encontrada");
        }

        UsuarioDto usuarioDto = this.usuarioService.buscarUsuarioDto(transacao.getUsuario().getId());
        CategoriaDto categoriaDto = this.categoriaService.buscarCategoriaDto(transacao.getCategoria().getId());
        return new TransacaoDto(transacao.getId(), transacao.getDescricao(), transacao.getValor(), categoriaDto,
                transacao.getClassificacao(), usuarioDto, transacao.getDataTransacao());
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
            transacoes = transacoes.stream().filter(
                    x -> x.classificacao().getNome().equals(filtroDto.classificacao())).toList();
        }

        return transacoes;
    }
}
