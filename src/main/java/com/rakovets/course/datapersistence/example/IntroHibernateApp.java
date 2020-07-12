package com.rakovets.course.datapersistence.example;

import com.rakovets.course.datapersistence.example.dal.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class IntroHibernateApp {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();

        Employee employee = new Employee();
        employee.setName("Dmitry Rakovets");
        Transaction transaction = session.getTransaction();
        transaction.begin();
        session.createNativeQuery("INSERT INTO employee (name) VALUES (:name)", Employee.class)
                .setParameter("name", employee.getName())
                .executeUpdate();
        transaction.commit();
        session.saveOrUpdate(employee);

        Employee retrievedPerson = null;
        List<Employee> findEmployee = session.createNativeQuery("SELECT * FROM employee WHERE id=2", Employee.class)
                .getResultList();
        if (!findEmployee.isEmpty()) {
            retrievedPerson = findEmployee.get(0);
        }
        session.find(Employee.class, 2L);
        System.out.println(retrievedPerson);

        List<Employee> resultList = session.createNativeQuery("SELECT * FROM employee", Employee.class)
                .getResultList();

        List<Employee> resultList1 = session.createQuery("from Employee", Employee.class)
                .getResultList();

        resultList.forEach(System.out::println);

        session.close();
        sessionFactory.close();
    }
}
