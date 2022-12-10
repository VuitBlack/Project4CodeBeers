package codebeers.dao;

import codebeers.modelo.Articulo;
import codebeers.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import java.util.List;


public class ArticuloDAOImpl implements ArticuloDAO {

    @Override
    public Articulo create(Articulo articulo) {
        return null;
    }

    @Override
    public List<Articulo> findAll() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        // Consulta HQL
        List<Articulo> articulos = session.createQuery("from Articulo").list();
        session.close();

        return articulos;
    }

    @Override
    public Articulo findById(Long id) {
        return null;
    }

    @Override
    public List<Articulo> findByPvp(Float pvp) {
        return null;
    }

    @Override
    public Articulo update(Articulo articulo) {
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }
}
