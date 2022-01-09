package com.rakovets.course.datapersistence.mapping.inheritance.demo;

import com.rakovets.course.datapersistence.mapping.inheritance.demo.dal.entity.tph.EmployeeTphEntity;
import com.rakovets.course.datapersistence.mapping.inheritance.demo.dal.entity.tph.ManagerTphEntity;
import com.rakovets.course.datapersistence.mapping.inheritance.demo.dal.entity.tph.ProgrammerTphEntity;
import com.rakovets.course.datapersistence.mapping.inheritance.demo.dal.entity.tph.ProgrammingLanguage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TablePerHierarchyTest {

    private static SessionFactory SESSION_FACTORY;

    @BeforeAll
    public static void init() {
        SESSION_FACTORY = new Configuration().configure().buildSessionFactory();
    }

    @Test
    public void testSaveEmployees() {
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();

        ProgrammerTphEntity programmerEntity = new ProgrammerTphEntity();
        programmerEntity.setName("Programmer");
        programmerEntity.setProgrammingLanguage(ProgrammingLanguage.JAVA);
        session.save(programmerEntity);

        ManagerTphEntity managerEntity = new ManagerTphEntity();
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

        List<EmployeeTphEntity> employeeEntities = session
                .createQuery("from EmployeeTphEntity", EmployeeTphEntity.class)
                .getResultList();

        employeeEntities.forEach(System.out::println);

        List<ProgrammerTphEntity> programmerEntities = session
                .createQuery("from ProgrammerTphEntity", ProgrammerTphEntity.class)
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
