import codebeers.modelo.Articulo;
import codebeers.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;

public class ArticuloTest {

    @Test
    void createTablesTest() {

        Articulo precisionBass = new Articulo(null, "Bajo electrico", 1200f, 60f, 30);
        Articulo guitarra = new Articulo(null, "Guitarra electrica", 1800f, 60f, 50);

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        session.beginTransaction();

        session.save(precisionBass);
        session.save(guitarra);

        session.getTransaction().commit();

        session.close();
        sessionFactory.close();
        HibernateUtil.shutdown();
    }
}
