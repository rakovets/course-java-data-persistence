package by.rakovets.course.datapersistence.example.cache.dao;

import by.rakovets.course.datapersistence.example.cache.entity.Employee;
import by.rakovets.course.datapersistence.example.cache.util.EmployeeTestDataImporter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


public class EmployeeDaoTest {
    @BeforeAll
    static void init() {
        EmployeeTestDataImporter.getInstance().importTestData();
    }

    @Test
    public void testFindById() {
        Employee employee1 = EmployeeDao.getInstance().findById(1L);
        Employee employee2 = EmployeeDao.getInstance().findById(1L);
        Employee employee3 = EmployeeDao.getInstance().findById(1L);
        Assertions.assertEquals(employee1.getId(), employee2.getId());
        Assertions.assertEquals(employee1.getId(), employee3.getId());
        Assertions.assertEquals(employee3.getId(), employee2.getId());
    }
}
