package com.rakovets.course.datapersistence.solution;

import com.rakovets.course.datapersistence.solution.jpa.dal.dao.MessageDao;
import com.rakovets.course.datapersistence.solution.jpa.dal.entity.MessageEntity;
import com.rakovets.course.datapersistence.solution.jpa.util.DynamicEntityManagerFactoryUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;

public class TestMessageDao {

    @Test
    public void testFindMessageEntityById() {
        EntityManager entityManager = DynamicEntityManagerFactoryUtil.getEntityManager();
        entityManager.getTransaction().begin();
        MessageDao messageDao = new MessageDao();
        Assertions.assertEquals("Message{id=3, text='Welcome to hell'}",
                messageDao.findMessageEntityById(3).toString());
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Test
    public void testSaveMessageDao() {
        EntityManager entityManager = DynamicEntityManagerFactoryUtil.getEntityManager();
        entityManager.getTransaction().begin();
        MessageEntity messageEntity = new MessageEntity("Test");
        MessageDao messageDao = new MessageDao();
        messageDao.saveMessageEntity(messageEntity);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
