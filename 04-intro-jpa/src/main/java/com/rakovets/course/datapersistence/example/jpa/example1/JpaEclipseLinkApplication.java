package com.rakovets.course.datapersistence.example.jpa.example1;

import com.rakovets.course.datapersistence.example.jpa.example1.dal.dao.EmployeeDao;
import com.rakovets.course.datapersistence.example.jpa.example1.dal.entity.EmployeeEntity;
import com.rakovets.course.datapersistence.example.jpa.example1.util.DynamicEntityManagerFactoryUtil;

public class JpaEclipseLinkApplication {
    public static void main(String[] args) {
        EmployeeEntity employeeEntity = new EmployeeEntity("Dmitry Rakovets", 120000, "Software Engineer");
        EmployeeDao employeeDao = new EmployeeDao();

        // Use EclipseLink persistence unit
        DynamicEntityManagerFactoryUtil.init("EclipseLink");
        employeeDao.save(employeeEntity);
        DynamicEntityManagerFactoryUtil.close();
    }
}
