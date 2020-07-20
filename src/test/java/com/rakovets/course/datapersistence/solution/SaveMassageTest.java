package com.rakovets.course.datapersistence.solution;

import com.rakovets.course.datapersistence.solution.entity.Messages;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Test;

import javax.persistence.Persistence;
import java.util.logging.Logger;

public class SaveMassageTest {
    private static SessionFactory SESSION_FACTORY;
    private static Logger LOGER = Logger.getLogger(String.valueOf(SaveMassageTest.class));

    @Test
    public void testSaveMassage() {
        Persistence.createEntityManagerFactory("Message");
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();
        Messages messages = new Messages();
        messages.setText("Hello");
        Long id = (Long) session.save(messages);
        transaction.commit();
        session.close();

        session = SESSION_FACTORY.openSession();
        Transaction transaction1 = session.beginTransaction();

        Messages saveMassage = session.get(Messages.class,id);
        saveMassage = session.get(Messages.class , id);
        System.out.println(saveMassage);
    }
}