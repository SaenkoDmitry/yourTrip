package dao;

import model.Route;
import org.hibernate.Session;

import java.util.List;

public class RouteDao {
    private static RouteDao ourInstance = new RouteDao();

    public static RouteDao getInstance() {
        return ourInstance;
    }

    private RouteDao() {
    }

    public List<Route> getAll(Session session) {
        return session.createCriteria(Route.class).list();
    }

    public void insert(Session session, Route result) {
        session.saveOrUpdate(result);
    }

    public void clean(Session session) {
        List<Route> results = getAll(session);
        results.forEach(s -> session.delete(s));
        if (results.size() > 0) {
            session.flush();
        }
    }

    public List<Route> getById(Session session, Integer id) {
        return session
                .createQuery("from Route where id = :id")
                .setParameter("id", id)
                .getResultList();
    }
}
