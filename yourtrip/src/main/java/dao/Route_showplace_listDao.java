package dao;

import model.Route_showplace_list;
import org.hibernate.Session;

import java.util.List;

public class Route_showplace_listDao {
    private static Route_showplace_listDao ourInstance = new Route_showplace_listDao();

    public static Route_showplace_listDao getInstance() {
        return ourInstance;
    }

    private Route_showplace_listDao() {
    }

    public List<Route_showplace_list> getAll(Session session) {
        return session.createCriteria(Route_showplace_list.class).list();
    }

    public void insert(Session session, Route_showplace_list result) {
        session.saveOrUpdate(result);
    }

    public void clean(Session session) {
        List<Route_showplace_list> results = session.createQuery("from Route_showplace_list").getResultList();
        for (Route_showplace_list result: results) {
            session.delete(result);
        }
        if (results.size() > 0) {
            session.flush();
        }
    }

    public List<Route_showplace_list> getById(Session session, Integer id) {
        return session
                .createQuery("from Route_showplace_list where id = :id")
                .setParameter("id", id)
                .getResultList();
    }
}
