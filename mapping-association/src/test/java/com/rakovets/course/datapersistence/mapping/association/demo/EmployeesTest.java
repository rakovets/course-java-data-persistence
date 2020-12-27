package com.rakovets.course.datapersistence.mapping.association.demo;

import com.rakovets.course.datapersistence.mapping.association.demo.entity.Address;
import com.rakovets.course.datapersistence.mapping.association.demo.entity.Employee;
import com.rakovets.course.datapersistence.mapping.association.demo.entity.Meeting;
import com.rakovets.course.datapersistence.mapping.association.demo.entity.Organization;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class EmployeesTest {

    private static SessionFactory SESSION_FACTORY;

    @BeforeAll
    public static void init() {
        SESSION_FACTORY = new Configuration().configure().buildSessionFactory();
    }

    @Test
    public void testSaveOrganization() {
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();

        Organization organization = new Organization();
        organization.setName("ItAcademy");

        session.save(organization);

        transaction.commit();
        session.close();
    }

    @Test
    public void testSaveEmployee() {
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();

        Organization organization = new Organization();
        organization.setName("Read Hat");
        session.save(organization);

        Employee employee = new Employee();
        employee.setName("Linus Torvalds");
        employee.setOrganization(organization);
        session.save(employee);

        transaction.commit();
        session.close();
    }

    @Test
    public void testGetEmployee() {
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();

        Employee employee = session.get(Employee.class, 1L);

        transaction.commit();
        session.close();
    }

    @Test
    public void testAddEmployeeToExistingOrg() {
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();

        Organization organization = session.get(Organization.class, 1L);

        Employee employee = new Employee();
        employee.setName("New employee");
        employee.setOrganization(organization);
        session.save(employee);

        transaction.commit();
        session.close();
    }

    @Test
    public void testGetOrganizationWithAllEmployees() {
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();

        Organization organization = session.load(Organization.class, 1L);

        transaction.commit();
        session.close();
    }

    @Test
    public void addAddressToEmployee() {
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();

        Employee employee = session.get(Employee.class, 1L);

        Address address = new Address();
        address.setCity("Minsk");
        address.setEmployee(employee);
        session.save(address);

        transaction.commit();
        session.close();
    }

    @Test
    public void addMeetingToExistingEmployee() {
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();

        Employee employee = session.get(Employee.class, 1L);

        Meeting meeting = new Meeting();
        meeting.getEmployees().add(employee);
        session.save(meeting);

        transaction.commit();
        session.close();
    }

    @AfterAll
    public static void finish() {
        SESSION_FACTORY.close();
    }
}
