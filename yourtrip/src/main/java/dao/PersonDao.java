package dao;

import model.Person;
import org.hibernate.Session;

import java.util.List;

public class PersonDao {
    private static PersonDao ourInstance = new PersonDao();

    public static PersonDao getInstance() {
        return ourInstance;
    }

    private PersonDao() {
    }

    public List<Person> getAll(Session session) {
        return session.createCriteria(Person.class).list();
    }

    public void insert(Session session, Person result) {
        session.saveOrUpdate(result);
    }

    public void clean(Session session) {
        List<Person> results = getAll(session);
        results.forEach(s -> session.delete(s));
        if (results.size() > 0) {
            session.flush();
        }
    }

    public void remove(Session session, Person person) {
        session.delete(person);
        List<Person> results = getAll(session);
        if (results.size() > 0) {
            session.flush();
        }
    }

    public List<Person> getById(Session session, Integer id) {
        return session
                .createQuery("from Person where id = :id")
                .setParameter("id", id)
                .getResultList();
    }

    public List<Person> getByName(Session session, String login) {
        return session
                .createQuery("from Person where login = :login")
                .setParameter("login", login)
                .getResultList();
    }
}