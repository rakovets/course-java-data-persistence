package com.rakovets.course.datapersistence.query.hql.dal.dao;

import com.rakovets.course.datapersistence.query.hql.dal.entity.Employee;
import com.rakovets.course.datapersistence.query.hql.dal.entity.Payment;
import com.rakovets.course.datapersistence.query.hql.dal.util.EmployeeTestDataImporter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

public class EmployeeDaoTest {
    private static SessionFactory sessionFactory;

    @BeforeAll
    static void initDb() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        EmployeeTestDataImporter.getInstance().importTestData(sessionFactory);
    }

    @Test
    public void testFindAll() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<Employee> results = EmployeeDao.getInstance().findAll(session);
        List<String> fullNames
                = results.stream().map(Employee::fullName).collect(toList());
        assertThat(results, hasSize(5));
        assertThat(fullNames, containsInAnyOrder("Bill Gates", "Steve Jobs", "Sergey Brin", "Tim Cook", "Diane Greene"));

        session.getTransaction().commit();
        session.close();
    }

    @Test
    public void testFindAllByFirstName() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<Employee> results = EmployeeDao.getInstance().findAllByFirstName(session, "Bill");
        assertThat(results, hasSize(1));
        assertThat(results.get(0).getLastName(), equalTo("Gates"));

        session.getTransaction().commit();
        session.close();
    }

    @Test
    public void testFindFirstThreeEmployeesOrderedByBirthday() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<Employee> results = EmployeeDao.getInstance().findLimitedEmployeesOrderedByBirthday(session, 3);
        assertThat(results, hasSize(3));
        List<String> fullNames
                = results.stream().map(Employee::fullName).collect(toList());
        assertThat(fullNames, contains("Diane Greene", "Steve Jobs", "Bill Gates"));

        session.getTransaction().commit();
        session.close();
    }

    @Test
    public void testAllFindByOrganizationName() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<Employee> results = EmployeeDao.getInstance().findAllByOrganizationName(session, "Google");
        assertThat(results, hasSize(2));
        List<String> fullNames
                = results.stream().map(Employee::fullName).collect(toList());
        assertThat(fullNames, containsInAnyOrder("Sergey Brin", "Diane Greene"));

        session.getTransaction().commit();
        session.close();
    }

    @Test
    public void testFindAllPaymentsByOrganizationName() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<Payment> applePayments = EmployeeDao.getInstance().findAllPaymentsByOrganizationName(session, "Apple");
        assertThat(applePayments, hasSize(5));
        List<Integer> amounts = applePayments.stream().map(p -> p.getAmount().intValue()).collect(toList());
        assertThat(amounts, contains(250, 500, 600, 300, 400));

        session.getTransaction().commit();
        session.close();
    }

    @Test
    public void testFindAveragePaymentAmountByFirstAndLastNames() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Double averagePaymentAmount = EmployeeDao
                .getInstance()
                .findAveragePaymentAmountByFirstAndLastNames(session, "Bill", "Gates");
        assertThat(averagePaymentAmount, equalTo(300.0d));
        session.getTransaction().commit();
        session.close();
    }

    @Test
    public void testFindOrganizationNamesWithAvgEmployeePaymentsOrderedByOrgName() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<Object[]> results = EmployeeDao
                .getInstance()
                .findOrganizationNamesWithAvgEmployeePaymentsOrderedByOrgName(session);
        assertThat(results, hasSize(3));
        List<String> orgNames = results.stream().map(a -> (String) a[0]).collect(toList());
        assertThat(orgNames, contains("Apple", "Google", "Microsoft"));

        List<Double> orgAvgPayments = results.stream().map(a -> (Double) a[1]).collect(toList());
        assertThat(orgAvgPayments, contains(410.0d, 400.0d, 300.0d));

        session.getTransaction().commit();
        session.close();
    }

    @Test
    public void testCanYouDoIt() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<Object[]> results = EmployeeDao.getInstance().canYouDoIt(session);
        assertThat(results, hasSize(2));

        List<String> names = results.stream().map(r -> ((Employee) r[0]).fullName()).collect(toList());
        assertThat(names, contains("Sergey Brin", "Steve Jobs"));

        List<Double> averagePayments = results.stream().map(r -> (Double) r[1]).collect(toList());
        assertThat(averagePayments, contains(500.0d, 450.0d));

        session.getTransaction().commit();
        session.close();
    }

    @AfterAll
    static void finish() {
        sessionFactory.close();
    }
}
