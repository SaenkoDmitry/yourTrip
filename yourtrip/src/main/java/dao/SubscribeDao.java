package dao;

import model.Subscribe;
import org.hibernate.Session;

import java.util.List;

public class SubscribeDao {
    private static SubscribeDao ourInstance = new SubscribeDao();

    public static SubscribeDao getInstance() {
        return ourInstance;
    }

    private SubscribeDao() {
    }

    public List<Subscribe> getAll(Session session) {
        return session.createCriteria(Subscribe.class).list();
    }

    public void insert(Session session, Subscribe result) {
        session.saveOrUpdate(result);
    }

    public void clean(Session session) {
        List<Subscribe> results = getAll(session);
        results.forEach(s -> session.delete(s));
        if (results.size() > 0) {
            session.flush();
        }
    }

    public void remove(Session session, Subscribe Subscribe) {
        session.remove(Subscribe);
        session.flush();
    }

    public List<Subscribe> getById(Session session, Integer id) {
        return session
                .createQuery("from Subscribe where id = :id")
                .setParameter("id", id)
                .getResultList();
    }

    public boolean existSubscribe(Session session, Integer id_from, Integer id_to) {
        return session
                .createQuery("from Subscribe where person_id_from = :person_id_from and person_id_to = :person_id_to")
                .setParameter("person_id_from", id_from)
                .setParameter("person_id_to", id_to)
                .getResultList().size() > 0;
    }
}
