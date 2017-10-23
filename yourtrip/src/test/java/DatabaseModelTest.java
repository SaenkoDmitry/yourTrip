import dao.*;
import model.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Date;

public class DatabaseModelTest {

    @BeforeClass
    public static void initDb() throws Exception {
        Database.setUp();
    }

    @Test
    public void personAndRouteTest() throws Exception {
        Session session = Database.session();
        Transaction txn = session.beginTransaction();
        // cleaning table Person
        PersonDao.getInstance().clean(session);
        Person person = new Person("test", "qwerty", "lol@gmail.com", new Date(System.currentTimeMillis()), Role.normal.toString());

        // adding person instance to table Person
        PersonDao.getInstance().insert(session, person);
        Route route = new Route("home", "", true, false, Category.romantic.toString(), 5, person);

        // adding route instance to table Route
        RouteDao.getInstance().insert(session, route);

        // getting all instances from Person
        System.out.println(PersonDao.getInstance().getAll(session));

        session.close();

        // delete instance from table Person
        session = Database.session();
        txn = session.beginTransaction();
        Person person1 = PersonDao.getInstance().getByName(session, "test").get(0);
        System.out.println(person1);
        PersonDao.getInstance().remove(session, person1);
        txn.commit();
        session.close();
    }

    @Test
    public void showplaceTest() throws Exception {
        Session session = Database.session();
        Transaction txn = session.beginTransaction();
        Showplace showplace = new Showplace("moscow", "1:1", 5, 1, new Date(System.currentTimeMillis()));
        ShowplaceDao.getInstance().clean(session);
        ShowplaceDao.getInstance().insert(session, showplace);
        ShowplaceDao.getInstance().getAll(session);
        txn.commit();
        session.close();
    }

    @Test
    public void subscribeTest() throws Exception {
        Session session = Database.session();
        Transaction txn = session.beginTransaction();
        PersonDao.getInstance().clean(session);
        Person person = new Person("test", "qwerty", "lol@gmail.com", new Date(System.currentTimeMillis()), Role.normal.toString());
        PersonDao.getInstance().insert(session, person);
        Person person2 = new Person("test2", "qwerty", "lol@gmail.com", new Date(System.currentTimeMillis()), Role.normal.toString());
        PersonDao.getInstance().insert(session, person2);
        person = PersonDao.getInstance().getByName(session, "test").get(0);
        person2 = PersonDao.getInstance().getByName(session, "test2").get(0);
        Subscribe subscribe = new Subscribe(person, person2);
        SubscribeDao.getInstance().clean(session);
        SubscribeDao.getInstance().insert(session, subscribe);
        txn.commit();
        session.close();
    }
}