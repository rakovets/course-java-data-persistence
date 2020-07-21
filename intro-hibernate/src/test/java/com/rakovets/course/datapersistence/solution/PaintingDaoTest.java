package com.rakovets.course.datapersistence.solution;

import com.rakovets.course.datapersistence.solution.hibernate.dal.dao.PaintingDao;
import com.rakovets.course.datapersistence.solution.hibernate.dal.entity.Painting;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PaintingDaoTest {
    Painting newPainting = new Painting(2, "Wave", "No name");
    PaintingDao paintingDao = new PaintingDao();

    SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    Session session = sessionFactory.openSession();

    @Test
    public void getPaintingTest() {
        Painting paintingFromDb = paintingDao.readPainting(2, session);
        assertEquals(newPainting.getId(), paintingFromDb.getId());
        assertEquals(newPainting.getName(), paintingFromDb.getName());
        assertEquals(newPainting.getNameAuthor(), paintingFromDb.getNameAuthor());
    }
}
