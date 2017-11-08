package dao;

import model.Image;
import org.hibernate.Session;

import java.util.List;

public class ImageDao {
    private static ImageDao ourInstance = new ImageDao();

    public static ImageDao getInstance() {
        return ourInstance;
    }

    private ImageDao() {
    }

    public List<Image> getAll(Session session) {
        return session.createCriteria(Image.class).list();
    }

    public void insert(Session session, Image result) {
        session.saveOrUpdate(result);
    }

    public void clean(Session session) {
        List<Image> results = getAll(session);
        results.forEach(s -> session.delete(s));
        if (results.size() > 0) {
            session.flush();
        }
    }

    public void remove(Session session, Image person) {
        session.delete(person);
        List<Image> results = getAll(session);
        if (results.size() > 0) {
            session.flush();
        }
    }

    public List<Image> getById(Session session, Integer id) {
        return session
                .createQuery("from Image where id = :id")
                .setParameter("id", id)
                .getResultList();
    }

    public List<Image> getByName(Session session, String login) {
        return session
                .createQuery("from Image where login = :login")
                .setParameter("login", login)
                .getResultList();
    }
}
