package org.financeiro.repositories.transacao;

import org.financeiro.models.Transacao;
import org.financeiro.services.usuario.UsuarioService;
import org.financeiro.services.usuario.UsuarioServiceImpl;
import org.financeiro.singletons.TransacaoSingleton;

import java.util.List;

public class TransacaoRepositoryImpl implements TransacaoRepository {
    private final UsuarioService usuarioService = new UsuarioServiceImpl();

    @Override
    public void cadastrarTransacao(Transacao transacao) {
        TransacaoSingleton.getInstance().getTransacoes().add(transacao);
    }

    @Override
    public List<Transacao> listarTransacoes() {
        return TransacaoSingleton.getInstance().getTransacoes().stream().filter(x-> x.getIdUsuario() == this.usuarioService.buscarUsuarioLogado().id()).toList();
    }

    @Override
    public boolean excluirTransacao(int id) {
        return TransacaoSingleton.getInstance().getTransacoes().removeIf(x -> x.getId() == id);
    }

    @Override
    public void alterarTransacao(Transacao transacao) {
        int index = this.listarTransacoes().indexOf(transacao);

        TransacaoSingleton.getInstance().getTransacoes().set(index, transacao);
    }

    @Override
    public Transacao buscarTransacao(int id) {
        return TransacaoSingleton.getInstance().getTransacoes().stream().filter(x -> x.getId() == id && x.getIdUsuario() == this.usuarioService.buscarUsuarioLogado().id()).findFirst().orElse(null);
    }
}
