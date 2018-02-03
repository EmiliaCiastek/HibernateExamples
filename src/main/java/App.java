import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 */

public class App {
    private static SessionFactory sessionFactory;

    private static void configureSessionFactory() throws HibernateException {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public static void main(String[] args) {
        // Configure the session factory
        configureSessionFactory();

        List<Employee> employees = new ArrayList<>();
        Employee firstEmployee = new Employee("First", 10000.0);
        Employee secondEmployee = new Employee("Other", 20000.0);
        employees.add(firstEmployee);
        employees.add(secondEmployee);

        putDataToDataBase(employees);

        System.out.println("Added");

        Employee employee = getById(1); //id min value = 1, not 0!
        System.out.println(employee.toString());
        sessionFactory.close();
    }

    private static void putDataToDataBase(List<Employee> employees) {
        Session session = null;
        Transaction tx = null;

        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            employees.forEach(session::save);

            // Committing the change in the database.
            session.flush();
            tx.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();

            // Rolling back the changes to make the data consistent in case of any failure
            // in between multiple database write operations.
            tx.rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    private static Employee getById(int id) {
        Session session = sessionFactory.openSession();

        Employee employee = session.get(Employee.class, id);

        session.close();

        return employee;
    }
}
