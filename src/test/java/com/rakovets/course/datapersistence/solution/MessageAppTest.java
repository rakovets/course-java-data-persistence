package com.rakovets.course.datapersistence.solution;

import com.rakovets.course.datapersistence.solution.jpa.dal.entity.Message;
import com.rakovets.course.datapersistence.solution.jpa.util.CreateMessageApp;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.jupiter.api.Assertions.*;

public class MessageAppTest {
    private final CreateMessageApp messageApp = new CreateMessageApp();
    Message message = new Message("Hieee!");
    int messageId = 151;

    EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("EclipseLink");

    @Test
    public void saveMessageTest() {
        assertTrue(messageApp.saveMessage(message, entityManagerFactory));
    }

    @Test
    public void getMessageTest() {
        assertEquals(messageId, messageApp.getMessage(messageId, entityManagerFactory).getId());
    }
}
