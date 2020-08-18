package com.rakovets.course.datapersistence.mapping.inheritance.demo;

import com.rakovets.course.datapersistence.mapping.inheritance.demo.dal.entity.tpc.EmployeeTpcEntity;
import com.rakovets.course.datapersistence.mapping.inheritance.demo.dal.entity.tpc.ManagerTpcEntity;
import com.rakovets.course.datapersistence.mapping.inheritance.demo.dal.entity.tpc.ProgrammerTpcEntity;
import com.rakovets.course.datapersistence.mapping.inheritance.demo.dal.entity.tpc.ProgrammingLanguage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TablePerConcreteClassTest {

    private static SessionFactory SESSION_FACTORY;

    @BeforeAll
    public static void init() {
        SESSION_FACTORY = new Configuration().configure().buildSessionFactory();
    }

    @Test
    public void testSaveEmployees() {
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();

        ProgrammerTpcEntity programmerEntity = new ProgrammerTpcEntity();
        programmerEntity.setName("Programmer");
        programmerEntity.setProgrammingLanguage(ProgrammingLanguage.JAVA);
        session.save(programmerEntity);

        ManagerTpcEntity managerEntity = new ManagerTpcEntity();
        managerEntity.setNumberOfIdleHours(7);
        managerEntity.setName("Manager");
        session.save(managerEntity);

        transaction.commit();
        session.close();
    }

    @Test
    public void testGetEmployees() {
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();

        List<EmployeeTpcEntity> employeeEntities = session
                .createQuery("from EmployeeTpcEntity", EmployeeTpcEntity.class)
                .getResultList();

        employeeEntities.forEach(System.out::println);

        List<ProgrammerTpcEntity> programmerEntities = session
                .createQuery("from ProgrammerTpcEntity", ProgrammerTpcEntity.class)
                .getResultList();

        programmerEntities.forEach(System.out::println);

        transaction.commit();
        session.close();
    }

    @AfterAll
    public static void finish() {
        SESSION_FACTORY.close();
    }
}
