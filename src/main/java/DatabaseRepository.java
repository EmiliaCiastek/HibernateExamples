import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class DatabaseRepository implements Repository {
    private static SessionFactory sessionFactory;

    @Override
    public void add(Employee employee) {
        Session session = null;
        Transaction tx = null;

        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            session.save(employee);

            // Committing the change in the database.
            session.flush();
            tx.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            tx.rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void addAll(Collection<Employee> employees) {
        Session session = null;
        Transaction tx = null;

        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            employees.forEach(session::save);

            session.flush();
            tx.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            tx.rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Collection<Employee> getAll() {
        Session session = sessionFactory.openSession();

        List<Employee> employees = session.createQuery("from Employee", Employee.class).list();

        session.close();

        return employees;
    }

    @Override
    public Employee getById(int id) {
        Session session = sessionFactory.openSession();

        Employee employee = session.get(Employee.class, id);

        session.close();

        return employee;
    }

    @Override
    public void updateById(Employee employee) {
        Session session = null;
        Transaction tx = null;

        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            session.update(employee);

            session.flush();
            tx.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            tx.rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void removeById(int id) {
        Session session = null;
        Transaction tx = null;

        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            session.remove(getById(id));

            session.flush();
            tx.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            tx.rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public static void openConnection(){
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public static void closeConnection(){
        sessionFactory.close();
    }
}
