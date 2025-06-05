package org.financeiro.daos.usuario;

import org.financeiro.models.Usuario;
import org.financeiro.singletons.UsuarioSingleton;
import org.financeiro.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UsuarioDAOBancoImpl implements UsuarioDAO {
    @Override
    public void cadastrarUsuario(Usuario usuario) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(usuario);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
        }
    }

    @Override
    public List<Usuario> listarUsuarios() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Usuario", Usuario.class).list();
        }
    }

    @Override
    public void excluirUsuario(long id) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Usuario usuario = session.get(Usuario.class, id);
            if (usuario != null) {
                session.remove(usuario);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
        }
    }

    @Override
    public void alterarUsuario(Usuario usuario) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.merge(usuario);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
        }
    }

    @Override
    public Usuario buscarUsuario(long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Usuario.class, id);
        }
    }

    @Override
    public Usuario buscarUsuarioLogado() {
        return UsuarioSingleton.getInstance().getUsuarioLogado();
    }

    @Override
    public Usuario buscarUsuario(String login) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                            "FROM Usuario WHERE nome = :login", Usuario.class)
                    .setParameter("login", login)
                    .uniqueResult();
        }
    }

    @Override
    public void setUsuarioLogado(Usuario usuario) {
        UsuarioSingleton.getInstance().setUsuarioLogado(usuario);
    }
}
