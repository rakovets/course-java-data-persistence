package by.rakovets.course.datapersistence.example.jpa.example2.dal.dao;

import by.rakovets.course.datapersistence.example.jpa.example1.util.DynamicEntityManagerFactoryUtil;
import by.rakovets.course.datapersistence.example.jpa.example2.dal.entity.EmployeeEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
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
        EntityManager entityManager = DynamicEntityManagerFactoryUtil.getEntityManager();
        entityManager.getTransaction().begin();
        Query queryClear = entityManager.createQuery("DELETE FROM EmployeeEntity WHERE TRUE");
        queryClear.executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Test
    void saveTest() {
        // GIVEN
        EmployeeEntity expected =
                new EmployeeEntity("Dmitry Rakovets", 120000, "Software Engineer");

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
                new EmployeeEntity("Dmitry Rakovets", 120000, "Software Engineer");
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
                new EmployeeEntity("Dmitry Rakovets", 120000, "Software Engineer");
        EmployeeEntity employeeEntity2 =
                new EmployeeEntity("Dmitry Rakovets", 240000, "Senior Software Engineer");
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
                new EmployeeEntity("Dmitry Rakovets", 120000, "Software Engineer");
        baseDaoWithEmployee.save(expected);

        // WHEN
        expected.setSalary(150_000);
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
                new EmployeeEntity("Dmitry Rakovets", 120000, "Software Engineer");
        baseDaoWithEmployee.save(expected);

        // WHEN
        baseDaoWithEmployee.delete(expected);

        // THEN
        List<EmployeeEntity> actual = baseDaoWithEmployee.findAll();
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(actual.size(), 0);
    }
}
