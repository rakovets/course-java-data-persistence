package com.rakovets.course.datapersistence.mapping.inheritance.demo;

import com.rakovets.course.datapersistence.mapping.inheritance.demo.dal.entity.tps.EmployeeTpsEntity;
import com.rakovets.course.datapersistence.mapping.inheritance.demo.dal.entity.tps.ManagerTpsEntity;
import com.rakovets.course.datapersistence.mapping.inheritance.demo.dal.entity.tps.ProgrammerTpsEntity;
import com.rakovets.course.datapersistence.mapping.inheritance.demo.dal.entity.tps.ProgrammingLanguage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TablePerSubclassTest {

    private static SessionFactory SESSION_FACTORY;

    @BeforeAll
    public static void init() {
        SESSION_FACTORY = new Configuration().configure().buildSessionFactory();
    }

    @Test
    public void testSaveEmployees() {
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();

        ProgrammerTpsEntity programmerEntity = new ProgrammerTpsEntity();
        programmerEntity.setName("Programmer");
        programmerEntity.setProgrammingLanguage(ProgrammingLanguage.JAVA);
        session.save(programmerEntity);

        ManagerTpsEntity managerEntity = new ManagerTpsEntity();
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

        List<EmployeeTpsEntity> employeeEntities = session
                .createQuery("from EmployeeTpsEntity", EmployeeTpsEntity.class)
                .getResultList();

        employeeEntities.forEach(System.out::println);

        List<ProgrammerTpsEntity> programmerEntities = session
                .createQuery("from ProgrammerTpsEntity", ProgrammerTpsEntity.class)
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
