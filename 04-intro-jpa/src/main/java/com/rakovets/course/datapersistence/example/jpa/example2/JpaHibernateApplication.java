package com.rakovets.course.datapersistence.example.jpa.example2;

import com.rakovets.course.datapersistence.example.jpa.example2.dal.dao.EmployeeDaoImpl;
import com.rakovets.course.datapersistence.example.jpa.example2.dal.entity.EmployeeEntity;
import com.rakovets.course.datapersistence.example.jpa.example2.util.DynamicEntityManagerFactoryUtil;

import java.util.List;

public class JpaHibernateApplication {
    public static void main(String[] args) {
        EmployeeEntity employeeEntity = new EmployeeEntity("Dmitry Rakovets", 120000, "Software Engineer");
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();

        // Use Hibernate persistence unit
        DynamicEntityManagerFactoryUtil.init("Hibernate");

        System.out.println("\n\n\nCREATE:\n");
        employeeDao.save(employeeEntity);

        System.out.println("\n\n\nREAD:\n");
        EmployeeEntity one = employeeDao.findOne(1L);
        System.out.println(one);

        System.out.println("\n\n\nUPDATE:\n");
        one.setSalary(150_000);
        employeeDao.update(one);
        EmployeeEntity updated = employeeDao.findOne(1L);
        System.out.println(updated);

        System.out.println("\n\n\nDELETE:\n");
        employeeDao.delete(updated);
        List<EmployeeEntity> all = employeeDao.findAll();
        System.out.println("table size: " + all.size());

        DynamicEntityManagerFactoryUtil.close();
    }
}
