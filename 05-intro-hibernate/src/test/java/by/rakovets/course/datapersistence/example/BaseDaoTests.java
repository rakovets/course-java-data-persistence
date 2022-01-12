package by.rakovets.course.datapersistence.example;

import by.rakovets.course.datapersistence.example.hibernate.dal.dao.BaseDao;
import by.rakovets.course.datapersistence.example.hibernate.dal.dao.EmployeeDaoImpl;
import by.rakovets.course.datapersistence.example.hibernate.dal.entity.EmployeeEntity;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class BaseDaoTests {
    private static BaseDao<EmployeeEntity> baseDaoWithEmployee;

    @BeforeAll
    static void initTestComponent() {
        baseDaoWithEmployee = new EmployeeDaoImpl();
    }

    @BeforeEach
    void clearDB() {
        Session session = new Configuration().configure().buildSessionFactory().openSession();
        session.getTransaction().begin();
        Query queryClear = session.createQuery("DELETE FROM EmployeeEntity WHERE TRUE");
        queryClear.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Test
    void saveTest() {
        // GIVEN
        EmployeeEntity expected =
                new EmployeeEntity(0L, "Dmitry Rakovets", 120000.0, "Software Engineer");

        // WHEN
        baseDaoWithEmployee.save(expected);

        // THEN
        EmployeeEntity actual = baseDaoWithEmployee.findOne(expected.getId());
        Assertions.assertNotNull(actual);
        Assertions.assertNotNull(actual.getId());
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void findOneTest() {
        // GIVEN
        EmployeeEntity expected =
                new EmployeeEntity(0L, "Dmitry Rakovets", 120000.0, "Software Engineer");
        baseDaoWithEmployee.save(expected);

        // WHEN
        EmployeeEntity actual = baseDaoWithEmployee.findOne(expected.getId());

        // THEN
        Assertions.assertNotNull(actual);
        Assertions.assertNotNull(actual.getId());
    }

    @Test
    void findAllTest() {
        // GIVEN
        EmployeeEntity employeeEntity1 =
                new EmployeeEntity(0L, "Dmitry Rakovets", 120000.0, "Software Engineer");
        EmployeeEntity employeeEntity2 =
                new EmployeeEntity(0L, "Dmitry Rakovets", 240000.0, "Senior Software Engineer");
        List<EmployeeEntity> expected = List.of(employeeEntity1, employeeEntity2);
        baseDaoWithEmployee.save(employeeEntity1);
        baseDaoWithEmployee.save(employeeEntity2);

        // WHEN
        List<EmployeeEntity> actual = baseDaoWithEmployee.findAll();

        // THEN
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expected.size(), actual.size());
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void updateTest() {
        // GIVEN
        EmployeeEntity expected =
                new EmployeeEntity(0L, "Dmitry Rakovets", 120000.0, "Software Engineer");
        baseDaoWithEmployee.save(expected);

        // WHEN
        expected.setSalary(150_000.0);
        baseDaoWithEmployee.update(expected);

        // THEN
        EmployeeEntity actual = baseDaoWithEmployee.findOne(expected.getId());
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void deleteTest() {
        // GIVEN
        EmployeeEntity expected =
                new EmployeeEntity(0L, "Dmitry Rakovets", 120000.0, "Software Engineer");
        baseDaoWithEmployee.save(expected);

        // WHEN
        baseDaoWithEmployee.delete(expected);

        // THEN
        List<EmployeeEntity> actual = baseDaoWithEmployee.findAll();
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(actual.size(), 0);
    }
}
