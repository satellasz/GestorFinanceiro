package org.financeiro.daos.categoria;

import org.financeiro.models.Categoria;
import org.financeiro.services.usuario.UsuarioService;
import org.financeiro.services.usuario.UsuarioServiceImpl;
import org.financeiro.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CategoriaDAOBancoImpl implements CategoriaDAO {
    private final UsuarioService usuarioService = new UsuarioServiceImpl();

    @Override
    public void cadastrarCategoria(Categoria categoria) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.save(categoria);
            tx.commit();
        }
    }

    @Override
    public List<Categoria> listarCategorias() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            long idUsuarioLogado = this.usuarioService.buscarUsuarioLogado().id();
            return session.createQuery("from Categoria where usuario.id = :idUsuarioLogado", Categoria.class)
                    .setParameter("idUsuarioLogado", idUsuarioLogado)
                    .list();
        }
    }

    @Override
    public boolean excluirCategoria(long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            Categoria categoria = session.get(Categoria.class, id);
            session.delete(categoria);
            tx.commit();

            categoria = session.get(Categoria.class, id);
            return categoria == null;
        }
    }

    @Override
    public void alterarCategoria(Categoria categoria) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.update(categoria);
            tx.commit();
        }
    }

    @Override
    public Categoria buscarCategoria(String nome, long idUsuario) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Categoria where nome = :nome and usuario.id = :idUsuario", Categoria.class)
                    .setParameter("nome", nome)
                    .setParameter("idUsuario", idUsuario)
                    .uniqueResult();
        }
    }

    @Override
    public Categoria buscarCategoria(String nome) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Categoria where nome = :nome", Categoria.class)
                    .setParameter("nome", nome)
                    .uniqueResult();
        }
    }

    @Override
    public Categoria buscarCategoria(long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Categoria.class, id);
        }
    }
}