package com.rakovets.course.datapersistence.solution.jpa.util;

import com.rakovets.course.datapersistence.solution.jpa.dal.entity.Message;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CreateMessageApp {
    public static void main(String[] args) {
        Message message = new Message("Hieee!");
        int messageId = 151;
        CreateMessageApp messageApp = new CreateMessageApp();

        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("EclipseLink");

        messageApp.saveMessage(message, entityManagerFactory);
        messageApp.getMessage(messageId, entityManagerFactory);

        entityManagerFactory.close();
    }

    public boolean saveMessage(Message message, EntityManagerFactory entityManagerFactory) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(message);
        entityManager.getTransaction().commit();
        entityManager.close();
        return true;
    }

    public Message getMessage(int messageId, EntityManagerFactory entityManagerFactory) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Message message = entityManager.find(Message.class, messageId);
        System.out.println(entityManager.find(Message.class, messageId).toString());
        entityManager.getTransaction().commit();
        entityManager.close();
        return message;
    }
}
