package dao;

import model.Showplace_from_to;
import org.hibernate.Session;

import java.util.List;

public class Showplace_from_toDao {
    private static Showplace_from_toDao ourInstance = new Showplace_from_toDao();

    public static Showplace_from_toDao getInstance() {
        return ourInstance;
    }

    private Showplace_from_toDao() {
    }

    public List<Showplace_from_to> getAll(Session session) {
        return session.createCriteria(Showplace_from_to.class).list();
    }

    public void insert(Session session, Showplace_from_to result) {
        session.saveOrUpdate(result);
    }

    public void clean(Session session) {
        List<Showplace_from_to> results = session.createQuery("from Showplace_from_to").getResultList();
        for (Showplace_from_to result: results) {
            session.delete(result);
        }
        if (results.size() > 0) {
            session.flush();
        }
    }

    public List<Showplace_from_to> getById(Session session, Integer id) {
        return session
                .createQuery("from Showplace_from_to where id = :id")
                .setParameter("id", id)
                .getResultList();
    }
}
