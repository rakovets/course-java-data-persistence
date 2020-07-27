package com.rakovets.course.datapersistence.solution;

import com.rakovets.course.datapersistence.solution.hibernate.dal.dao.PaintingDao;
import com.rakovets.course.datapersistence.solution.hibernate.dal.entity.Painting;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PaintingDaoTest {
    static SessionFactory sessionFactory;
    static Session session;
    static Transaction transaction;
    static PaintingDao paintingDao;
    static Painting newPainting;



    @BeforeAll
    static void initDb() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();

        newPainting = new Painting("Wave", "No name");
        paintingDao = new PaintingDao();
        paintingDao.createPainting(newPainting, session);
    }

    @Test
    public void getPaintingTest() {
        Painting paintingFromDb = paintingDao.readPainting(1, session);
        assertEquals(newPainting, paintingFromDb);
    }

    @Test
    public void createPaintingTest() {
        Painting painting = new Painting("work", "David");
        paintingDao.createPainting(painting, session);
        Painting paintingFromDb = paintingDao.readPainting(2, session);
        assertEquals(painting, paintingFromDb);
    }

    @Test
    public void updatePaintingTest() {
        Painting paintingAfterUpdate = new Painting("Wave", "Karl");
        Painting paintingFromDb = paintingDao.readPainting(1, session);
        paintingDao.updatePainting(paintingFromDb, session);
        paintingFromDb.setNameAuthor("Karl");
        paintingDao.updatePainting(paintingFromDb, session);
        Painting painting = paintingDao.readPainting(1, session);
        assertEquals(paintingAfterUpdate, painting);
    }

    @Test
    public void deletePaintingTest() {
        Painting paintingFromDb = paintingDao.readPainting(1, session);
        paintingDao.deletePainting(paintingFromDb, session);
        Painting paintingAfterDelete = paintingDao.readPainting(1, session);
        assertEquals(paintingAfterDelete, null);
    }
}
