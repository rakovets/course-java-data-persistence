package com.rakovets.course.datapersistence.solution.jpa;

import com.rakovets.course.datapersistence.solution.jpa.dal.dao.MessageDao;
import com.rakovets.course.datapersistence.solution.jpa.dal.entity.MessageEntity;
import com.rakovets.course.datapersistence.solution.jpa.util.DynamicEntityManagerFactoryUtil;

public class CreateMessageApp {
    public static void main(String[] args) {
        MessageEntity messageEntity1 = new MessageEntity("Hello");
        MessageEntity messageEntity2 = new MessageEntity("Database");
        MessageEntity messageEntity3 = new MessageEntity("Welcome to hell");
        MessageDao messageDao = new MessageDao();

        DynamicEntityManagerFactoryUtil.init("EclipseLink");
        messageDao.saveMessageEntity(messageEntity1);
        messageDao.saveMessageEntity(messageEntity2);
        messageDao.saveMessageEntity(messageEntity3);
        MessageEntity foundMessageEntityById = messageDao.findMessageEntityById(3);
        DynamicEntityManagerFactoryUtil.close();

        System.out.println(foundMessageEntityById);
    }
}
