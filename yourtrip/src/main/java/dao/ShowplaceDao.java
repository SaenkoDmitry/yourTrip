package dao;

import model.Showplace;
import org.hibernate.Session;

import java.util.List;

public class ShowplaceDao {
    private static ShowplaceDao ourInstance = new ShowplaceDao();

    public static ShowplaceDao getInstance() {
        return ourInstance;
    }

    private ShowplaceDao() {
    }

    public List<Showplace> getAll(Session session) {
        return session.createCriteria(Showplace.class).list();
    }

    public void insert(Session session, Showplace result) {
        session.saveOrUpdate(result);
    }

    public void clean(Session session) {
        List<Showplace> results = session.createQuery("from Showplace").getResultList();
        for (Showplace result: results) {
            session.delete(result);
        }
        if (results.size() > 0) {
            session.flush();
        }
    }

    public List<Showplace> getById(Session session, Integer id) {
        return session
                .createQuery("from Showplace where id = :id")
                .setParameter("id", id)
                .getResultList();
    }
}
