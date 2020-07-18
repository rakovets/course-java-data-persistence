package com.rakovets.course.datapersistence.solution.jpa.util;

import com.rakovets.course.datapersistence.solution.jpa.dal.entity.Message;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CreateMessageApp {
    public static void main(String[] args) {
        Message message = new Message("Goodbye!");
        int messageId = 151;

        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("EclipseLink");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(message);
        entityManager.getTransaction().commit();
        entityManager.close();

        EntityManager entityManager1 = entityManagerFactory.createEntityManager();
        entityManager1.getTransaction().begin();
        System.out.println(entityManager1.find(Message.class, messageId).toString());
        entityManager1.getTransaction().commit();
        entityManager1.close();

        entityManagerFactory.close();
    }
}
