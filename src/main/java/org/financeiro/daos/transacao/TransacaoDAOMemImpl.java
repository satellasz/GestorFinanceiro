package org.financeiro.daos.transacao;

import org.financeiro.models.Transacao;
import org.financeiro.services.usuario.UsuarioService;
import org.financeiro.services.usuario.UsuarioServiceImpl;
import org.financeiro.singletons.TransacaoSingleton;

import java.util.List;

public class TransacaoDAOMemImpl implements TransacaoDAO {
    private final UsuarioService usuarioService = new UsuarioServiceImpl();

    @Override
    public void cadastrarTransacao(Transacao transacao) {
        transacao.setId(this.getIdProximaTransacao());
        TransacaoSingleton.getInstance().getTransacoes().add(transacao);
    }

    @Override
    public List<Transacao> listarTransacoes() {
        return TransacaoSingleton.getInstance().getTransacoes().stream().filter(
                x -> x.getUsuario().getId() == this.usuarioService.buscarUsuarioLogado().id()).toList();
    }

    @Override
    public boolean excluirTransacao(long id) {
        return TransacaoSingleton.getInstance().getTransacoes().removeIf(x -> x.getId() == id);
    }

    @Override
    public void alterarTransacao(Transacao transacao) {
        int index = this.listarTransacoes().indexOf(transacao);

        TransacaoSingleton.getInstance().getTransacoes().set(index, transacao);
    }

    @Override
    public Transacao buscarTransacao(long id) {
        return TransacaoSingleton.getInstance().getTransacoes().stream().filter(
                x -> x.getId() == id && x.getUsuario().getId() == this.usuarioService.buscarUsuarioLogado().id()).findFirst().orElse(
                null);
    }

    public long getIdProximaTransacao() {
        List<Transacao> transacoes = this.listarTransacoes();

        if (transacoes.isEmpty()) {
            return 1;
        }

        Transacao transacao = transacoes.getLast();

        return transacao.getId() + 1L;
    }
}
