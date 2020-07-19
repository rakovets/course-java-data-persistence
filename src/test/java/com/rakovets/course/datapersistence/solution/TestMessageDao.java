package com.rakovets.course.datapersistence.solution;

import com.rakovets.course.datapersistence.solution.jpa.dal.dao.MessageDao;
import com.rakovets.course.datapersistence.solution.jpa.dal.entity.MessageEntity;
import com.rakovets.course.datapersistence.solution.jpa.util.DynamicEntityManagerFactoryUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;

public class TestMessageDao {

    @Test
    public void testFindMessageEntityById() {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        MessageDao messageDao = new MessageDao();
        Assertions.assertEquals("Message{id=3, text='Welcome to hell'}",
                messageDao.findMessageEntityById(3).toString());
        transaction.commit();
        session.close();
    }

    @Test
    public void testSaveMessageDao() {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        MessageDao messageDao = new MessageDao();
        messageDao.saveMessageEntity(new MessageEntity("Test"));
        transaction.commit();
        session.close();
    }
}
