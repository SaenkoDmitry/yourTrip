package dao;

import model.Subscription;
import org.hibernate.Session;

import java.util.List;

public class SubscriptionDao {
    private static SubscriptionDao ourInstance = new SubscriptionDao();

    public static SubscriptionDao getInstance() {
        return ourInstance;
    }

    private SubscriptionDao() {
    }

    public List<Subscription> getAll(Session session) {
        return session.createCriteria(Subscription.class).list();
    }

    public void insert(Session session, Subscription result) {
        session.saveOrUpdate(result);
    }

    public void clean(Session session) {
        List<Subscription> results = getAll(session);
        results.forEach(s -> session.delete(s));
        if (results.size() > 0) {
            session.flush();
        }
    }

    public void remove(Session session, Subscription Subscription) {
        session.remove(Subscription);
        session.flush();
    }

    public List<Subscription> getById(Session session, Integer id) {
        return session
                .createQuery("from Subscription where id = :id")
                .setParameter("id", id)
                .getResultList();
    }

    public boolean existSubscription(Session session, Integer id_from, Integer id_to) {
        return session
                .createQuery("from Subscription where person_id_from = :person_id_from and person_id_to = :person_id_to")
                .setParameter("person_id_from", id_from)
                .setParameter("person_id_to", id_to)
                .getResultList().size() > 0;
    }
}
