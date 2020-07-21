package com.rakovets.course.datapersistence.solution;

import com.rakovets.course.datapersistence.solution.dal.dao.PaintingDao;
import com.rakovets.course.datapersistence.solution.dal.entity.Painting;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestPaintingDao {

    @Test
    public void testCreate() {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        PaintingDao paintingDao = new PaintingDao();
        Painting paintingTest = new Painting("authorNameTest", "nameTest");
        paintingDao.create(paintingTest, session);
        transaction.commit();
        session.close();
    }

    @Test
    public void testRead() {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        PaintingDao paintingDao = new PaintingDao();
        Painting paintingTest = new Painting("Mikhail Bulgakov", "Master and Margarita");
        paintingDao.create(paintingTest, session);
        Assertions.assertEquals("Painting{id=1, authorName='Mikhail Bulgakov', name='Master and Margarita'}",
                paintingDao.read(1, session).toString());
        transaction.commit();
        session.close();
    }

    @Test
    public void testUpdate() {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        PaintingDao paintingDao = new PaintingDao();
        Painting paintingTest = new Painting("Mikhail Bulgakov", "Master and Margarita");
        paintingDao.create(paintingTest, session);
        Painting readPainting = paintingDao.read(1, session);
        readPainting.setAuthorName("authorNameTest");
        readPainting.setName("nameTest");
        paintingDao.update(readPainting, session);
        Assertions.assertEquals("Painting{id=1, authorName='authorNameTest', name='nameTest'}",
                paintingDao.read(1, session).toString());
        transaction.commit();
        session.close();
    }

    @Test
    public void testDelete() {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        PaintingDao paintingDao = new PaintingDao();
        Painting paintingTest = new Painting("Mikhail Bulgakov", "Master and Margarita");
        paintingDao.create(paintingTest, session);
        paintingDao.delete(paintingTest, session);
        Assertions.assertEquals(null, paintingDao.read(1, session));
        transaction.commit();
        session.close();
    }
}
