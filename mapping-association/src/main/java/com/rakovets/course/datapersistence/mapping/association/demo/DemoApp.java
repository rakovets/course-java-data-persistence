package com.rakovets.course.datapersistence.mapping.association.demo;

import com.rakovets.course.datapersistence.mapping.association.demo.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class DemoApp {
    private static final SessionFactory SESSION_FACTORY = new Configuration().configure().buildSessionFactory();

    public static void main(String[] args) {
        Session session = SESSION_FACTORY.openSession();

        session.save(new Employee());

        session.close();
        SESSION_FACTORY.close();
    }
}
