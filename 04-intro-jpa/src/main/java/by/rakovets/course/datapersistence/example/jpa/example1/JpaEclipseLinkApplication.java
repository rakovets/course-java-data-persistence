package by.rakovets.course.datapersistence.example.jpa.example1;

import by.rakovets.course.datapersistence.example.jpa.example1.dal.dao.EmployeeDao;
import by.rakovets.course.datapersistence.example.jpa.example1.dal.dao.EmployeeDaoJpa;
import by.rakovets.course.datapersistence.example.jpa.example1.dal.entity.EmployeeEntity;
import by.rakovets.course.datapersistence.example.jpa.example1.util.DynamicEntityManagerFactoryUtil;

public class JpaEclipseLinkApplication {
    public static void main(String[] args) {
        EmployeeEntity employeeEntity = new EmployeeEntity("Dmitry Rakovets", 120000, "Software Engineer");
        EmployeeDao employeeDao = new EmployeeDaoJpa();

        // Use EclipseLink persistence unit
        DynamicEntityManagerFactoryUtil.init("EclipseLink");
        employeeDao.save(employeeEntity);
        DynamicEntityManagerFactoryUtil.close();
    }
}
