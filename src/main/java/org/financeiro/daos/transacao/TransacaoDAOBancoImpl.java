package org.financeiro.daos.transacao;

import org.financeiro.models.Transacao;
import org.financeiro.services.usuario.UsuarioService;
import org.financeiro.services.usuario.UsuarioServiceImpl;
import org.financeiro.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class TransacaoDAOBancoImpl implements TransacaoDAO {
    private final UsuarioService usuarioService = new UsuarioServiceImpl();

    @Override
    public void cadastrarTransacao(Transacao transacao) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.save(transacao);
            tx.commit();
        }
    }

    @Override
    public List<Transacao> listarTransacoes() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            long idUsuarioLogado = this.usuarioService.buscarUsuarioLogado().id();
            return session.createQuery("from Transacao where usuario.id = :idUsuarioLogado", Transacao.class)
                    .setParameter("idUsuarioLogado", idUsuarioLogado)
                    .list();
        }
    }

    @Override
    public boolean excluirTransacao(long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            Transacao transacao = session.get(Transacao.class, id);
            session.delete(transacao);
            tx.commit();

            transacao = session.get(Transacao.class, id);
            return transacao == null;
        }
    }

    @Override
    public void alterarTransacao(Transacao transacao) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.update(transacao);
            tx.commit();
        }
    }

    @Override
    public Transacao buscarTransacao(long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Transacao.class, id);
        }
    }
}
