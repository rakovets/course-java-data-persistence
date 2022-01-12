package by.rakovets.course.datapersistence;

import by.rakovets.course.datapersistence.hibernate.dal.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class EmployeeTest {
    private static SessionFactory SESSION_FACTORY;

    @BeforeAll
    static void init() {
        SESSION_FACTORY = new Configuration().configure().buildSessionFactory();
    }

    @Test
    void testSaveEmployee() {
        Session session = SESSION_FACTORY.openSession();

        Employee employee = new Employee();
        employee.setName("Dmitry Rakovets");
        Long id = (Long) session.save(employee);

        Employee savedEmployee = session.find(Employee.class, id);
        Assertions.assertEquals(savedEmployee.getName(), "Dmitry Rakovets");

        session.close();
    }

    @Test
    public void testSaveEmployee2() {
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();
        Employee employee = new Employee();
        employee.setName("Max");
        Long id = (Long) session.save(employee);

        transaction.commit();
        session.close();

        session = SESSION_FACTORY.openSession();
        Transaction transaction2 = session.beginTransaction();

        Employee savedEmployee = session.get(Employee.class, id);
        savedEmployee = session.get(Employee.class, id);
        savedEmployee = session.get(Employee.class, id);
        savedEmployee = session.get(Employee.class, id);
        savedEmployee = session.get(Employee.class, id);
        System.out.println(savedEmployee);

        transaction2.commit();
        session.close();
    }

    @Test
    public void testChangeField() {
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();
        Employee employee = new Employee();
        employee.setName("Max");
        session.saveOrUpdate(employee);
        employee.setName("Alex");
        employee.setName("Igor");

        transaction.commit();
        session.close();
    }


    @AfterAll
    static void finish() {
        SESSION_FACTORY.close();
    }
}
